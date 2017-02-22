package cn.com.didi.user.login;

import cn.com.didi.user.context.ILoginContext;

/**
 * @author xlm
 */
public interface ILoginService {
	/**
	 * ��¼
	 * @param context
	 */
	public ILoginResult login(ILoginContext context);
	/**
	 * ����LoginObverser
	 * @param obverser
	 */
	public void addLoginObverser(ILoginProcess obverser);
}
