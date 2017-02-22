package cn.com.didi.user.login;

import cn.com.didi.core.property.IResult;
import cn.com.didi.user.context.IUserContext;

public interface ILoginResult extends IResult<IUserContext> {
	public String getUserNameAlias();
	/**
	 * 创建会话
	 * @param session
	 */
	public void setSession(ISession session);
	/**
	 * 获取会话
	 * @return
	 */
	public ISession getSession();
}
