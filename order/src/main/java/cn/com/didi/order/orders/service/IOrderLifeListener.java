package cn.com.didi.order.orders.service;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * @author xlm
 *
 */
public interface IOrderLifeListener {
	/**
	 * 发布订单
	 */
	public  boolean publish(IOrderService service, IOrderInfo info,IOrderRuslt<Void> result);

	/**
	 * 自动派单,state 为{@code ORDER_STATE_PUBLISH 可以自动派单}
	 */
	public boolean autoDispatch(IOrderService service, IOrderInfo info,IOrderRuslt<Void> result,IReciverDto reciver);

	/**
	 * 尝试重新派单,状态位通知或发布中的可以可
	 */
	public boolean reAutoDispatch(IOrderService service, IOrderInfo info);

	/**
	 * 发出抢单通知
	 */
	public boolean notifyOrder(IOrderService service, IOrderInfo info);

	/**
	 * 接受订单
	 */
	public boolean accept(IOrderService service, IOrderInfo info);

	/**
	 * 开始服务
	 */
	public boolean startService(IOrderService service, IOrderInfo info);

	/**
	 * @param info
	 */
	public boolean finishService(IOrderService service, IOrderInfo info);

	/**
	 * @param info
	 */
	public boolean charge(IOrderService service, IOrderInfo info);

	/**
	 * 评价
	 */
	public boolean evaluation(IOrderService service, IOrderInfo info);

	/**
	 * 订单超时
	 */
	public boolean timeout(IOrderService service, IOrderInfo info);

	/**
	 * 取消订单
	 */
	public boolean cannel(IOrderService service, IOrderInfo info);

	/**
	 * 没有接单人
	 */
	public boolean noReceiver(IOrderService service, IOrderInfo info);
	
	
	/**
	 * 通用通知
	 * @param service
	 * @param info
	 * @param result
	 * @return
	 */
	public boolean notifyNomal(IOrderService service,IOrderInfo info,IOrderRuslt<Void> result);

}
