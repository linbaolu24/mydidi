package cn.com.didi.platform.order.domain;

public class OrderStringIDJAO {
	private String orderId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getOrderIdLong() {
		return Long.parseLong(orderId);
	}
}
