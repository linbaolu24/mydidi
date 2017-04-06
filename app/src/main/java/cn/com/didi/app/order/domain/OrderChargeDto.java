package cn.com.didi.app.order.domain;

public class OrderChargeDto extends OrderIDJAO{
	private Integer cost	;
	private String cment	;
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getCment() {
		return cment;
	}
	public void setCment(String cment) {
		this.cment = cment;
	}
	

}
