package cn.com.didi.core.cache;

/**
 * redis缓存
 * @author xlm
 *
 */
public interface IRedisCache extends IListAbleCache<String, Object>,IHashAbleCache<String, Object, Object> {

}
