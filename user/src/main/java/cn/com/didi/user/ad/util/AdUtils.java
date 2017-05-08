package cn.com.didi.user.ad.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import cn.com.didi.core.property.Couple;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;

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


	public static List<Couple<AdDto, AdPicDto>> combine(List<AdDto> adDto, List<AdPicDto> adPic,
			Consumer<AdDto> consumer) {
		List<Couple<AdDto, AdPicDto>> arrayList = new ArrayList<Couple<AdDto, AdPicDto>>(adDto.size());
		allLoop: for (AdDto one : adDto) {
			for (AdPicDto two : adPic) {
				if (one.getAdId().equals(two.getAdId())) {
					arrayList.add(new Couple<>(one, two));
					break allLoop;
				}
				if(consumer!=null){
					consumer.accept(one);
				}
			}
		}
		return arrayList;

	}
}
