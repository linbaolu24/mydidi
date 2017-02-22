package cn.com.didi.user.login;

import cn.com.didi.user.context.ILoginContext;

public interface ILoginProcess {
	/**
	 * 
	 */
	ILoginResult postLoginBefore(ILoginContext context);
	/**
	 * 如果是postLoginBefore返回不为空,将不进行postLoginAfter
	 */
	ILoginResult postLoginAfter(ILoginContext context,ILoginResult result);
	
	/**
	 * 处理登录异常
	 * @param context
	 * @param result
	 * @param session
	 * @return
	 */
	ILoginResult postLoginException(ILoginContext context,ILoginResult result,Exception e);
	/**
	 * 如果返回false,将不再执行
	 */
	boolean postLoginSession(ILoginContext context,ISession session);
	
	
}
