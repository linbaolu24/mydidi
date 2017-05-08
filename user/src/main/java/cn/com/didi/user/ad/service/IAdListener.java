package cn.com.didi.user.ad.service;

import java.util.List;

import cn.com.didi.core.property.Couple;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;

public interface IAdListener {
	public void fireQueryAdList(List<Couple<AdDto, AdPicDto>> list);
}
