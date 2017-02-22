package cn.com.didi.test.spring.listener;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListener;
import org.springframework.util.Log4jConfigurer;

public class SpringLog4jListener implements TestExecutionListener{

	@Override
	public void beforeTestClass(TestContext testContext) throws Exception {
		Log4jConfigurer.initLogging("classpath:log/log4j/log4j.properties");
	}

	@Override
	public void prepareTestInstance(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void beforeTestMethod(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTestMethod(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterTestClass(TestContext testContext) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
