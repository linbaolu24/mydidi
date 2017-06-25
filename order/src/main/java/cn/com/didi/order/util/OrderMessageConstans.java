package cn.com.didi.order.util;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.message.MessageFactory;
import cn.com.didi.domain.util.DomainMessageConstans;

public class OrderMessageConstans {
	
	/**/
	
	/*以下为微信异常*/
	/**
	 * 微信获取access code异常
	 */
	public static final Message WECHAT_GET_ACCESS_CODE_ERROR=newMessage(DomainMessageConstans.CODE_WECHAT_GET_ACCESS_CODE_ERROR);//"5002100001";
	/**
	 * 获取微信用户信息异常
	 */
	public static final Message WECHAT_GET_USER_INFO_ERROR=newMessage(DomainMessageConstans.CODE_WECHAT_GET_USER_INFO_ERROR);//"5002100002";
	
	public static final Message WECHAT_GET_ACCESS_TOKEN_ERROR=newMessage(DomainMessageConstans.CODE_WECHAT_GET_ACCESS_TOKEN_ERROR);//"5002100003";
	
	
	
	
	
	
	
   /* 以下为订单*/	
	/**
	 * 订单自动派单无师傅
	 */
	public static final Message ORDER_AUTO_DIS_NO_MASTER=newMessage(DomainMessageConstans.CODE_ORDER_AUTO_DIS_NO_MASTER);
	
	
	/**
	 * 订单已完成不能进行该操作
	 */
	public static final Message ORDER_FINISHED=newMessage(DomainMessageConstans.CODE_ORDER_FINISHED);
	
	/**
	 * 不存在该订单
	 */
	public static final Message ORDER_NOT_EXIST=newMessage(DomainMessageConstans.CODE_ORDER_NOT_EXIST);
	/**
	 * 不属于该用户的订单
	 */
	public static final Message ORDER_ACCOUTID_NOT_EQUAL=newMessage(DomainMessageConstans.CODE_ORDER_ACCOUTID_NOT_EQUAL);
	
	/**
	 * 已完成服务的订单不能取消
	 */
	public static final Message ORDER_SERVICE_FINISH_CANNOT_CANNEL=newMessage(DomainMessageConstans.CODE_ORDER_SERVICE_FINISH_CANNOT_CANNEL);

	/**
	 * 非待付款状态订单不能创建交易
	 */
	public static final Message ORDER_NOT_PENDING_CHARGE=newMessage(DomainMessageConstans.CODE_ORDER_NOT_PENDING_CHARGE);
	
	
	/**
	 * 订单已经接单 不需要超时
	 */
	public static final Message ORDER_HAVE_TIKEING=newMessage(DomainMessageConstans.CODE_ORDER_HAVE_TIKEING);
	
	
	
	/**
	 * 交易不存在对应的订单
	 */
	public static final Message ORDER_DEAL_NOT_EXIST=newMessage(DomainMessageConstans.CODE_ORDER_DEAL_NOT_EXIST);
	/**
	 * 非待付款订单不能完成交易
	 */
	public static final Message ORDER_NOT_PENDING_CHARGE_FINISH_DEAL=newMessage(DomainMessageConstans.CODE_ORDER_NOT_PENDING_CHARGE_FINISH_DEAL);
	/**
	 * 更新状态时状态发生变更
	 */
	public static final Message ORDER_UPDATE_STATE_CHANGE=newMessage(DomainMessageConstans.CODE_ORDER_UPDATE_STATE_CHANGE);
	/**非待评价订单不能评价*/
	public static final Message ORDER_NOT_PENDING_EVE=newMessage(DomainMessageConstans.CODE_ORDER_NOT_PENDING_EVE);
	
	
	/**
	 * 非待付费状态不能发起收款
	 */
	public static final Message ORDER_NOT_PENDING_CHAREGE_CAN_NOT_CHARGE=newMessage(DomainMessageConstans.CODE_ORDER_NOT_PENDING_CHAREGE_CAN_NOT_CHARGE);
	/**
	 * 订单ID冲突多次
	 */
	public static final Message ORDER_ID_CONFLICT=newMessage(DomainMessageConstans.CODE_ORDER_ID_CONFLICT);//="5003000013";
	
	
	
