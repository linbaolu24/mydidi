package cn.com.didi.test.spring;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.internal.runners.statements.InvokeMethod;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.didi.test.frame.Invoker;
import cn.com.didi.test.frame.InvokerBuilder;
import cn.com.didi.test.spring.annotion.Builder;

public class MySpringJuniT4Runner extends SpringJUnit4ClassRunner {
	InvokerBuilder builder;
	private Class<?> clazz;

	public MySpringJuniT4Runner(Class<?> clazz)
			throws InitializationError, InstantiationException, IllegalAccessException {
		super(clazz);
		getBuilder();
	}

	public InvokerBuilder getBuilder() throws InstantiationException, IllegalAccessException {
		if (builder == null) {
			if(clazz==null){
				clazz=getTestClass().getJavaClass();
			}
			Class<? extends InvokerBuilder> cla = AnnotationUtils.findAnnotation(clazz, Builder.class).value();
			if (cla != null) {
				builder = cla.newInstance();
			}
		}
		return builder;
	}

	/**
	 * Returns a {@link Statement} that invokes {@code method} on {@code test}
	 */
	protected Statement methodInvoker(FrameworkMethod method, Object test) {
		Invoker invoker = builder.build(method.getMethod());
		if (invoker != null) {
			return new InvokerStatement(test, invoker);
		}
		return new InvokeMethod(method, test);
	}

	protected class InvokerStatement extends Statement {
		protected Object instance;
		protected Invoker invoker;

		public InvokerStatement(Object instance, Invoker invoker) {
			super();
			this.instance = instance;
			this.invoker = invoker;
		}

		@Override
		public void evaluate() throws Throwable {
			invoker.invoke(instance, null);
		}

	}

	@Override
	protected List<FrameworkMethod> computeTestMethods() {
		List<FrameworkMethod> list = new ArrayList(super.computeTestMethods());
		List<Invoker> invokers;
		try {
			invokers = getBuilder().list(clazz);
			if (invokers != null) {
				for (Invoker invoker : invokers) {
					Member member = invoker.getMember();
					if (!(member instanceof Method)) {
						continue;
					}
					FrameworkMethod temp = new FrameworkMethod((Method) member);
					if (!list.contains(temp)) {
						list.add(temp);
					}
				}
			}
			return Collections.unmodifiableList(list);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
