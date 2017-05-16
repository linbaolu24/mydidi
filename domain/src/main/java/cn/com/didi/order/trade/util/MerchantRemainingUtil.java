package cn.com.didi.order.trade.util;

import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;

public class MerchantRemainingUtil {
	public static void copyProperty(MerchantDayRemainingDto source,MerchantDayRemainingDto dest){
		dest.setAccountId(source.getAccountId());
		dest.setPat(source.getPat());
		dest.setCategory(source.getCategory());
		dest.setDaytime(source.getDaytime());
		dest.setRemaining(source.getRemaining());
	}
	public static MerchantDayRemainingDto convertFromDeal(DealDto dto){
		MerchantDayRemainingDto mdto=new MerchantDayRemainingDto();
		mdto.setCategory(dto.getDealType());
		mdto.setPat(dto.getDat());
		mdto.setAccountId(dto.getDai());
		mdto.setDaytime(DateUtil.getCurrentYYYYMMDD(dto.getCreateTime()));
		mdto.setRemaining(dto.getRemain());
		return mdto;
	}
}