	/**
	 * 存在未完成的美容美发订单
	 */
	public static final Message ORDER_EXIST_NOT_END_MRMF_ORDER =newMessage(DomainMessageConstans.CODE_ORDER_EXIST_NOT_END_MRMF_ORDER);//="5003000014";
	
	/**
	 * 不存在押金
	 */
	public static final Message ORDER_NO_DEPOSITE =newMessage(DomainMessageConstans.CODE_ORDER_NO_DEPOSITE);//="5003000015";
	/**
	 * 不存在体验记录
	 */
	public static final Message ORDER_NO_USER_EXPERIENCE =newMessage(DomainMessageConstans.CODE_ORDER_NO_USER_EXPERIENCE);//="5003000016";
	
	/**
	 * 美容美发间隔时间不到
	 */
	public static final Message ORDER_MRMF_INTERVAL_NOT_ARRIVE =newMessage(DomainMessageConstans.CODE_ORDER_MRMF_INTERVAL_NOT_ARRIVE);//="5003000017";
	
	
	/**
	 *  您还未注册会员
	 */
	public static final Message ORDER_MRMF_NOT_VIP =newMessage(DomainMessageConstans.CODE_ORDER_MRMF_NOT_VIP);//="5003000018"; 
	
	/**
	 * 每月美容美发数量控制
	 */
	public static final Message ORDER_MRMF_MONTH_NUM_CONTROLLER =newMessage(DomainMessageConstans.CODE_ORDER_MRMF_MONTH_NUM_CONTROLLER);
	
	
	

	/**
	 * 非待服务订单不能改派
	 */
	public static final Message  ORDER_STATE_NOT_PENDING_SERIVCE=newMessage(DomainMessageConstans.CODE_ORDER_STATE_NOT_PENDING_SERIVCE);//"5003000020";
	
	/**
	 * 非自营订单不能改派
	 */
	public static final Message  ORDER_NOT_SELF=newMessage(DomainMessageConstans.CODE_ORDER_NOT_SELF);;//"5003000021";
	
	/**
	 * 订单通知无师傅
	 */
	public static final Message ORDER_NOTIFY_DIS_NO_MASTER=newMessage(DomainMessageConstans.CODE_ORDER_NOTIFY_DIS_NO_MASTER);
	
	
	/*以下为交易*/
	/**
	 * 交易不存在
	 */
	public static final Message DEAL_NOT_EXIST=newMessage(DomainMessageConstans.CODE_DEAL_NOT_EXIST);
	
	/**
	 * 交易记录金额不相等
	 */
	public static final Message DEAL_ACCOUNT_NOT_EQUAL= newMessage(DomainMessageConstans.CODE_DEAL_ACCOUNT_NOT_EQUAL);
	
	/**
	 * 账户余额不足不能转账
	 */
	public static final Message DEAL_ASSERT_NOT_ENOUGH= newMessage(DomainMessageConstans.CODE_DEAL_ASSERT_NOT_ENOUGH);
	
	
	/**
	 * 交易状态已发生变化
	 */
	public static final Message DEAL_STATE_CHANGE= newMessage(DomainMessageConstans.CODE_DEAL_STATE_CHANGE);;//"5004200004";
	
	/**
	 * 获取用户锁失败
	 */
	public static final Message DEAL_GET_USER_LOCK_FAIL= newMessage(DomainMessageConstans.CODE_DEAL_GET_USER_LOCK_FAIL);//"5004200005";
	
	
	/**已经成功状态的交易不能更新为失败
	 */
	public static final Message DEAL_SUCCESS_CAN_NOT_FAIL=newMessage(DomainMessageConstans.CODE_DEAL_SUCCESS_CAN_NOT_FAIL);//="5004200006";
	
