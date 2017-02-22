package cn.com.didi.user.login;

/**
 * @author xlm
 *
 */
public interface ISession {
	/**
	 * @param key
	 * @param obj
	 */
	public void put(String key,Object obj);
	/**
	 * @param key
	 * @return
	 */
	public Object get(String key);
	/**
	 * 幂等操作
	 * 销毁会话
	 */
	void dispose();
}
