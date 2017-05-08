package cn.com.didi.user.ad.service.impl;

import java.util.List;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.thread.ExecutorFactory;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.service.IAdListener;
import cn.com.didi.user.ad.service.IAdService;
import cn.com.didi.user.ad.util.AdUtils;

@Service
public class AdLogServiceImpl implements IAdListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdLogServiceImpl.class);
	@Resource
	protected IAdService adService;
	@Resource
	protected ExecutorFactory factory;
	protected Executor exe;

	public void init() {
		if (exe == null) {
			exe = factory.createExecutor();
		}
	}

	@Override
	public void fireQueryAdList(List<Couple<AdDto, AdPicDto>> list) {
		try {
			final List adList = AdUtils.getAdListFromCouple(list);
			exe.execute(new Runnable() {
				@Override
				public void run() {
					try {
						adService.addExposure(adList);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

}
