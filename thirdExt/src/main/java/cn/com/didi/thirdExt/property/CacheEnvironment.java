package cn.com.didi.thirdExt.property;

import cn.com.didi.core.cache.IHashAbleCache;
import cn.com.didi.core.property.impl.env.EnviromentAdapter;
import cn.com.didi.thirdExt.util.ThirdConstants;
import cn.com.didi.user.system.service.ISysParamService;

/**
 * @author xlm
 *
 */
public class CacheEnvironment extends EnviromentAdapter {
	public IHashAbleCache<String, Object, Object> getHashAble() {
		return hashAble;
	}
	public void setHashAble(IHashAbleCache<String, Object, Object> hashAble) {
		this.hashAble = hashAble;
	}
	protected IHashAbleCache<String, Object, Object> hashAble;
	protected ISysParamService sysParamService;
	@Override
	public String getProperty(String key) {
		String value= (String) hashAble.hget(ThirdConstants.CACHED_ENV, key);
		if(value==null){
			value =sysParamService.selectSysparamsValue(key);
		}
		return value;
	}
	public ISysParamService getSysParamService() {
		return sysParamService;
	}
	public void setSysParamService(ISysParamService sysParamService) {
		this.sysParamService = sysParamService;
	}
	
}
