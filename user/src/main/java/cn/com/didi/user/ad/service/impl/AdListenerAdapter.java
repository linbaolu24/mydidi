package cn.com.didi.user.ad.service.impl;

import java.util.List;

import cn.com.didi.core.property.Couple;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.service.IAdListener;

public class AdListenerAdapter implements IAdListener{

	@Override
	public void fireQueryAdList(List<Couple<AdDto, AdPicDto>> list) {
	}

}
