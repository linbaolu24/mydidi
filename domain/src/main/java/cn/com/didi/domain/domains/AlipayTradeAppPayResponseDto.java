package cn.com.didi.domain.domains;

public class AlipayTradeAppPayResponseDto {
	private String alipay_trade_app_pay_response;
	private String sign;
	private String sign_type;
	public String getAlipay_trade_app_pay_response() {
		return alipay_trade_app_pay_response;
	}
	public void setAlipay_trade_app_pay_response(String alipay_trade_app_pay_response) {
		this.alipay_trade_app_pay_response = alipay_trade_app_pay_response;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	@Override
	public String toString() {
		return "AlipayTradeAppPayResponseDto [alipay_trade_app_pay_response=" + alipay_trade_app_pay_response
				+ ", sign=" + sign + ", sign_type=" + sign_type + "]";
	}
	
}
