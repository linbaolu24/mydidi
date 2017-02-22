package cn.com.didi.core.cache;

public interface IListAbleCache<K, V> extends ICache<K, V>{
	/**
	 * 列表左插入
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	long listLeftPush(K key, V value);

	/**
	 * 列表右插入
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	long listRightPush(K key, V value);
	/**
	 * @param key
	 * @return
	 */
	long listLength(K key);
	/**
	 * @param key
	 * @return
	 */
	V listLeftPop(K key);
}
