package cn.com.didi.test.frame;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author xlm
 *
 */
public interface InvokerBuilder {
	public Invoker build(Method me);
	public List<Invoker> list(Class<?> clazz);
}
