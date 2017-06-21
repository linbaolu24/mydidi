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
	 * 短信网关返回异常
	 */
	public static final String CODE_SHORTMESSAGE_NETGATE_RETURN="5001000010";
	
	
	/**
	 * 用户被禁用
	 */
	public static final String CODE_USER_STATE_NOT_VALID="5001000011";
	
	
	
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
	 * 短信网关返回失败
	 */
	public static final String CODE_SM_GATE_ERROR="5002000003";
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关请求错误 http返回码非200
	 */
	public static final String CODE_SM_GATE_REQUEST_ERROR="5002000004";
	
	
	/*以下为微信异常*/
	/**
	 * 微信获取access code异常
	 */
	public static final String CODE_WECHAT_GET_ACCESS_CODE_ERROR="5002100001";
	/**
	 * 获取微信用户信息异常
	 */
	public static final String CODE_WECHAT_GET_USER_INFO_ERROR="5002100002";
	
	/**
	 * 获取微信ACCESS_TOKEN_ERROR
	 */
	public static final String CODE_WECHAT_GET_ACCESS_TOKEN_ERROR="5002100003";
	/**
	 * 
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
	/**
	 * 非待付款状态的订单不能创建订单
	 */
	public static final String CODE_ORDER_NOT_PENDING_CHARGE="5003000006";
	
	
	/**
	 * 已接单订单不能超时
	 */
	public static final String CODE_ORDER_HAVE_TIKEING="5003000007";
	
	/**
	 * 交易不存在对应的订单
	 */
	public static final String CODE_ORDER_DEAL_NOT_EXIST="5003000008";
	
	/**
	 * 非待付款订单不能完成交易
	 */
	public static final String CODE_ORDER_NOT_PENDING_CHARGE_FINISH_DEAL="5003000009";
	

	/**
	 * 更新状态时状态发生变更
	 */
	public static final String CODE_ORDER_UPDATE_STATE_CHANGE="5003000010";
	
	/**
	 * 非待评价订单不能评价
	 */
	public static final String CODE_ORDER_NOT_PENDING_EVE="5003000011";
	
	/**
	 * 非待付费状态不能发起收款
	 */
	public static final String CODE_ORDER_NOT_PENDING_CHAREGE_CAN_NOT_CHARGE="5003000012";
	
	/**
	 * 订单ID冲突多次
	 */
	public static final String CODE_ORDER_ID_CONFLICT="5003000013";
	
	/**
	 * 存在未完成的美容美发订单
	 */
	public static final String CODE_ORDER_EXIST_NOT_END_MRMF_ORDER="5003000014";
	
	/**
	 * 不存在押金
	 */
	public static final String CODE_ORDER_NO_DEPOSITE="5003000015";
	
	/**
	 * 不存在体验记录
	 */
	public static final String  CODE_ORDER_NO_USER_EXPERIENCE="5003000016";
	
	/**
	 * 美容美发间隔时间不到
	 */
	public static final String  CODE_ORDER_MRMF_INTERVAL_NOT_ARRIVE="5003000017";
	
	/**
	 *您还未注册会员
	 */
	public static final String  CODE_ORDER_MRMF_NOT_VIP="5003000018";
	
	/**
	 * 每月美容美发数量控制
	 */
	public static final String  CODE_ORDER_MRMF_MONTH_NUM_CONTROLLER="5003000019";
	
	/**
	 * 非待服务订单不能改派
	 */
	public static final String  CODE_ORDER_STATE_NOT_PENDING_SERIVCE="5003000020";
	
	/**
	 * 非自营订单不能改派
	 */
	public static final String  CODE_ORDER_NOT_SELF="5003000021";
	
	
	
	/*以下位交易*/
	/**
	 * 交易记录不存在
	 */
	public static final String CODE_DEAL_NOT_EXIST="5004200001";
	/**
	 * 交易记录金额不相等
	 */
	public static final String CODE_DEAL_ACCOUNT_NOT_EQUAL="5004200002";
	/**
	 * 账户余额不足不能转账
	 */
	public static final String CODE_DEAL_ASSERT_NOT_ENOUGH="5004200003";
	
	/**
	 * 验证阿里签名失败
	 */
	public static final String CODE_DEAL_VERIFY_ALI_SIGN_FAIL="5004000003";
	/**
	 * 阿里返回交易失败
	 */
	public static final String CODE_DEAL_ALI_RESULT_FAIL="5004000004";
	
	/**
	 * 生成支付宝订单支付信息异常
	 */
	public static final String CODE_DEAL_ALI_PAY_ORDERINFO_FAIL="5004000005";
	
	/**
	 * 阿里转账到用户异常
	 */
	public static final String CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION="5004000006";
	
	/**
	 * 阿里转账到用户异常,并且恢复审核状态失败
	 */
	public static final String CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR="5004000007";
	
	/**
	 * 转账成功更新完成提取状态失败
	 */
	public static final String CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR="5004000008";
	
	
	
	
	
	/**微信统一下单异常*/
	public static final String CODE_DEAL_WECHAT_TYXD_ERROR="5004100001";
	
	/**微信统一下单生成请求异常*/
	public static final String CODE_DEAL_WECHAT_TYXD_BUILD_REQUEST_ERROR="5004100002";
	
	/**微信统一下单生成解析返回异常*/
	public static final String CODE_DEAL_WECHAT_TYXD_PARSE_RESPONSE_ERROR="5004100003";
	
	/**微信异步通知支付结果解析异常*/
	public static final String CODE_DEAL_WECHAT_PAY_NOTIFY_PARSE_RESPONSE_ERROR="5004100004";
	
	/**微信异步通知验证签名异常*/
	public static final String CODE_DEAL_WECHAT_PAY_NOTIFY_VERIFGY_SIGN_ERROR="5004100005";
	
	/**微信返回异常*/
	public static final String CODE_DEAL_WECHAT_PAY_NOTIFY_ERROR="5004100006";
	
	/**
	 * 微信转账到用户异常,并且恢复审核状态失败
	 */
	public static final String CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR="5004100007";
	
	/**
	 * 转账成功更新完成提取状态失败
	 */
	public static final String CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR="5004100008";
	
	/**
	 * 微信转账到用户异常
	 */
	public static final String CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION="5004100009";
	
	
	/**微信转账生成请求异常*/
	public static final String CODE_DEAL_WECHAT_TRANSFER_BUILD_REQUEST_ERROR="5004100010";
	/**微信转账解析返回结果异常*/
	public static final String CODE_DEAL_WECHAT_TRANSFER_PARSE_RESPONSE_ERROR="5004100011";
	
	
	/**未付款不能升级为VIP*/
	public static final String CODE_VIP_NOT_PAY="5005000001";
	
	/**
	 * 支付未到账不能升级为VIP
	 */
	public static final String CODE_VIP_PAY_NOT_ARRIVE="5005000002";
	
	
	
	
	
}
