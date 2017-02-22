package cn.com.didi.test.frame;

import java.lang.reflect.Member;

public interface Invoker {
	String getName();

	Class<?> getType();

	Class<?> getDeclaringClass();

	public Object invoke(Object target,Object ...args) throws Throwable;
	public Member getMember();
}
