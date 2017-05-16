package cn.com.didi.order.orders.service.impl.plugin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.property.Couple;
import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.user.users.service.IVipService;
@Service
public class MrmfV2OperationInterceptor extends MrmfOperationInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(MrmfV2OperationInterceptor.class);
	@Resource
	protected IVipService vipService;
	public <R> IOrderRuslt<R> interceptor(OrderMessageOperation operation, OrderContextDto data, IOrderService source){
		OrderDto order = data.getOrderDto();
		if (SpecialTypeEnum.MRMF.codeEqual(order.getSpecialType())){
			if (OrderMessageOperation.AUTH.equals(operation)||OrderMessageOperation.BEFORE_PUBLISH.equals(operation)){
				IOrderRuslt<R> result= vipHandle(order, data, source);
				if(result!=null&&!result.success()){
					return result;
				}
				result=handleDeposit(order, data);
				if(result!=null&&!result.success()){
					return result;
				}
				result=handleCountController(order, data);
				return result;
			} else if (OrderMessageOperation.BEFORE_ADD.equals(operation)) {
				return popBeforeAdd(order, data, source);
			}
		}
		return null;
	}
	protected <T> IOrderRuslt<T> vipHandle(OrderDto order, OrderContextDto data, IOrderService source){
		LOGGER.debug("开始VIP校验");
		boolean hasVip=vipService.hasVip(order.getConsumerAccountId(), order.getSlsId());
		LOGGER.debug("订单 {} VIP校验结果",order,hasVip);
		if(hasVip){
			return null;
		}
		return new OrderRuslt<>(OrderMessageConstans.ORDER_MRMF_NOT_VIP);	
	}
	protected  <T> IOrderRuslt<T> popBeforeAdd(OrderDto order, OrderContextDto data, IOrderService source){
		Couple<IMerchantDto, IReciverDto> couple = reciverSearch
				.getMerchantAndReciver(order.getMerchantAccountId());
		
		order.setEvaluation(5);
		if (couple != null) {
			data.setMerchantDto(couple.getFirst());
			data.setReciverDto(couple.getSecond());
		}
		IOrderRuslt<T> result= merchantVerify(data);
		order.setState(OrderState.ORDER_STATE_FINISH.getCode());
		return result;
	}
}
