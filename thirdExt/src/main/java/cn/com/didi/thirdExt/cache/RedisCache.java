package cn.com.didi.thirdExt.cache;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import cn.com.didi.core.cache.IRedisCache;

public class RedisCache implements IRedisCache {
	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Resource(name="redisTemplate")
	private ValueOperations<String, Object> valueOps;
	@Resource(name="redisTemplate")
	private ListOperations<String, Object> listOps;
	@Resource(name="redisTemplate")
	private HashOperations<String, Object, Object> hashOps;

	public DataType getkeyType(String key) {
		return redisTemplate.type(key);
	}

	@Override
	public long listLeftPush(String key, Object value) {
		return listOps.leftPush(key, value);
	}

	@Override
	public long listRightPush(String key, Object value) {
		return listOps.rightPush(key, value);
	}

	@Override
	public long listLength(String key) {
	    return listLength(key);
	}

	@Override
	public Object listLeftPop(String key) {
		return listOps.leftPop(key);
	}

	@Override
	public Object put(String key, Object value) {
		return valueOps.getAndSet(key, value);
	}

	
	public Object put(String key, Object value, long TTL){
		Object obj=valueOps.getAndSet(key, value);
		redisTemplate.expire(key, TTL, TimeUnit.MILLISECONDS);
		return obj;
	}
	@Override
	public Object put(String key, Object value, Date expiry) {
		Long ttl=expiry.getTime()-System.currentTimeMillis();
		return put(key, value,ttl);
	}

	@Override
	public Object get(String key) {
		return valueOps.get(key);
	}

	@Override
	public Object remove(String key) {
		Object obj=valueOps.get(key);
		redisTemplate.delete(key);
		return obj;
	}

	@Override
	public Set<String> keySet() {
		return redisTemplate.keys("*");
	}

	@Override
	public Collection<Object> values() {
		return valueOps.multiGet(keySet());
	}

	@Override
	public boolean containsKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public void destroy() {
		//redisTemplate.isExposeConnection()
	}

	@Override
	public boolean clear() {
		 redisTemplate.delete(keySet());
		 return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		return keySet().size();
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> map) {
		valueOps.multiSet(map);
	}

	@Override
	public Object hput(String key, Object hashKey, Object value) {
		Object obj=hget(key,hashKey);
		 hashOps.put(key, hashKey, value);
		 return obj;
	}

	@Override
	public Object hget(String key, Object hashKey) {
		return hashOps.get(key, hashKey);
	}

	@Override
	public boolean hasHashKey(String key, Object hashKey) {
		return hashOps.hasKey(key, hashKey);
	}

	@Override
	public Map<Object, Object> entries(String key) {
		return hashOps.entries(key);
	}

	@Override
	public void putAll(String key, Map<? extends Object, ? extends Object> value) {
		hashOps.putAll(key,value);
	}

	@Override
	public void delete(String key, Object haskKey) {
		hashOps.delete(key, haskKey);
	}

	@Override
	public List<Object> multiGet(String key, Collection<Object> hashKeys) {
		return hashOps.multiGet(key, hashKeys);
	}

}
