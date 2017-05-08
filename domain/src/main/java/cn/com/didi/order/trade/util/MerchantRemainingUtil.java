package cn.com.didi.order.trade.util;

import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;

public class MerchantRemainingUtil {
	public static void copyProperty(MerchantDayRemainingDto source,MerchantDayRemainingDto dest){
		dest.setAccountId(source.getAccountId());
		dest.setPat(source.getPat());
		dest.setCategory(source.getCategory());
		dest.setDaytime(source.getDaytime());
		dest.setRemaining(source.getRemaining());
	}
	
}
