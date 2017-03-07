package cn.com.didi.test.spring.listener;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import cn.com.didi.test.frame.InvokerBuilder;

public class BuilderInjectTestExecutionListener extends AbstractTestExecutionListener {
	private InvokerBuilder builder;

	/**
	 * The default implementation is <em>empty</em>. Can be overridden by
	 * subclasses as necessary.
	 */
	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		AutowireCapableBeanFactory beanFactory = testContext.getApplicationContext().getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(builder, AutowireCapableBeanFactory.AUTOWIRE_NO, false);
		beanFactory.initializeBean(builder, testContext.getTestClass().getName());
	}
}
