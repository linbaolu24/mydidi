package cn.com.didi.order.orders.service.impl;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.service.IOrderLifeListener;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * @author xlm
 * 
 */
public class OrderLifeListenerAdapter implements IOrderLifeListener {

	
	public boolean autoDispatch(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean reAutoDispatch(IOrderService service, IOrderInfo info) {
		return true;
	}

	public boolean notifyOrder(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean accept(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean startService(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean finishService(IOrderService service, IOrderInfo info) {
		return true;
	}

	public boolean charge(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean evaluation(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean timeout(IOrderService service, IOrderInfo info) {
		return true;
	}

	public boolean cannel(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean noReceiver(IOrderService service, IOrderInfo info) {
		return true;

	}

	public boolean publish(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result) {
		return true;
	}

	public boolean autoDispatch(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result, IReciverDto reciver) {
		return false;
	}

	@Override
	public boolean notifyNomal(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result) {
		// TODO Auto-generated method stub
		return false;
	}

}
