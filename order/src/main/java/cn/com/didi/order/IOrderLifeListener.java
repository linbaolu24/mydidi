package cn.com.didi.order;

/**
 * @author xlm
 *
 */
public interface IOrderLifeListener {
	/**
	 * 发布订单
	 */
	public void publish(IOrderService service, IOrderInfo info);

	/**
	 * 自动派单,state 为{@code ORDER_STATE_PUBLISH 可以自动派单}
	 */
	public void autoDispatch(IOrderService service, IOrderInfo info);

	/**
	 * 尝试重新派单,状态位通知或发布中的可以可
	 */
	public void reAutoDispatch(IOrderService service, IOrderInfo info);

	/**
	 * 发出抢单通知
	 */
	public void notifyOrder(IOrderService service, IOrderInfo info);

	/**
	 * 接受订单
	 */
	public void accept(IOrderService service, IOrderInfo info);

	/**
	 * 开始服务
	 */
	public void startService(IOrderService service, IOrderInfo info);

	/**
	 * @param info
	 */
	public void finishService(IOrderService service, IOrderInfo info);

	/**
	 * @param info
	 */
	public void charge(IOrderService service, IOrderInfo info);

	/**
	 * 评价
	 */
	public void evaluation(IOrderService service, IOrderInfo info);

	/**
	 * 订单超时
	 */
	public void timeout(IOrderService service, IOrderInfo info);

	/**
	 * 取消订单
	 */
	public void cannel(IOrderService service, IOrderInfo info);

	/**
	 * 没有接单人
	 */
	public void noReceiver(IOrderService service, IOrderInfo info);

}
