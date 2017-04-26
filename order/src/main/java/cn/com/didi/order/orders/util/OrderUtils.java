package cn.com.didi.order.orders.util;

import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.order.orders.domain.OrderDto;

public class OrderUtils {
	public static void populate(OrderDto dto,IMerchantDto merchantDto){
		dto.setMci(merchantDto.getMci());
		dto.setMasterName(merchantDto.getMasterName());
		dto.setMlat(merchantDto.getLat());
		dto.setMlng(merchantDto.getLng());
	}
}
