package cn.com.didi.domain.util;

/**
 * @author xlm
 *
 */
public class DomainMessageConstans {
	
	/**
	 * 格式 500 表示错误 000 表示系统 0001 表示具体错误
	 * 系统错误
	 */
	public static final String CODE_SYSTEM_ERROR="5000000000";
	
	/**
	 * 格式 500 表示错误 000 表示系统 0001 表示具体错误
	 * http请求状态非200
	 */
	public static final String CODE_SYSTEM_HTTP_STATU_NOT_200="5000000001";
	
	/**
	 * 格式 500 表示错误 000 表示系统 0001 表示具体错误
	 * http请求状态非200
	 */
	public static final String CODE_SYSTEM_HTTP_ERROR="5000000002";
	
	/**
	 * 格式 500 表示错误 000 表示登录 0001 表示具体错误
	 * 参数不合法
	 */
	public static final String CODE_PARAM_ILLEGAL_ERROR="5000010000";

	/**
	 * 格式 500 表示错误 100 表示用户 0001 表示具体错误
	 * 用户密码加密错误
	 */
	public static final String CODE_USER_PASSCORD_CODE_ERROR="5001000001";

	
	/**
	 * 用户密码解密错误
	 */
	public static final String CODE_USER_PASSCORD_DECODE_ERROR="5001000002";
	
	
	
	/**
	 * 用户不存在
	 */
	public static final String CODE_USER_USER_NOT_EXISTS="5001000003";
	
	
	/**
	 * 用户已存在
	 */
	public static final String CODE_USER_USER__EXISTS="5001000004";
	
	/**
	 * 密码不相等
	 */
	public static final String CODE_USER_PASSOWRD_NOT_EQUAL="5001000005";
	/**
	 * 验证码无效
	 */
	public static final String  CODE_USER_VC_UNVALID="5001000006";
	/**
	 * 请求发送验证码太频繁
	 * 
	 */
	public static final String  CODE_USER_VC_TOO_OFEN="5001000007";
	/**
	 * 验证码不相等
	 */
	public static final String CODE_USER_VC_NOT_EQUAL="5001000008";
	/**
	 * 用户未登录
	 */
	public static final String CODE_USER_NOT_LOGIN="5001000009";
	/**
	 * 用户未登录
	 */
	public static final String CODE_SHORTMESSAGE_NETGATE_RETURN="5001000010";
	
	
	
	
	
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信内容加密错误
	 */
	public static final String CODE_SM_CONTENT_CODE_ERROR="5002000001";
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关返回结果解析失败
	 */
	public static final String CODE_SM_PARSE_RESULT_ERROR="5002000002";
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关返回结果解析失败
	 */
	public static final String CODE_SM_GATE_ERROR="5002000003";
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关请求错误
	 */
	public static final String CODE_SM_GATE_REQUEST_ERROR="5002000003";
	
	
	/**
	 * 订单自动派单无师傅
	 */
	public static final String CODE_ORDER_AUTO_DIS_NO_MASTER="5003000001";
	
	/**
	 * 订单已完成不能进行该操作
	 */
	public static final String CODE_ORDER_FINISHED="5003000002";
	
	/**
	 * 不存在该订单
	 */
	public static final String CODE_ORDER_NOT_EXIST="5003000003";
	/**
	 * 不属于该用户的订单
	 */
	public static final String CODE_ORDER_ACCOUTID_NOT_EQUAL="5003000004";
	
	/**
	 * 已完成服务的订单不能取消
	 */
	public static final String CODE_ORDER_SERVICE_FINISH_CANNOT_CANNEL="5003000005";
}
