package cn.com.didi.platform.order.domain;

public class OrderStringIDJAO {
	private String orderId;
	private Long accountId;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Long getOrderIdLong() {
		return Long.parseLong(orderId);
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
}
