package cn.com.didi.user.login;

import cn.com.didi.core.property.IResultVisitor;

public interface ILoginResultVisitor extends IResultVisitor{
	/**
	 * 接收返回结果
	 * @param loginResult
	 */
	 void acceptLoginResult(ILoginResult loginResult);
}
