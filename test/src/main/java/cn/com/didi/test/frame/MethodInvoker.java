package cn.com.didi.test.frame;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * @author xlm
 *
 */
public class MethodInvoker implements Invoker{
	private Method method;
	@Override
	public String getName() {
		return method.getName();
	}

	@Override
	public Class<?> getType() {
		return method.getReturnType();
	}

	@Override
	public Class<?> getDeclaringClass() {
		return method.getDeclaringClass();
	}

	@Override
	public Object invoke(Object target,Object ...args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		return method.invoke(target, args);
	}

	@Override
	public Member getMember() {
		return method;
	}

}
