package cn.com.didi.order.orders.service.impl.plugin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.filter.IOperationInterceptor;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.InternalFlagEnum;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OperationNotifyFlag;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.users.service.IUserExperienceService;

@Service
public class MrmfOperationInterceptor
		implements IOperationInterceptor<OrderMessageOperation, OrderContextDto, IOrderService> {
	private static final Logger LOGGER = LoggerFactory.getLogger(MrmfOperationInterceptor.class);
	@Resource
	protected IReciverSearchService reciverSearch;
	@Resource
	protected IDepositService depositService;
	@Resource
	protected IUserExperienceService userExperienceService;
	@Resource
	protected IAppEnv appEnv;


	@Override
	public <R> IOrderRuslt<R> interceptor(OrderMessageOperation operation, OrderContextDto data, IOrderService source) {
		OrderDto order = data.getOrderDto();
		if (SpecialTypeEnum.MRMF.codeEqual(order.getSpecialType())) {
			if (OrderMessageOperation.BEFORE_PUBLISH.equals(operation)) {
				// 对于美容美发在发布之前需要对消费者进行校验
				IOrderRuslt<R> result=handleExist(order, data, source);
				if(result != null &&! result.success()){
					return result;
				}
				result = handleUserExperence(order, data);
				if (result == null || result.success()) {
					return result;
				}
				result = handleDeposit(order, data);
				return result;
			} else if (OrderMessageOperation.BEFORE_ADD.equals(operation)) {
				Couple<IMerchantDto, IReciverDto> couple = reciverSearch
						.getMerchantAndReciver(order.getMerchantAccountId());
				if (couple != null) {
					data.setMerchantDto(couple.getFirst());
					data.setReciverDto(couple.getSecond());
				}
				return merchantVerify(data);
			} else if (OrderMessageOperation.ORDER_PUBLISHFAIL.equals(operation)) {
				return handleFail(order, data);
			} else if (OrderMessageOperation.ORDER_PUBLISHED.equals(operation)) {
				notifyMerchant(order, data, source);
			} else if (OrderMessageOperation.CANCELED.equals(operation)) {
				return handleCancel(order,data);
			}
		}
		return null;
	}

	protected <R> IOrderRuslt<R> merchantVerify(OrderContextDto data) {
		IMerchantDto dto = data.getMerchantDto();
		if (dto == null) {
			
		}
		OrderDto order = data.getOrderDto();
		order.setMasterName(dto.getMasterName());
		order.setMerchantAccountId(dto.getMerchantId());
		order.setMlat(dto.getLat());
		order.setMlng(order.getMlng());
		order.setMci(dto.getMci());
		order.setState(OrderState.ORDER_STATE_START_SERVICE.getCode());
		return null;
	}
	
	protected <R> IOrderRuslt<R> handleExist(OrderDto order, OrderContextDto data, IOrderService source) {
		IOrderRuslt<R> result = null;
		LOGGER.debug("订单{}判断是否存在未完成", order);
		boolean exists=source.existNotFinishOrder(order.getConsumerAccountId(), order.getSlsId());
		if(exists){
			return new OrderRuslt<R>(OrderMessageConstans.ORDER_EXIST_NOT_END_MRMF_ORDER);
		}
		return result;

	}
	
	
	protected <R> IOrderRuslt<R> handleUserExperence(OrderDto order, OrderContextDto data) {
		IOrderRuslt<R> result = null;
		LOGGER.debug("订单{}增加体验机会", order);
		boolean added = userExperienceService.addExperience(order.getConsumerAccountId(), order.getSlsId());
		if (!added) {
			LOGGER.debug("不可以增加体验机会.");
			return new OrderRuslt<>(OrderMessageConstans.ORDER_NO_USER_EXPERIENCE);
		}
		LOGGER.debug("========设置体验机会标志=========");
		data.setFlag(OperationNotifyFlag.ADD_ExperienceService.flagSet(data.getFlag()));
		order.setInternalFlag(InternalFlagEnum.EXPERIENCE.flagSet(order.getInternalFlag()==null?0:order.getInternalFlag()));
		LOGGER.debug("========体验机会结束=========");
		return result;

	}

	protected <R> IOrderRuslt<R> handleDeposit(OrderDto order, OrderContextDto data) {
		LOGGER.debug("判断订单用户押金情况{}", order.getConsumerAccountId());
		boolean isExists = depositService.existDeposit(order.getConsumerAccountId());
		LOGGER.debug("佣金存在? {}", isExists);
		if(!isExists){
			String message=OrderMessageConstans.ORDER_NO_DEPOSITE.getMessage(NumberUtil.intToDecimal2(appEnv.getDeposite()));
			return new OrderRuslt<>(OrderMessageConstans.ORDER_NO_DEPOSITE.getCode(),message);
		}
		return null;

	}

	protected <R> IOrderRuslt<R> handleFail(OrderDto order, OrderContextDto data) {
		try {
			if (OperationNotifyFlag.ADD_ExperienceService.isFlagSet(data.getFlag())) {
				userExperienceService.releaseExperience(order.getConsumerAccountId(), order.getSlsId());
			}
		} catch (Exception e) {
			LOGGER.error("订单 {},释放体验机会失败.", order, e);
		}
		return null;
	}
	

	protected <R> IOrderRuslt<R> handleCancel(OrderDto order, OrderContextDto data) {
		try {
			if (InternalFlagEnum.EXPERIENCE.isFlagSet(order.getInternalFlag())) {
				userExperienceService.releaseExperience(order.getConsumerAccountId(), order.getSlsId());
			}
		} catch (Exception e) {
			LOGGER.error("取消订单 {},释放体验机会失败.", order, e);
		}
		return null;
	}

	public void notifyMerchant(OrderDto order, OrderContextDto data, IOrderService source) {
		try {
			source.notifyMerchantOrder(order, data.getReciverDto());
		} catch (Exception e) {
			LOGGER.error("订单 {},通知商户失败.", order, e);
		}

	}
}
