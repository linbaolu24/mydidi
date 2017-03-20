package cn.com.didi.domain.util;

public class AlipayConstants {
	public static final String APP_ID = "app_id";// String 是 32 支付宝分配给开发者的应用ID
													// 2014072300007148
	public static final String METHOD = "method";// String 是 128 接口名称
													// alipay.trade.app.pay
	public static final String FORMAT = "format";// String 否 40 仅支持JSON JSON
	public static final String CHARSET = "charset";// String 是 10
													// 请求使用的编码格式，如utf-8,gbk,gb2312等
													// utf-8
	public static final String SIGN_TYPE = "sign_type";// String 是 10
														// 商户生成签名字符串所使用的签名算法类型，目前支持RSA2和RSA，推荐使用RSA2
														// RSA2
	public static final String SIGN = "sign"; // String 是 256 商户请求参数的签名串，详见签名
												// 详见示例
	public static final String TIMESTAMP = "timestamp";// String 是 19
														// 发送请求的时间，格式"yyyy-MM-dd
														// HH:mm:ss" 2014-07-24
														// 03:07:50
	public static final String VERSION = "version";// String 是 3 调用的接口版本，固定为：1.0
													// 1.0
	public static final String NOTIFY_URL = "notify_url";// String 是 256
															// 支付宝服务器主动通知商户服务器里指定的页面http/https路径。建议商户使用https
															// https://api.xx.com/receive_notify.htm
	public static final String BIZ_CONTENT = "biz_content";// String 是 -
															// 业务请求参数的集合，最大长度不限，除公共参数外所有请求参数都必须放在这个参数中传递，具体参照各产品快速接入文档
	
	
	
	
	
	public static final String BODY = "body";//	String	否	128	对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。	Iphone6 16G
	public static final String SUBJECT= "subject";//	S	String	是	256	商品的标题/交易标题/订单标题/订单关键字等。	大乐透
	public static final String OUT_TRADE_NO= "out_trade_no";//	S	String	是	64	商户网站唯一订单号	70501111111S001111119
	public static final String TIMEOUT_EXPRESS= "timeout_express";//	S	String	否	6	设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭。当用户进入支付宝收银台页面（不包括登录页面），会触发即刻创建支付宝交易，此时开始计时。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m。	90m
	public static final String TOTAL_AMOUNT= "total_amount";//	S	String	是	9	订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]	9.00
	public static final String SELLER_ID= "seller_id";//	S	String	否	16	收款支付宝用户ID。 如果该值为空，则默认为商户签约账号对应的支付宝用户ID	2088102147948060
	public static final String PRODUCT_CODE= "product_code";//	S	String	是
	
	
	public static final String RSA2="RSA2";
	public static final String RSA="RSA";
	public static final String DEFAULT_CHARSET="UTF-8";
	
	public static final String ALIPAY_TRADE_APP_PAY_RESPONSE="alipay_trade_app_pay_response";
}
