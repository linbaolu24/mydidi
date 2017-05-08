package cn.com.didi.order.orders.service.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.core.filter.IOperationInterceptor;
import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderLifeListener;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * 装饰可发出消息
 * @author xlm
 *
 */
public abstract class AbstractDecoratAbleMessageOrderService implements IOrderService{
	protected List<IOrderLifeListener> listenerList;
	protected IOperationInterceptor<OrderMessageOperation, OrderContextDto, IOrderService> operationInterceptor;
	protected IOperationListener<OrderMessageOperation, OrderContextDto> orderLifeListener;
	protected IOrderService orderService;
	protected IOrderService decoratedOrderService=this;
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
				if(!lis.publish(getDecoratedOrderService(),info,result)){
					return;
				}
			}
		}
	}
	/**
	 * 发出发布通知
	 * @param info
	 */
	public  <T> IOrderRuslt<T> interceptor(OrderMessageOperation operation,OrderContextDto dto){
		IOrderRuslt<T> result= (IOrderRuslt<T>) operationInterceptor.interceptor(operation, dto,getDecoratedOrderService());
		dto.setLastSuccess(operation);
		return result;
	}
	/**
	 * 发出发布通知
	 * @param info
	 */
	public void notifyListener(OrderMessageOperation operation,OrderContextDto dto){
		orderLifeListener.operate(operation, dto);
	}
	/**
	 * 发出发布通知
	 * @param info
	 */
	public OrderContextDto  notifyListener(OrderMessageOperation operation,OrderDto dto){
		OrderContextDto context=new OrderContextDto(dto);
		notifyListener(operation, dto);
		return context;
	}
	
	
}
