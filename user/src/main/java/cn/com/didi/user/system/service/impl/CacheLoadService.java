package cn.com.didi.user.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import cn.com.didi.core.cache.IHashAbleCache;
import cn.com.didi.thirdExt.util.ThirdConstants;
import cn.com.didi.user.system.domain.SystemParameterDto;
import cn.com.didi.user.system.service.ISysParamService;

@Service
public class CacheLoadService implements InitializingBean {
	@Resource
	protected IHashAbleCache<String, Object, Object> cache;
	@Resource
	protected ISysParamService sysParams;

	public void init() {
		if (cache.containsKey(ThirdConstants.CACHED_ENV_SET)) {
			return;
		}
		List<SystemParameterDto> params = sysParams.selectAllParams();
		if (!CollectionUtils.isEmpty(params)) {
			for (int i = 0; i < params.size(); i++) {
				cache.hput(ThirdConstants.CACHED_ENV, params.get(i).getParamCode(), params.get(i).getParamValue());
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
