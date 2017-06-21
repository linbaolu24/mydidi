package cn.com.didi.core.cache;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @author xlm
 *
 */
public interface ICache<K, V> {
	V put(K key, V value);

	V put(K key, V value, long TTL);

	/**
	 * @param key
	 * @param value
	 * @param expiry
	 */
	V put(K key, V value, Date expiry);

	/**
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 * @param key
	 * @return
	 */
	public V remove(K key);

	/**
	 * @return
	 */
	public Set<K> keySet();
	/**
	 * @return
	 */
	public Collection<V> values();
	/**
	 * @param key
	 * @return
	 */
	public boolean containsKey(K key);
	/**
	 * 
	 */
	public void destroy();
	/**
	 * @return
	 */
	public boolean clear();
	/**
	 * @return
	 */
	public String getName();
	/**
	 * 缓冲数量
	 * @return
	 */
	public int size();
	/**
	 * @param map
	 */
	void putAll(Map<? extends K,? extends V> map);
}
