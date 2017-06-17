package cn.com.didi.thirdExt.lock;

import org.redisson.config.Config;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import cn.com.didi.core.lock.LockManager;

public class RedissoonLockManagerFactory implements FactoryBean<LockManager>, InitializingBean {
	protected boolean mock = false;
	protected LockManager object;
	private int dataBase = 4;
	private String url;

	@Override
	public LockManager getObject() throws Exception {
		return object;
	}

	@Override
	public Class<?> getObjectType() {
		return LockManager.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (mock) {
			object = LockManager.NULL_LOCKMANAGER;
		} else {
			Config config = new Config();
			config.useSingleServer().setAddress(url).setDatabase(dataBase);
			RedissonLockManager manaer = new RedissonLockManager();
			manaer.setConfig(config);
			manaer.init();
			object = manaer;
		}
	}

	public int getDataBase() {
		return dataBase;
	}

	public void setDataBase(int dataBase) {
		this.dataBase = dataBase;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isMock() {
		return mock;
	}

	public void setMock(boolean mock) {
		this.mock = mock;
	}

}
