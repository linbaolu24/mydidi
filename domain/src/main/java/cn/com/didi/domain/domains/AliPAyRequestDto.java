package cn.com.didi.domain.domains;

/**
 * @author xlm
 *
 */
public class AliPAyRequestDto {
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getOrderInfo() {
		return orderInfo;
	}
	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}
	private String partnerId;//\"2088521250419454\"&"
	private Long dealId;
	private String subject;
	private String body;
	private Integer cost;
	private String notifyUrl;
	private String charset;
	private String sign;
	private String signType;
	private String orderInfo;
	
    //"out_trade_no=\"2017031816584158415946\"&subject=\"\U901a\U8baf\U5f55\U98ce\U9669\U68c0\U6d4b\"&body=\"\U901a\U8baf\U5f55\U98ce\U9669\U68c0\U6d4b\"&total_fee=\"0.01\"&it_b_pay=\"4m\"&notify_url=\"http%3A%2F%2Fpay.chengniu.com%3A8100%2FALIPAY%2F300%2F01%2FkeyNotify.do\"&service=\"mobile.securitypay.pay\"&_input_charset=\"utf-8\"&payment_type=\"1\"&seller_id=\"app@kongapi.com\"&sign=\"hT4n66%2B7hSsZS9gm2j%2FRDdIItnyMZXY3T%2Fe7UFu%2Fei3BtcA5O23ynXGvp6B1da5bHe9vO7QB38q3YDGsbgvTif%2Fei9L3pl%2Fqz9pj%2BtW2vDb4%2BnrWbSLA3Uzc2WjOjgGwO2Tz99DF6a%2BI%2B2s54QftjGs6Cvyr3EW%
}
