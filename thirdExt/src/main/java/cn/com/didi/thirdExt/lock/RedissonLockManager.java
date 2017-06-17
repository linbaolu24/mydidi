package cn.com.didi.thirdExt.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

import cn.com.didi.core.lock.ILock;
import cn.com.didi.core.lock.LockManager;

public class RedissonLockManager implements LockManager {
	private Redisson redisson;
	private Config config;
	
	public void init() {
		if (redisson == null) {
			if (config == null) {
				redisson = (Redisson) Redisson.create();
			} else {
				redisson = (Redisson) Redisson.create(config);
			}
		}
	}
	/*
	 * @Override public boolean lock(String name, long timeOut) { // TODO
	 * Auto-generated method stub return false; }
	 * 
	 * @Override public boolean unlock(String name) { // TODO Auto-generated
	 * method stub return false; }
	 */

	@Override
	public ILock accquireLock(String name) {
		return new RedissonLock(name, redisson);
	}

	public static class RedissonLock implements ILock {
		private RLock lock;

		public RedissonLock(String name, Redisson redisson) {
			lock = redisson.getLock(name);
		}

		@Override
		public void lock() {
			lock.lock();

		}

		@Override
		public boolean lock(Long timeOut) {
			return lock(timeOut, TimeUnit.SECONDS);
		}

		@Override
		public boolean lock(Long timeOut, TimeUnit unit) {
			try {
				return lock.tryLock(timeOut, unit);
			} catch (InterruptedException e) {
				return false;
			}
		}

		@Override
		public void unlock() {
			lock.unlock();
		}
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
	
}
