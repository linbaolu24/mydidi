package cn.com.didi.order;

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
	public void publish(IOrderInfo info);
	/**
	 * 自动派单,state 为{@code ORDER_STATE_PUBLISH 可以自动派单}
	 */
	public void autoDispatch(IOrderInfo info);
	/**
	 * 尝试重新派单,状态位通知或发布中的可以可
	 */
	public void reAutoDispatch(IOrderInfo info);
	/**
	 * 发出抢单通知
	 */
	public void notifyOrder(IOrderInfo info);
	/**
	 * 接受订单
	 */
	public void accept(IOrderInfo info);
	/**
	 * 开始服务
	 */
	public void startService(IOrderInfo info);
	/**
	 * @param info
	 */
	public void finishService(IOrderInfo info);
	/**
	 * @param info
	 */
	public void charge(IOrderInfo info);
	/**
	 * 评价
	 */
	public void evaluation(IOrderInfo info);
	/**
	 * 订单超时
	 */
	public void timeout(IOrderInfo info);
	/**
	 * 取消订单
	 */
	public void cannel(IOrderInfo info);
	/**
	 * 没有接单人
	 */
	public void noReceiver(IOrderInfo info);
	/**
	 * 设置已装饰的service
	 * @param service
	 */
	public void setDecoratedOrderService(IOrderService service);
}
