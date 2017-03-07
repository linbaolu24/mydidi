package cn.com.didi.order.orders.service.impl;

import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.service.IOrderService;

public class DispatchServiceImpl extends  OrderLifeListenerAdapter{

	public void publish(IOrderService service, IOrderInfo info) {
		/*if(info.selfBusiness()){//如果自营业务发出自动派单
			service.autoDispatch(info);
		}else{
			service.notifyOrder(info);//如果非自营业务发出抢单
		}*/
	}
}
