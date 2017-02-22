package cn.com.didi.core.callBack;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflactCallBack<T,R> implements ICallBack<T, R> {
	private Method method;
	@Override
	public R call(T param,Object... args) {
		if(param==null){
			return null;
		}
		
	    try {
			return (R)method.invoke(param, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	public void init(Class clazz,String name,Class... params){
		try {
			method=clazz.getDeclaredMethod(name, params);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}
	public ReflactCallBack(Method method) {
		super();
		this.method = method;
	}
	public ReflactCallBack() {
		this(null);
	}
}
