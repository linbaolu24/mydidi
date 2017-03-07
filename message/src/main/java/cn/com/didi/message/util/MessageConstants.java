package cn.com.didi.message.util;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.message.MessageFactory;
import static cn.com.didi.domain.util.DomainMessageConstans.*;
/**
 * @author xlm
 *
 */
public class MessageConstants {
	
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信内容加密错误
	 */
	public static final Message SM_CONTENT_CODE_ERROR=newMessage(CODE_SM_CONTENT_CODE_ERROR);
	
	
	
	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关返回结果解析失败
	 */
	public static final Message SM_PARSE_RESULT_ERROR=newMessage(CODE_SM_PARSE_RESULT_ERROR);
	
	/**
	 * 短信网关返回发送失败
	 */
	public static final Message SM_GATE_ERROR=newMessage(CODE_SM_GATE_ERROR);
	

	/**
	 * 格式 500 表示错误 100 表示表示短信错误  0001 表示具体错误
	 * 短信网关请求错误
	 */
	public static final Message SM_GATE_REQUEST_ERROR=newMessage(CODE_SM_GATE_REQUEST_ERROR);
	/**
	 * @param code
	 * @return
	 */
	public static Message newMessage(String code){
		return MessageFactory.creatMessage(code);
	}
}
