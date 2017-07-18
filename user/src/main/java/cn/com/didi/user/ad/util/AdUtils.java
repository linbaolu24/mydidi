package cn.com.didi.user.ad.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.lang.time.DateUtils;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.utils.DateUtil;
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
			Consumer<AdDto> consumer, double hdivW) {
		List<Couple<AdDto, AdPicDto>> arrayList = new ArrayList<Couple<AdDto, AdPicDto>>(adDto.size());
		for (AdDto one : adDto) {
			double minDis = Double.MAX_VALUE;
			Couple<AdDto, AdPicDto> cou = new Couple<>(one, null);
			for (AdPicDto two : adPic) {
				if (one.getAdId().equals(two.getAdId())) {
					double distance = Math.abs(1.0 * two.getHeight() / two.getWidth() - hdivW);
					if (distance < minDis) {
						minDis = distance;
						cou.setSecond(two);
					}
				}
			}
			if (cou.getSecond() != null) {
				arrayList.add(cou);
			}
			if (consumer != null) {
				consumer.accept(one);
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
	
	/**
	 * 未开始 3 结束 1 -1表示不处理
	 * @param now
	 * @param dto
	 * @return
	 */
	public static int isEndAd(Date now,AdDto dto){
		Calendar cal=DateUtils.toCalendar(now);
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		cal=DateUtils.truncate(cal, Calendar.DAY_OF_MONTH);
		Date end=dto.getAdsEnd();
		Date start=dto.getAdsStart();
		if(end==null||start==null){
			return 1;
		}
		Date days=cal.getTime();
		int startCom=days.compareTo(start);
		if(startCom<0){
			return 3;
		}
		int endCom=days.compareTo(end);
		if(endCom<0){
			return -1;
		}
		if(endCom>0){
			return 1;
		}
		return hour<dto.getAdsTimeEnd()?-1:1;
		
	}
}
