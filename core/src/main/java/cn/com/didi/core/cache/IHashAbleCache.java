package cn.com.didi.core.cache;

import java.util.Map;

/**
 * @author xlm
 *
 */
public interface IHashAbleCache<K,HK,V> extends ICache<K, V> {
	/**
	 * @param key
	 * @param hashKey
	 * @param value
	 * @return
	 */
	V hput(K key,HK hashKey,V value);
	/**
	 * @param key
	 * @param hashKey
	 * @return
	 */
	V hget(K key,HK hashKey);
	/**
	 * @param key
	 * @param hashKey
	 * @return
	 */
	boolean hasHashKey(K key,HK hashKey);
	/**
	 * @param key
	 * @return
	 */
	Map<HK,V> entries(K key);
	public void putAll(K key,Map<? extends HK,? extends V> value);
}
