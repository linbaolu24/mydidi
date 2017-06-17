package cn.com.didi.user.ad.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import cn.com.didi.core.property.Couple;
import cn.com.didi.domain.util.AdCategoryEnum;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.AdvertRecordDto;
import cn.com.didi.user.ad.domain.DpDto;

public class AdUtils {
	/**
	 * @param lists
	 * @return
	 */
	public static List<Long> getAdList(List<AdDto> lists) {
		final List<Long> adList = new ArrayList<>(lists.size());
		lists.stream().forEach(one -> adList.add(one.getAdId()));
		return adList;

	}
	
	/**
	 * @param lists
	 * @return
	 */
	public static <T> List<Long> getAdListFromCouple(List<Couple<AdDto,T>> lists) {
		final List<Long> adList = new ArrayList<>(lists.size());
		lists.stream().forEach(one -> adList.add(one.getFirst().getAdId()));
		return adList;

	}

   
	/**
	 * @param adDto
	 * @param adPic
	 * @param consumer
	 * @return
	 */
	public static List<Couple<AdDto, AdPicDto>> combine(List<AdDto> adDto, List<AdPicDto> adPic,
			Consumer<AdDto> consumer) {
		List<Couple<AdDto, AdPicDto>> arrayList = new ArrayList<Couple<AdDto, AdPicDto>>(adDto.size());
		allLoop: for (AdDto one : adDto) {
			double minDis = Double.MAX_VALUE;
			for (AdPicDto two : adPic) {
				if (one.getAdId().equals(two.getAdId())) {
					double distance = 1.0 * two.getHeight() / two.getWidth();
					if (distance < minDis) {
						minDis=distance;
						arrayList.add(new Couple<>(one, two));
						break allLoop;
					}
				}
				if (consumer != null) {
					consumer.accept(one);
				}
			}
		}
		return arrayList;

	}
	public static void copyProperty(DpDto dp,AdvertRecordDto record){
		record.setDisplayPosition(record.getDisplayPosition());
		record.setPhoneType(dp.getPhoneType());
	}
	
	public static void popProperty(Long adId,Long accountId,AdCategoryEnum cat, DpDto dp,AdvertRecordDto record){
		record.setDisplayPosition(record.getDisplayPosition());
		record.setPhoneType(dp.getPhoneType());
		record.setCategory(cat.getCode());
		record.setAccountId(accountId);
		record.setAdId(adId);
		if(record.getCreateTime()==null){
			record.setCreateTime(new Date());
		}
		
	}
}
