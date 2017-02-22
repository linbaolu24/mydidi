package cn.com.didi.message.registry;

import java.net.URL;
import java.util.List;

import cn.com.didi.message.listener.IMessageListener;

/**
 * 注册中心
 * @author xlm
 *
 */
public interface IRegistry {
	/**
	 * @param url
	 * @param listener
	 */
	public void register(URL url,IMessageListener listener);
	/**
	 * @param url
	 * @param listener
	 */
	public void unregister(URL url,IMessageListener listener);
	/**
	 * @param url
	 * @param listener
	 */
	public void subscribe(URL url,IMessageListener listener);
	/**
	 * @param url
	 * @param listener
	 */
	public void unsubscribe(URL url,IMessageListener listener);
	/**
	 * @param url
	 * @return
	 */
	public List<IMessageListener> lookup(URL ul);
	/**
	 * @param url
	 * @return
	 */
	public List<IMessageListener> lookSub(URL url);
}
