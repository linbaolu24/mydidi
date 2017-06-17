package cn.com.didi.order.orders.service;

import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.order.orders.domain.OrderDto;

public interface IOrderNotifyMessageFinder {
	/**
	 * 查找商户接单
	 * @param dto
	 * @return
	 */
	public MessageDto findBTakingMessage(OrderDto dto);
	/**
	 * 查找用户被接单
	 * @param dto
	 * @return
	 */
	public MessageDto findCTakedMessage(OrderDto dto);
	/**
	 * 查找通知商户有新的订单
	 * @param dto
	 * @return
	 */
	public MessageDto findBToTakeMessage(OrderDto dto);
	/**
	 * 查找 商户完成订单
	 * @param dto
	 * @return
	 */
	public MessageDto findBStartMessage(OrderDto dto);
	/**
	 * 查找 商户完成订单
	 * @param dto
	 * @return
	 */
	public MessageDto findBFinishMessage(OrderDto dto);
	
	
	/**
	 * 查找 商户发起收费
	 * @param dto
	 * @return
	 */
	public MessageDto findBChargeMessage(OrderDto dto);
	
	/**
	 * 用户取消
	 * @param dto
	 * @return
	 */
	public MessageDto findCCancelMessage(OrderDto dto);
	
	/**用户完成支付
	 * @param dto
	 * @return
	 */
	public MessageDto findCFinishDealMessage(OrderDto dto);
	/**
	 * 查找商户订单被改派
	 * @param dto
	 * @return
	 */
	public MessageDto findBOrderReassignment(OrderDto dto);
	
}
