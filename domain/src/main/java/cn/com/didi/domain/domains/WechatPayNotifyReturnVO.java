package cn.com.didi.domain.domains;

public class WechatPayNotifyReturnVO extends WechatPayCustomerReturnVo{
	private String bank_type;//付款银行
	private String sign;
	private String openid;//用户在商户appid下的唯一标识
	private String trade_type;
	private String total_fee;
	private String cash_fee;//现金支付金额订单现金支付金额，详见支付金额
	private String transaction_id;//	微信支付订单号
	private String out_trade_no;//	商户系统的订单号，与请求一致。
	private String time_end;//支付完成时间，
	private String appid;//应用ID	appid	是	String(32)	wx8888888888888888	微信开放平台审核通过的应用APPID
	private String source;
	private String fee_type;
	private String is_subscribe;
	private String mch_id;
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(String cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTime_end() {
		return time_end;
	}
	public void setTime_end(String time_end) {
		this.time_end = time_end;
	}
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	@Override
	public String toString() {
		return super.toString()+"WechatPayNotifyReturnVO [bank_type=" + bank_type + ", sign=" + sign + ", openid=" + openid
				+ ", trade_type=" + trade_type + ", total_fee=" + total_fee + ", cash_fee=" + cash_fee
				+ ", transaction_id=" + transaction_id + ", out_trade_no=" + out_trade_no + ", time_end=" + time_end
				+ ", appid=" + appid + "]";
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getIs_subscribe() {
		return is_subscribe;
	}
	public void setIs_subscribe(String is_subscribe) {
		this.is_subscribe = is_subscribe;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	
}