	/**
	 *将交易状态为失败出错了
	 */
	public static final  Message DEAL_UPDATE_FAIL_ERROR=newMessage(DomainMessageConstans.CODE_DEAL_UPDATE_FAIL_ERROR);//"5004200007";
	
	/**
	 * 验证阿里签名失败
	 */
	public static final Message DEAL_VERIFY_ALI_SIGN_FAIL=newMessage(DomainMessageConstans.CODE_DEAL_VERIFY_ALI_SIGN_FAIL);
	/**
	 * 阿里返回交易失败
	 */
	public static final Message DEAL_ALI_RESULT_FAIL=newMessage(DomainMessageConstans.CODE_DEAL_ALI_RESULT_FAIL);
	
	/**
	 * 生成支付宝订单支付信息异常
	 */
	public static final Message DEAL_ALI_PAY_ORDERINFO_FAIL= newMessage(DomainMessageConstans.CODE_DEAL_ALI_PAY_ORDERINFO_FAIL);
	
	/**
	 * 阿里转账到用户异常
	 */
	public static final Message DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION=newMessage(DomainMessageConstans.CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION);
	
	
	/**
	 * 阿里转账到用户异常
	 */
	public static final Message DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR=newMessage(DomainMessageConstans.CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR);
	
	/**
	 * 转账成功更新完成提取状态失败
	 */
	public static final Message DEAL_ALI_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR=newMessage(DomainMessageConstans.CODE_DEAL_ALI_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR);
	
	/**
	 * 微信统一下单异常
	 */
	public static final Message DEAL_WECHAT_TYXD_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TYXD_ERROR);
	
	
	
	/**微信统一下单生成请求异常*/
	public static final Message DEAL_WECHAT_TYXD_BUILD_REQUEST_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TYXD_BUILD_REQUEST_ERROR);
	
	/**微信统一下单生成解析返回异常*/
	public static final Message DEAL_WECHAT_TYXD_PARSE_RESPONSE_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TYXD_PARSE_RESPONSE_ERROR);
	/**微信异步通知支付结果解析异常*/
	public static final Message DEAL_WECHAT_PAY_NOTIFY_PARSE_RESPONSE_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_PAY_NOTIFY_PARSE_RESPONSE_ERROR);
	/**微信异步通知验证签名异常*/
	public static final Message DEAL_WECHAT_PAY_NOTIFY_VERIFGY_SIGN_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_PAY_NOTIFY_VERIFGY_SIGN_ERROR);
	
	
	
	/**微信支付异常*/
	public static final Message DEAL_WECHAT_PAY_NOTIFY_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_PAY_NOTIFY_ERROR);
	
	
	
	/**
	 * 微信转账到用户异常,并且恢复审核状态失败
	 */
	public static final Message DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR=newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR);
	
	/**
	 * 转账成功更新完成提取状态失败
	 */
	public static final Message DEAL_WECHAT_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR=newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR);
	
	/**
	 * 微信转账到用户异常
	 */
	public static final Message DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION=newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION);
	
	
	/**微信转账生成请求异常*/
	public static final Message DEAL_WECHAT_TRANSFER_BUILD_REQUEST_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TRANSFER_BUILD_REQUEST_ERROR);
	/**
	 * 微信转账解析返回结果异常
	 */
	public static final Message DEAL_WECHAT_TRANSFER_PARSE_RESPONSE_ERROR= newMessage(DomainMessageConstans.CODE_DEAL_WECHAT_TRANSFER_PARSE_RESPONSE_ERROR);
	
	
	
	
	
	/**
	 * @param code
	 * @return
	 */
	public static Message newMessage(String code){
		return MessageFactory.creatMessage(code);
	}
}
