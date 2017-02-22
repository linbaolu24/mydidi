package cn.com.didi.test.frame;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.MethodCallback;
import org.springframework.util.ReflectionUtils.MethodFilter;

import cn.com.didi.test.annotion.FilePath;
import cn.com.didi.test.annotion.Uri;
import cn.com.didi.test.spring.IMcvContainer;

/**
 * @author xlm
 *
 */
public class AnnotionBuilder implements InvokerBuilder {

	@Override
	public Invoker build(Method me) {
		cn.com.didi.test.annotion.Uri uri = AnnotationUtils.findAnnotation(me, cn.com.didi.test.annotion.Uri.class);
		cn.com.didi.test.annotion.FilePath filePath = AnnotationUtils.findAnnotation(me,
				cn.com.didi.test.annotion.FilePath.class);
		if (uri == null || filePath == null) {
			return null;
		}
		return new AnnotionInvoker(me, uri, filePath);
	}

	protected class AnnotionInvoker implements Invoker {
		private Method method;
		private cn.com.didi.test.annotion.Uri uri;
		private cn.com.didi.test.annotion.FilePath filePath;

		@Override

		public String getName() {
			return method.getName();
		}

		public AnnotionInvoker(Method method, Uri uri, FilePath filePath) {
			super();
			this.method = method;
			this.uri = uri;
			this.filePath = filePath;
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
		public Object invoke(Object target, Object... args) throws Throwable {
			System.out.println(uri.value());
			System.out.println(filePath.value());
			if (target instanceof IMcvContainer) {

				return null;
			} else {
				return method.invoke(target, args);
			}
		}

		@Override
		public Member getMember() {
			return method;
		}

	}

	@Override
	public List<Invoker> list(Class clazz) {
		final List<Invoker> list = new ArrayList(4);
		ReflectionUtils.doWithMethods(clazz, new MethodCallback() {

			@Override
			public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
				list.add(new AnnotionInvoker(method,
						AnnotationUtils.findAnnotation(method, cn.com.didi.test.annotion.Uri.class),
						AnnotationUtils.findAnnotation(method, cn.com.didi.test.annotion.FilePath.class)));
			}
		}, new MethodFilter() {

			@Override
			public boolean matches(Method method) {
				return method.getAnnotation(cn.com.didi.test.annotion.Uri.class) != null
						&& method.getAnnotation(cn.com.didi.test.annotion.FilePath.class) != null;
			}
		});
		return list;
	}

}
