package cn.com.didi.user.ad.service.impl;

import java.util.List;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.thread.ExecutorFactory;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.DpDto;
import cn.com.didi.user.ad.service.IAdService;
import cn.com.didi.user.ad.util.AdUtils;

@Service
public class AdLogServiceImpl extends AdListenerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(AdLogServiceImpl.class);
	@Resource
	protected IAdService adService;
	@Resource
	protected ExecutorFactory factory;
	protected Executor exe;
	@Resource
	protected IAppEnv appEnv;

	public void init() {
		if (exe == null) {
			exe = factory.createExecutor();
		}
	}
	@Override
	public void fireQueryAdList(Long accountId, DpDto display, List<Couple<AdDto, AdPicDto>> list) {
		final List<Long> adList = AdUtils.getAdListFromCouple(list);
		exe.execute(new Runnable() {
			@Override
			public void run() {
				try {
						adService.addExposure(accountId,display,adList);
				} catch (Exception e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		});
	}

}
