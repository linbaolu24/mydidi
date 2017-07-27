package cn.com.didi.order.orders.service.impl.plugin;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import cn.com.didi.core.filter.IOperationInterceptor;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.UseAbleDto;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.InternalFlagEnum;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OperationNotifyFlag;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.users.domain.VipDto;
import cn.com.didi.user.users.service.IUserExperienceService;


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
	@Resource
	protected IOrderInfoService orderInfoService;

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
				if(result!=null&&!result.success()){
					return result;
				}
				result=handleCountController(order, data);
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
		/*if(OrderMessageOperation.FINISH_EVALUATION.equals(operation)&&order.getEvaluation()!=null){
			reciverSearch.updateEve(order.getMerchantAccountId(), eve);
		}*/
		return null;
	}

	protected <R> IOrderRuslt<R> merchantVerify(OrderContextDto data) {
		IMerchantDto dto = data.getMerchantDto();
		if (dto == null) {
			return new OrderRuslt<>( OrderMessageConstans.ORDER_NO_SPECAIL_MERCHANT);
		}
		OrderDto order = data.getOrderDto();
		order.setMasterName(dto.getMasterName());
		order.setMerchantAccountId(dto.getMerchantId());
		order.setMlat(dto.getLat());
		order.setMlng(dto.getLng());
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
			return new OrderRuslt<>(message,OrderMessageConstans.ORDER_NO_DEPOSITE.getCode());
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
	public  <R> IOrderRuslt<R> handleCountController(OrderDto order, OrderContextDto data){
		LOGGER.debug("次数控制校验");
		long interval=appEnv.getMrmfDayInterval();
		if(interval<=0){
			return null;
		}
		Date date=orderInfoService.selectLastOfst(order.getConsumerAccountId(), order.getSlsId(),
				OrderState.ORDER_STATE_PENDING_CHARGE.getCode(),OrderState.ORDER_STATE_Pending_EVALUATION.getCode(),OrderState.ORDER_STATE_FINISH.getCode());
		if(date==null){
			return null;
		}
	
		long realInterval=DateUtil.getIntervalDay(date,order.getOct());
		LOGGER.debug("上次完成服务时间{},本次时间{},间隔{},系统间隔{}",date,order.getOct(),realInterval,interval);
		if(realInterval<interval){
			String message=OrderMessageConstans.ORDER_MRMF_INTERVAL_NOT_ARRIVE.getMessage(interval,interval-realInterval);
			return new OrderRuslt<>(message,OrderMessageConstans.ORDER_MRMF_INTERVAL_NOT_ARRIVE.getCode());
		}
		return null;
	}
	public <R> UseAbleDto<R> getCountControllerByMonth(OrderDto order,OrderContextDto data,Consumer<UseAbleDto> consumer){
		LOGGER.debug("对每月数量控制");
		int numOfMonth=appEnv.getMrmfCountByMonth();
		Calendar cal=Calendar.getInstance();
		Date startDate=DateUtil.getFirstDayOfMonth(cal);
		cal.add(Calendar.DAY_OF_MONTH, 1);
		Date endTime=cal.getTime();
		int used=orderInfoService.count(order.getConsumerAccountId(), order.getSlsId(), startDate, endTime, OrderState.ORDER_STATE_PENDING_CHARGE.getCode(),OrderState.ORDER_STATE_Pending_EVALUATION.getCode(),OrderState.ORDER_STATE_FINISH.getCode());
		LOGGER.debug("时间起{},时间止{},每月免费洗发数量{},本月已使用{}",startDate,endTime,order.getOct(),numOfMonth,used);
		UseAbleDto<R> userable=new UseAbleDto<>(used, numOfMonth, null);
		if(consumer!=null){
			consumer.accept(userable);
		}
		return userable;
	}

	public <R> IOrderRuslt<R> handleCountControllerByMonth(OrderDto order, OrderContextDto data) {
		UseAbleDto<Void> userd= getCountControllerByMonth(order, data, null);
		int numOfMonth=userd.getCount();
		int used=userd.getUsed();
		if (numOfMonth < used) {
			String message=OrderMessageConstans.ORDER_MRMF_MONTH_NUM_CONTROLLER.getMessage(used,numOfMonth,numOfMonth-used);
			return new OrderRuslt<>(message, OrderMessageConstans.ORDER_MRMF_MONTH_NUM_CONTROLLER.getCode());
		}
		return null;
	}
	public <R> IOrderRuslt<R>  handleAuthCountControllerByMonth(OrderDto order, OrderContextDto data,VipDto vipDto) {
		UseAbleDto<VipDto> userd= getCountControllerByMonth(order, data, null);
		int numOfMonth=userd.getCount();
		int used=userd.getUsed();
		String message=OrderMessageConstans.ORDER_MRMF_MONTH_NUM_CONTROLLER.getMessage(used,numOfMonth,numOfMonth-used);
		userd.setDescription(message);
		userd.setData(vipDto);
		data.setUsed(userd);
		return new OrderRuslt<R>((Long)null);
	}
}

