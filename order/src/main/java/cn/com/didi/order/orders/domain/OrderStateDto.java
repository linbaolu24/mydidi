package cn.com.didi.order.orders.domain;

public class OrderStateDto {
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	private String state;
	private Integer cost;
	
}
