package cn.com.didi.order.impl;

import cn.com.didi.order.IOrderInfo;

/**
 * 订单服务接口
 * @author xlm
 *
 */
public class OrderServiceImpl extends  AbstractDecoratAbleMessageOrderService{
	public void publish(IOrderInfo info) {
		notifyPublish(info);
	}
	public void autoDispatch(IOrderInfo info) {
		notifyAutoDispatch(info);
	}
	public void reAutoDispatch(IOrderInfo info) {
		notifyReAutoDispatch(info);
	}
	

	public void notifyOrder(IOrderInfo info) {
		notifyOrder(info);
	}

	public void accept(IOrderInfo info) {
		notifyAccept(info);
	}

	public void startService(IOrderInfo info) {
		notifyStartService(info);
	}

	public void finishService(IOrderInfo info) {
		notifyFinishService(info);
	}

	public void charge(IOrderInfo info) {
		notifyCharge(info);
	}

	public void evaluation(IOrderInfo info) {
		notifyEvaluation(info);
	}

	public void timeout(IOrderInfo info) {
		notifyTimeOut(info);
	}

	public void cannel(IOrderInfo info) {
		notifyCannel(info);
	}

	public void noReceiver(IOrderInfo info) {
		notifyNoReceiver(info);
	}
} 
