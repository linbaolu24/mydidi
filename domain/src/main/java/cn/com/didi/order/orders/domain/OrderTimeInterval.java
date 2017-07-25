package cn.com.didi.order.orders.domain;

import cn.com.didi.domain.query.TimeInterval;

/**
 * @author xlm
 *
 */
public class OrderTimeInterval extends TimeInterval{
	/**
	 * 客户注册手机号
	 */
	private String crp;
	/**
	 * 商户注册手机号
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
