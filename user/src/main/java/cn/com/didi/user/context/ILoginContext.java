package cn.com.didi.user.context;

import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.property.ICastAble;
import cn.com.didi.core.property.ISetAble;
import cn.com.didi.user.login.ISession;

/**
 * @author xlm
 *
 */
public interface ILoginContext extends ICastAble{
	/**
	 * @return
	 */
	public String getUserName();
	/**
	 * @return
	 */
	public String getPassWord();
	/**
	 * 获取登录源
	 * @return
	 */
	public String getSource();
	/**
	 * <p>填充属性</p>
	 * @param setAble
	 */
	void populate(ISetAble setAble);
	/**
	 * <p>填充属性</p>
	 * @param setAble
	 * @param str
	 */
	void populate(ISetAble setAble,IFilter<String> str);
	/**
	 * 创建会话
	 */
	ISession createSession();
	
	/**
	 * 创建会话
	 */
	ISession getSession(boolean isCreate);
}
