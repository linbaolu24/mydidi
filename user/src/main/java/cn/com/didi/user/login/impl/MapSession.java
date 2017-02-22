package cn.com.didi.user.login.impl;

import java.util.HashMap;
import java.util.Map;

import cn.com.didi.user.login.ISession;

public class MapSession implements ISession{
	private Map<String,Object> map=new HashMap<String,Object>(4);
	@Override
	public void put(String key, Object obj) {
		map.put(key, obj);
	}

	@Override
	public Object get(String key) {
		return map.get(key);
	}

	@Override
	public void dispose() {
		map.clear();
	}

}
