package cn.com.didi.order.util;

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
	
	public static final Message ORDER_NOT_PENDING_CHARGE_FINISH_DEAL=newMessage(DomainMessageConstans.CODE_ORDER_NOT_PENDING_CHARGE_FINISH_DEAL);
	
	/**
	 * 交易不存在
	 */
	public static final Message DEAL_NOT_EXIST=newMessage(DomainMessageConstans.CODE_DEAL_NOT_EXIST);
	
	/**
	 * 交易记录金额不相等
	 */
	public static final Message DEAL_ACCOUNT_NOT_EQUAL= newMessage(DomainMessageConstans.CODE_DEAL_ACCOUNT_NOT_EQUAL);
	/**
	 * @param code
	 * @return
	 */
	public static Message newMessage(String code){
		return MessageFactory.creatMessage(code);
	}
}