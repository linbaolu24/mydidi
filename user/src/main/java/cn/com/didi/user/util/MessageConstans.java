package cn.com.didi.user.util;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.message.MessageFactory;
import static cn.com.didi.domain.util.DomainMessageConstans.*;
public abstract class MessageConstans {
	
	/**
	 * 用户密码解密错误
	 */
	public static final Message USER_PASSCORD_CODE_ERROR=newMessage(CODE_USER_PASSCORD_CODE_ERROR);
	
	/**
	 * 用户密码解密错误
	 */
	public static final Message USER_PASSCORD_DECODE_ERROR=newMessage(CODE_USER_PASSCORD_DECODE_ERROR);
	/**
	 * 用户不存在
	 */
	public static final Message USER_USER_NOT_EXISTS=newMessage(CODE_USER_USER_NOT_EXISTS);
	
	/**
	 * 用户已存在
	 */
	public static final Message USER_USER__EXISTS=newMessage(CODE_USER_USER_NOT_EXISTS);
	/**
	 * 密码不相等
	 */
	public static final Message USER_PASSOWRD_NOT_EQUAL=newMessage(CODE_USER_PASSOWRD_NOT_EQUAL);
	
	
	
	/**
	 * 验证码无效
	 */
	public static final Message USER_VC_UNVALID=newMessage(CODE_USER_VC_UNVALID);
	
	/**
	 * 请求发送验证码太频繁
	 */
	public static final Message USER_VC_TOO_OFEN=newMessage(CODE_USER_VC_TOO_OFEN);
	
	/**
	 * 验证码不相等
	 */
	public static final Message USER_VC_NOT_EQUAL=newMessage(CODE_USER_VC_NOT_EQUAL);
	/**
	 * 用户被禁用
	 */
	public static final Message USER_STATE_NOT_VALID=newMessage(CODE_USER_STATE_NOT_VALID);
	
	/**未付款不能升级为VIP*/
	public static final Message VIP_NOT_PAY=newMessage(CODE_VIP_NOT_PAY);
	/**
	 * 支付未到账不能升级为VIP
	 */
	public static final Message VIP_PAY_NOT_ARRIVE=newMessage(CODE_VIP_PAY_NOT_ARRIVE);
	/**
	 * @param code
	 * @return
	 */
	public static Message newMessage(String code){
		return MessageFactory.creatMessage(code);
	}
}
