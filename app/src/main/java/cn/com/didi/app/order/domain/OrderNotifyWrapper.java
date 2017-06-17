package cn.com.didi.app.order.domain;

import cn.com.didi.order.orders.domain.OrderNotifyDto;

public class OrderNotifyWrapper {
	private OrderNotifyDto wrapped;

	public OrderNotifyWrapper(OrderNotifyDto wrapped) {
		super();
		this.wrapped = wrapped;
	}

	public Long getOrderId() {
		return wrapped.getOrderId();
	}

	public void setOrderId(Long orderId) {
		wrapped.setOrderId(orderId);
	}

	public String getCname() {
		return wrapped.getCname();
	}

	public void setCname(String cname) {
		wrapped.setCname(cname);
	}

	public String getCci() {
		return wrapped.getCci();
	}

	public void setCci(String cci) {
		wrapped.setCci(cci);
	}

	public String getConsumerAddress() {
		return wrapped.getConsumerAddress();
	}

	public void setConsumerAddress(String consumerAddress) {
		wrapped.setConsumerAddress(consumerAddress);
	}

	public String getConsumerName() {
		return wrapped.getConsumerName();
	}

	public void setConsumerName(String consumerName) {
		wrapped.setConsumerName(consumerName);
	}
	
}
