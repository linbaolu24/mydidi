package cn.com.didi.order.orders.service;

import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderStateCostDto;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * @author xlm
 *
 */
public interface IOrderService {
	/**
	 * 失败
	 */
	static int ORDER_STATE_FAIL=0;
	/**
	 * 完成
	 */
	static int ORDER_STATE_FINISH=1;
	/**
	 * 取消
	 */
	static int ORDER_STATE_CANNEL=2;
	/**
	 * 发布
	 */
	static int ORDER_STATE_PUBLISH=3;
	/**
	 * 通知中
	 */
	static int ORDER_STATE_NOTIFY=4;
	/**接单状态*/
	static int ORDER_STATE_TAKING=5;
	/**
	 * 开始服务
	 */
	static int ORDER_STATE_START_SERVICE=6;
	/**
	 * 待收费
	 */
	static int ORDER_STATE_PENDING_CHARGE=7;
	/**
	 * 待评价
	 */
	static int ORDER_STATE_Pending_EVALUATION=8;
	/**
	 * 发布订单
	 */
	public IOrderRuslt<Void> publish(OrderDto info);
	/**
	 * 自动派单,state 为{@code ORDER_STATE_PUBLISH 可以自动派单}
	 */
	public IOrderRuslt<Void> autoDispatch(Long orderId,Long bId);
	/**
	 * 尝试重新派单,状态位通知或发布中的可以可
	 */
	public void reAutoDispatch(IOrderInfo info);
	/**
	 * 发出抢单通知
	 */
	public IOrderRuslt<Void> notifyOrder(Long orderId,Long bId);
	/**
	 * 接受订单
	 */
	public IOrderRuslt<Void> accept(Long orderId,Long bId);
	/**
	 * 开始服务
	 */
	public IOrderRuslt<Void> startService(Long orderId,Long mercharId);
	/**
	 * @param info
	 */
	public IOrderRuslt<Void> finishService(Long orderId, Long mercharId);
	/**
	 * @param info
	 */
	public IOrderRuslt<Void>  charge(Long orderId,Long mercharId,int cost);
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
	public IOrderRuslt<OrderStateCostDto> cannel(Long orderId, Long bId);
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
	public IOrderRuslt<Long> createDeal(Long orderId, Long bId);
	
	/**
	 * 完成交易
	 * @param orderId
	 * @param dealId
	 * @param cost
	 * @param bId
	 * @return
	 */
	public IOrderRuslt<Void> finishDeal(Long orderId,Long dealId,Integer cost,Long bId);
}
