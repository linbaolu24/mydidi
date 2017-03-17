package cn.com.didi.thirdExt.property;

import cn.com.didi.core.cache.IHashAbleCache;
import cn.com.didi.core.property.impl.env.EnviromentAdapter;
import cn.com.didi.thirdExt.util.ThirdConstants;

/**
 * @author xlm
 *
 */
public class CacheEnvironment extends EnviromentAdapter{
	public IHashAbleCache<String, Object, Object> getHashAble() {
		return hashAble;
	}
	public void setHashAble(IHashAbleCache<String, Object, Object> hashAble) {
		this.hashAble = hashAble;
	}
	protected IHashAbleCache<String, Object, Object> hashAble;
	@Override
	public String getProperty(String key) {
		return (String) hashAble.hget(ThirdConstants.CACHED_ENV, key);
	}
	
}
