package cn.com.didi.message.vm;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.message.listener.IMessageListener;
import cn.com.didi.message.registry.IRegistry;

/**
 * @author xlm
 *
 */
public class VmRegistry implements IRegistry {
	protected HashMap<URL, List<IMessageListener>> subscribe = new HashMap<URL, List<IMessageListener>>();
	protected HashMap<URL, List<IMessageListener>> register = new HashMap<URL, List<IMessageListener>>();
	protected ReentrantReadWriteLock subscribeLock = new ReentrantReadWriteLock();
	protected ReentrantReadWriteLock registerLock = new ReentrantReadWriteLock();

	public void register(URL url, IMessageListener listener) {
		register(registerLock.writeLock(), register, url, listener);
	}

	protected void unregister(Lock lock, Map<URL, List<IMessageListener>> map, URL url, IMessageListener listener) {
		try {
			lock.lock();
			List<IMessageListener> list = (List<IMessageListener>) map.get(url);
			if (CollectionUtils.isEmpty(list)) {
				return;
			}
			list.remove(listener);
		} finally {
			lock.unlock();
		}

	}

	protected void register(Lock lock, Map<URL, List<IMessageListener>> map, URL url, IMessageListener listener) {
		try {
			lock.lock();
			List<IMessageListener> list = (List<IMessageListener>) map.get(url);
			if (CollectionUtils.isEmpty(list)) {
				list = new ArrayList<IMessageListener>(4);
				map.put(url, list);
			}
			list.add(listener);
		} finally {
			lock.unlock();
		}

	}

	protected List<IMessageListener> look(Lock lock, Map<URL, List<IMessageListener>> map, URL url) {
		try {
			lock.lock();
			List<IMessageListener> list = (List<IMessageListener>) map.get(url);
			return list;
		} finally {
			lock.unlock();
		}

	}

	public void unregister(URL url, IMessageListener listener) {
		unregister(registerLock.writeLock(), register,url, listener);
	}

	public void subscribe(URL url, IMessageListener listener) {
		register(subscribeLock.writeLock(),subscribe,url,listener);
	}

	public void unsubscribe(URL url, IMessageListener listener) {
		unregister(subscribeLock.writeLock(), subscribe, url, listener);
		
	}

	public List<IMessageListener> lookup(URL url) {
		return look(registerLock.readLock(), register, url);
	}

	public List<IMessageListener> lookSub(URL url) {
		return look(registerLock.readLock(), subscribe, url);
	}

}
