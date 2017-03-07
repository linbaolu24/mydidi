package cn.com.didi.order.orders.util;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.message.MessageFactory;
import cn.com.didi.domain.util.DomainMessageConstans;

public class OrderMessageConstans {
	
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
	 * @param code
	 * @return
	 */
	public static Message newMessage(String code){
		return MessageFactory.creatMessage(code);
	}
}
