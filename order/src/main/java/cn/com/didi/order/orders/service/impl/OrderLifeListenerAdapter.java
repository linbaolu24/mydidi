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

	@Override
	public boolean publish(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean autoDispatch(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result, IReciverDto reciver) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean reAutoDispatch(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean notifyOrder(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean accept(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean startService(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean finishService(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean charge(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean evaluation(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean timeout(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean cannel(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean noReceiver(IOrderService service, IOrderInfo info) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean notifyNomal(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
