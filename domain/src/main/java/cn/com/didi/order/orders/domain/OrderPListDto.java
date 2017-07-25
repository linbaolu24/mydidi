package cn.com.didi.order.orders.domain;

public class OrderPListDto extends OrderListDto{
	/**
	 * 客户注册手机
	 */
	private String crp;
	/**
	 * 商户注册手机
	 */
	private String mrp;
	public String getCrp() {
		return crp;
	}
	public void setCrp(String crp) {
		this.crp = crp;
	}
	public String getMrp() {
		return mrp;
	}
	public void setMrp(String mrp) {
		this.mrp = mrp;
	}
}
