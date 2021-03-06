package cn.com.didi.order.orders.service;

import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderListBaseDto;

public interface IOrderRenderService {
	/**
	 * @param order
	 * @return
	 */
	public String renderStateText(OrderDto order);
	/**
	 * @param listBase
	 * @return
	 */
	public String renderStateText(OrderListBaseDto listBase);
	/**
	 * 判定交流标志
	 * @param listBase
	 * @return
	 */
	public Integer judgeCommunionFlag(OrderListBaseDto listBase);
}
