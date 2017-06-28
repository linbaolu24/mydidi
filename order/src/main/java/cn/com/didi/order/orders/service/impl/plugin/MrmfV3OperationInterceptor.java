package cn.com.didi.order.orders.service.impl.plugin;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.message.Message;
import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.UseAbleDto;
import cn.com.didi.domain.util.LatLngUtiil;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.user.users.domain.VipDto;
import cn.com.didi.user.users.service.IVipService;

/**
 * @author xlm
 *
 */
@Service
public class MrmfV3OperationInterceptor extends MrmfV2OperationInterceptor{
	private static final Logger LOGGER = LoggerFactory.getLogger(MrmfV3OperationInterceptor.class);
	@Resource
	protected IVipService vipService;
	public <R> IOrderRuslt<R> interceptor(OrderMessageOperation operation, OrderContextDto data, IOrderService source){
		OrderDto order = data.getOrderDto();
		if (SpecialTypeEnum.MRMF.codeEqual(order.getSpecialType())){
			if (OrderMessageOperation.AUTH.equals(operation)||OrderMessageOperation.BEFORE_PUBLISH.equals(operation)){
				VipDto dto=getVipDto(order, data);
				IOrderRuslt<R> result= vipHandle(order, data, source,dto);
				if(result!=null&&!result.success()){
					return result;
				}
				result=handleDeposit(order, data);
				if(result!=null&&!result.success()){
					return result;
				}
				result=handleCountController(order, data);
				if(OrderMessageOperation.AUTH.equals(operation)&&(result==null||result.success())){
					UseAbleDto<VipDto> userAbleDto=new UseAbleDto<>(0, 0, "");
					userAbleDto.setData(dto);
					data.setUsed(userAbleDto);
				}
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
		LOGGER.debug("订单 {} VIP校验结果{}",order,hasVip);
		if(hasVip){
			return null;
		}
		return new OrderRuslt<>(OrderMessageConstans.ORDER_MRMF_NOT_VIP);	
	}
	protected <T> IOrderRuslt<T> handleDistance(OrderDto order, OrderContextDto data, IOrderService source){
		LOGGER.debug("对距离进行校验");
		boolean hasVip=vipService.hasVip(order.getConsumerAccountId(), order.getSlsId());
		LOGGER.debug("订单 {} VIP校验结果{}",order,hasVip);
		if(hasVip){
			return null;
		}
		return new OrderRuslt<>(OrderMessageConstans.ORDER_MRMF_NOT_VIP);	
	}
	protected <R> IOrderRuslt<R> merchantVerify(OrderContextDto data) {
		IOrderRuslt<R> result=super.merchantVerify(data);
		if(result!=null&&!result.success()){
			return result;
		}
		int distance=appEnv.getMrmfCommmitMaxDistance();
		LOGGER.debug("订单最小提交距离为{}米",distance);
		if(distance<=0){
			return null;
		}
		OrderDto dto=data.getOrderDto();
		double rdistance=Math.abs(LatLngUtiil.getDistance(dto.getLng().doubleValue(), dto.getLat().doubleValue(),
				dto.getMlng().doubleValue(), dto.getLat().doubleValue()));
		if(rdistance>distance){
			Message message=OrderMessageConstans.ORDER_COUSMER_MERCHANT_DISTANCE_TOO_LONG;
			return new OrderRuslt<>(message.getMessage(distance),message.getCode());
		}
		return null;
		
	}
}
