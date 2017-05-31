package cn.com.didi.test.spring;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;

import cn.com.didi.test.frame.AnnotionBuilder;
import cn.com.didi.test.spring.annotion.Builder;
import cn.com.didi.test.spring.listener.SpringLog4jListener;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MySpringJuniT4Runner.class)
@Builder(value=AnnotionBuilder.class)
@TestExecutionListeners(value=SpringLog4jListener.class,mergeMode=MergeMode.MERGE_WITH_DEFAULTS)
public class BaseSpringTest {
	@Autowired
	protected ApplicationContext wac;

}
