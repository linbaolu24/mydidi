package cn.com.didi.order.orders.domain;

import java.util.Date;

public class OrderListDto { 
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Date getOct() {
		return oct;
	}
	public void setOct(Date oct) {
		this.oct = oct;
	}
	public Date getOrt() {
		return ort;
	}
	public void setOrt(Date ort) {
		this.ort = ort;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getSlsId() {
		return slsId;
	}
	public void setSlsId(String slsId) {
		this.slsId = slsId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getMci() {
		return mci;
	}
	public void setMci(String mci) {
		this.mci = mci;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}
	public String getBusinessCharge() {
		return businessCharge;
	}
	public void setBusinessCharge(String businessCharge) {
		this.businessCharge = businessCharge;
	}
	
	private String orderId;
	private String state;
	private String slsId;
	private String cname;
	private Date ort;
	private Date oct;
	private String masterName;
	private String mci;
	private String lat;
	private String lng;
	private String businessCategory; 
	private String businessCharge;
	private Integer cost;
	
	
}
