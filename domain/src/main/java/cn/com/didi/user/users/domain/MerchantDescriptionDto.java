package cn.com.didi.user.users.domain;

public class MerchantDescriptionDto {
	private String lng;	//经度	String				数字格式的字符串
	private String lat;	//纬度	String				数字格式的字符串
	private String description;	//描述	String				
	private String contactInformation;	//联系方式	String				
	private String cname;	//商户名称	String				
	private Long accountId;	//账户ID	Long				账户ID
	private Integer distance;	//距离	int				单位为米
	private String address;	//地址	String				String
	private int orderCount;
	private String merchantEvaluation;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContactInformation() {
		return contactInformation;
	}
	public void setContactInformation(String contactInformation) {
		this.contactInformation = contactInformation;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getDistance() {
		return distance;
	}
	public void setDistance(Integer distance) {
		this.distance = distance;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public String getMerchantEvaluation() {
		return merchantEvaluation;
	}
	public void setMerchantEvaluation(String merchantEvaluation) {
		this.merchantEvaluation = merchantEvaluation;
	}

}
