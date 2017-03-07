package cn.com.didi.order.orders.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderLifeListener;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * 装饰可发出消息
 * @author xlm
 *
 */
public abstract class AbstractDecoratAbleMessageOrderService implements IOrderService{
	protected List<IOrderLifeListener> listenerList;
	protected IOrderService orderService;
	protected IOrderService decoratedOrderService;
	public IOrderService getOrderService() {
		return orderService;
	}
	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}
	public IOrderService getDecoratedOrderService() {
		return decoratedOrderService;
	}
	public void setDecoratedOrderService(IOrderService decoratedOrderService) {
		this.decoratedOrderService = decoratedOrderService;
	}
	
	public List<IOrderLifeListener> getListenerList() {
		return listenerList;
	}
	public void setListenerList(List<IOrderLifeListener> listenerList) {
		this.listenerList = listenerList;
	}
	/**
	 * 发出发布通知
	 * @param info
	 */
	public void notifyPublish(IOrderInfo info,IOrderRuslt<Void> result){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				if(!lis.publish(this,info,result)){
					return;
				}
			}
		}
	}
	
	public void notifyAutoDispatch(IOrderInfo info,IOrderRuslt<Void> result,IReciverDto reciver){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.autoDispatch(this,info,result,reciver);
			}
		}
	}
	
	public void notifyReAutoDispatch(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.reAutoDispatch(this,info);
			}
		}
	}
	
	
	public void notifyAccept(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.accept(this,info);
			}
		}
	}
	
	public void notifyStartService(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.startService(this,info);
			}
		}
	}
	
	public void notifyFinishService(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.finishService(this,info);
			}
		}
	}
	
	
	public void notifyCharge(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.charge(this,info);
			}
		}
	}
	
	public void notifyNotifyOrder(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.notifyOrder(this,info);
			}
		}
	}
	
	public void notifyEvaluation(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.evaluation(this,info);
			}
		}
	}
	
	
	public void notifyCannel(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.cannel(this,info);
			}
		}
	}
	
	public void notifyNoReceiver(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.noReceiver(this,info);
			}
		}
	}
	
	public void notifyTimeOut(IOrderInfo info){
		if(!CollectionUtils.isEmpty(listenerList)){
			for(IOrderLifeListener lis:listenerList){
				lis.timeout(this,info);
			}
		}
	}
}
