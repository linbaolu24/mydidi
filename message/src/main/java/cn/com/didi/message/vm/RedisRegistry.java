package cn.com.didi.message.vm;

import java.net.URL;
import java.util.List;

import cn.com.didi.message.listener.IMessageListener;
import cn.com.didi.message.registry.IRegistry;

public class RedisRegistry implements IRegistry{
	protected IRegistry registry;
	public void register(URL url, IMessageListener listener) {
		String userId=url.getUserInfo();//如果要区分功能使用path
	}

	public void unregister(URL url, IMessageListener listener) {
	}

	public void subscribe(URL url, IMessageListener listener) {
		registry.subscribe(url, listener);
	}

	public void unsubscribe(URL url, IMessageListener listener) {
		registry.unsubscribe(url, listener);
	}

	public List<IMessageListener> lookup(URL ul) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IMessageListener> lookSub(URL url) {
		return registry.lookSub(url);
	}

	public IRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(IRegistry registry) {
		this.registry = registry;
	}
    
}
