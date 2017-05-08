package cn.com.didi.user.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if(cache.get(ThirdConstants.CACHED_ENV_LIST)!=null){
			return ;
		}
		cache.put(ThirdConstants.CACHED_ENV_LIST, params);
		if (!CollectionUtils.isEmpty(params)) {
			Map<String,String> map=new HashMap<>();
			Map<String,Object> objMap=new HashMap<>();
			for (int i = 0; i < params.size(); i++) {
				map.put(params.get(i).getParamCode(),params.get(i).getParamValue());
				objMap.put(params.get(i).getParamCode(),params.get(i));
			}
			cache.putAll(ThirdConstants.CACHED_ENV, map);
			cache.putAll(ThirdConstants.CACHED_ENV_OBJECT, objMap);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
	
}
