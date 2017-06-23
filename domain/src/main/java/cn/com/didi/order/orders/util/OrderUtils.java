package cn.com.didi.order.orders.util;

import java.util.Date;

import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.util.State;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderNotifyDto;

public class OrderUtils {
	public static void populate(OrderDto dto,IMerchantDto merchantDto){
		dto.setMci(merchantDto.getMci());
		dto.setMasterName(merchantDto.getMasterName());
		dto.setMlat(merchantDto.getLat());
		dto.setMlng(merchantDto.getLng());
		dto.setMerchantAccountId(merchantDto.getMerchantId());
	}
	public static void toOrderNotifyDto(OrderDto dto,OrderNotifyDto notifyDto){
		notifyDto.setCas(dto.getCas());
		notifyDto.setCci(dto.getCci());
		notifyDto.setCname(dto.getCname());
		notifyDto.setConsumerAccountId(dto.getConsumerAccountId());
		notifyDto.setConsumerAddress(dto.getConsumerAddress());
		notifyDto.setConsumerName(dto.getConsumerName());
		notifyDto.setDescription(dto.getDescription());
		notifyDto.setFlsId(dto.getFlsId());
		notifyDto.setSlsId(dto.getSlsId());
		notifyDto.setLat(dto.getLat());
		notifyDto.setLng(dto.getLng());
		notifyDto.setMerchantAccountId(dto.getMerchantAccountId());
		notifyDto.setOrderId(dto.getOrderId());
		notifyDto.setValidFlag(State.VALID.getState());
		notifyDto.setCreateTime(new Date());
	}
}
