package cn.com.didi.order.orders.domain;

import java.io.Serializable;

public class OrderRenderDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String stateText;

	public String getStateText() {
		return stateText;
	}

	public void setStateText(String stateText) {
		this.stateText = stateText;
	}
	private OrderDto order;

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}
	
}
