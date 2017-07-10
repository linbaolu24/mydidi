package cn.com.didi.order.orders.domain;

import java.util.Date;

/**
 * @author xlm
 *
 */
public class OrderListBaseDto {
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
	
	private Long orderId;
	private String state;
	private String slsId;
	private String cname;
	private Date ort;
	private Date oct;
	private Date sst;
	private String lat;
	private String lng;
	private String businessCategory; 
	private String businessCharge;
	private Integer cost;
	private String ryUserId;
	private Integer communionFlag;
	
	/**
	 * 状态文本
	 */
	private String stateText;
	  /**
     * 客户账户id
     */
    private Long consumerAccountId;

    /**
     * 商户账户id
     */
    private Long merchantAccountId;
    /**
     * 特殊标志
     */
    private String specialType;

	public Long getConsumerAccountId() {
		return consumerAccountId;
	}
	public void setConsumerAccountId(Long consumerAccountId) {
		this.consumerAccountId = consumerAccountId;
	}
	public Long getMerchantAccountId() {
		return merchantAccountId;
	}
	public void setMerchantAccountId(Long merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getStateText() {
		return stateText;
	}
	public void setStateText(String stateText) {
		this.stateText = stateText;
	}
	public String getRyUserId() {
		return ryUserId;
	}
	public void setRyUserId(String ryUserId) {
		this.ryUserId = ryUserId;
	}
	public Integer getCommunionFlag() {
		return communionFlag;
	}
	public void setCommunionFlag(Integer communionFlag) {
		this.communionFlag = communionFlag;
	}
	public Date getSst() {
		return sst;
	}
	public void setSst(Date sst) {
		this.sst = sst;
	}
	
	
}
