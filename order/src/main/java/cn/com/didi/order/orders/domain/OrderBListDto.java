package cn.com.didi.order.orders.domain;

public class OrderBListDto extends OrderListBaseDto{
	private String consumerAddress;
	private String consumerName;
	private String cci;
	public String getConsumerAddress() {
		return consumerAddress;
	}

	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}

	public String getConsumerName() {
		return consumerName;
	}

	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}

	public String getCci() {
		return cci;
	}

	public void setCci(String cci) {
		this.cci = cci;
	}
	
	
}
