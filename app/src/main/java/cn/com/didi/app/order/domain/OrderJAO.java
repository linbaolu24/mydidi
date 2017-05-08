package cn.com.didi.app.order.domain;

public class OrderJAO {
	
	public Integer getFlsId() {
		return flsId;
	}
	public void setFlsId(Integer flsId) {
		this.flsId = flsId;
	}
	public Integer getSlsId() {
		return slsId;
	}
	public void setSlsId(Integer slsId) {
		this.slsId = slsId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConsumerAddress() {
		return consumerAddress;
	}
	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}
	public String getCci() {
		return cci;
	}
	public void setCci(String cci) {
		this.cci = cci;
	}
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getCas() {
		return cas;
	}
	public void setCas(String cas) {
		this.cas = cas;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	private Integer flsId;//	一级服务ID
	private Integer slsId;//	二级服务ID
	private String description;//	需求描述
	private String consumerAddress;//	客户地址
	private String cci;//	客户联系方式
	private String consumerName;//	客户名
	private String cas;//	客户地址代码
	private String lng;//	经度
	private String lat;//	纬度
	private String cname;//	服务小项的名字
	private Long merchantAccountId;//商户ID
	private String specialType;
	public Long getMerchantAccountId() {
		return merchantAccountId;
	}
	public void setMerchantAccountId(Long merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}

}
