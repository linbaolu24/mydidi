package cn.com.didi.order.orders.service;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.domains.UseAbleDto;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderStateDto;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.user.users.domain.VipDto;

/**
 * @author xlm
 *
 */
public interface IOrderService {
	/**
	 * 发布订单 date为订单创建时间
	 */
	public IOrderRuslt<Void> publish(OrderDto info);
	/**
	 * @param dto
	 * @return
	 */
	public IOrderRuslt<UseAbleDto<VipDto>> auth(OrderDto dto);
	/**
	 * 指定商户发布订单
	 */
	public void notifyMerchantOrder(OrderDto info,IReciverDto reciver);
	/**
	 * 自动派单,state 为{@code ORDER_STATE_PUBLISH 可以自动派单}
	 */
	public IOrderRuslt<Void> autoDispatch(Long orderId,Long bId);
	/**
	 * 订单改派
	 * @param orderId
	 * @param bId
	 * @return
	 */
	public IOrderRuslt<Void> reassignment(Long orderId,Long bId);
	/**
	 * 尝试重新派单,状态位通知或发布中的可以可
	 * @deprecated
	 */
	public void reAutoDispatch(IOrderInfo info);
	/**
	 * 发出抢单通知
	 */
	public IOrderRuslt<Void> notifyOrder(Long orderId,Long bId);
	/**
	 * 接受订单
	 */
	public IOrderRuslt<OrderDto> accept(Long orderId,Long bId);
	/**
	 * 开始服务
	 */
	public IOrderRuslt<OrderDto> startService(Long orderId,Long mercharId);
	/**
	 * @param info
	 */
	public IOrderRuslt<OrderDto> finishService(Long orderId, Long mercharId);
	/**
	 * @param info
	 */
	public IOrderRuslt<OrderDto>  charge(Long orderId,Long mercharId,int cost,String cment);
	
	 /**
	 * @param orderId
	 * @param mercharId
	 * @param cost
	 * @param cment
	 * @return
	 */
	public IOrderRuslt<OrderDto>  finishServiceAndcharge(Long orderId,Long mercharId,int cost,String cment);
	/**
	 * 评价
	 */
	public IOrderRuslt<Void> evaluation(Long orderId,Long bId,int eveal,String textEval);
	/**
	 * 订单超时
	 */
	public IOrderRuslt<Void>  timeout(Long orderId, Long bId);
	/**
	 * 取消订单
	 */
	public IOrderRuslt<OrderStateDto> cannel(Long orderId, Long bId);
	/**
	 * 没有接单人
	 */
	public void noReceiver(IOrderInfo info);
	/**
	 * 设置已装饰的service
	 * @param service
	 */
	public void setDecoratedOrderService(IOrderService service);
	
	
	/**
	 * @param orderId
	 * @param bId
	 * @return
	 */
	public IOrderRuslt<OrderDealDescDto> createDeal(Long orderId, Long bId,PayAccountEnum payEnum,String desc);
	

	/**
	 * @param payResult
	 * @return
	 */
	public IOrderRuslt<Void> finishDeal(PayResultDto payResult);
	
	/**
	 * @param accountId
	 * @param slsId
	 * @return
	 */
	public boolean existNotFinishOrder(Long accountId,Integer slsId);
	/**
	 * 在检查的前提下获取改派订单
	 * @param orderId
	 * @return
	 */
	IOrderRuslt<OrderDto> getOrderWithCheckChangeDispatch(Long orderId);
	
}
