package cn.com.didi.order.impl;

import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.IOrderLifeListener;
import cn.com.didi.order.IOrderService;

/**
 * @author xlm
 * 
 */
public class OrderLifeListenerAdapter implements IOrderLifeListener{

	public void publish(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void autoDispatch(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void reAutoDispatch(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void notifyOrder(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void accept(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void startService(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void finishService(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void charge(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void evaluation(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void timeout(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void cannel(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

	public void noReceiver(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		
	}

}
