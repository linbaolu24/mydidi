package cn.com.didi.domain.domains.wechat;

import java.io.Serializable;

import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;

public class WechatPayContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String	appid;//	应用ID String(32)	是	wx8888888888888888	微信开放平台审核通过的应用APPID
	private String partnerid;//	商户号	 String(32)	是	1900000109	微信支付分配的商户号
	private String	prepayid	;//预支付交易会话ID String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
	private String	packageStr;//	String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
	private String	noncestr;//	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
	private String	timestamp;//	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
	private	String sign;//	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法
	private WechatPayCustomerReturnVo wechatPayCustomerReturnVo;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getPartnerid() {
		return partnerid;
	}
	public void setPartnerid(String partnerid) {
		this.partnerid = partnerid;
	}
	public String getPrepayid() {
		return prepayid;
	}
	public void setPrepayid(String prepayid) {
		this.prepayid = prepayid;
	}
	public String getPackageStr() {
		return packageStr;
	}
	public void setPackageStr(String packageStr) {
		this.packageStr = packageStr;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public WechatPayCustomerReturnVo getWechatPayCustomerReturnVo() {
		return wechatPayCustomerReturnVo;
	}
	public void setWechatPayCustomerReturnVo(WechatPayCustomerReturnVo wechatPayCustomerReturnVo) {
		this.wechatPayCustomerReturnVo = wechatPayCustomerReturnVo;
	}
	
}
