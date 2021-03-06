package cn.com.didi.order.orders.service.impl;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderNotifyMessageFinder;

@Service
public class OrderNotifyMessageFinderImpl implements IOrderNotifyMessageFinder{
	private MessageDto bTakingMessage;
	private MessageDto cCancelMessage;
	private MessageDto bOrderReassignment;
	@PostConstruct
	public void init(){
		bTakingMessage=new MessageDto();
		bTakingMessage.setTitle("新订单提醒");
		bTakingMessage.setText("您有一个新订单，请及时处理。");
		cCancelMessage=new MessageDto();
		cCancelMessage.setText("您有一个订单被取消，请及时处理。");
		cCancelMessage.setTitle("订单取消提醒");
		bOrderReassignment=sendMessage(null, "订单改派提醒", "您的订单被改派。", false);
	}
	@Override
	public MessageDto findBTakingMessage(OrderDto dto) {
		//sendMessage(info, "您有新的订单", "您有新的订单", dto);
		return bTakingMessage;
	}
	public MessageDto sendMessage(OrderDto  info, String title,String text,boolean bool){
		MessageDto dto=new MessageDto();
		dto.setText(text);
		dto.setTitle(title);
		return dto;
	}
	@Override
	public MessageDto findCTakedMessage(OrderDto dto) {
		//sendMessage(info, "您的订单已接单", "您的订单已接单", false);
        return sendMessage(dto,  "您的订单已被接单", "您的订单已被接单", false);
		//return null;
	}

	@Override
	public MessageDto findBToTakeMessage(OrderDto dto) {
		return bTakingMessage;
	}

	@Override
	public MessageDto findBFinishMessage(OrderDto dto) {
		return sendMessage(dto,  "您的订单师傅完成服务", "您的订单师傅完成服务", false);
		//return null;
	}

	@Override
	public MessageDto findBChargeMessage(OrderDto dto) {
		return sendMessage(dto,  "您的订单师傅发起收费", "您的订单师傅发起收费", false);
		//return null;
	}

	@Override
	public MessageDto findCCancelMessage(OrderDto dto) {
		return sendMessage(dto,  "您的订单已被取消", "您的订单已被取消", false);
		//return null;
	}

	@Override
	public MessageDto findCFinishDealMessage(OrderDto dto) {
		return sendMessage(dto, "您的订单用户已完成付款", "您的订单用户已完成付款", true);
		//return null;
	}
	@Override
	public MessageDto findBStartMessage(OrderDto dto) {
		MessageDto messageDto=new MessageDto();
		messageDto.setText("师傅开始服务");
		messageDto.setTitle("师傅已上门处理您的"+StringUtils.defaultIfEmpty(dto.getCname(),"")+"订单");
		return messageDto;
	}
	@Override
	public MessageDto findBOrderReassignment(OrderDto dto) {
		return bOrderReassignment;
	}

}
