package cn.com.didi.test.spring;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestExecutionListeners.MergeMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.didi.test.frame.AnnotionBuilder;
import cn.com.didi.test.spring.annotion.Builder;
import cn.com.didi.test.spring.listener.SpringLog4jListener;

/**
 * <p>标题: 单元测试基类</p>
 * <p>描述: 单元测试基类 </p>
 * <p>版权: 税友软件集团股份有限公司</p>
 * <p>创建时间:2015-10-8 </p>
 * <p>作者：谢磊敏</p>
 * <p>修改历史记录：</p>
 */
//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MySpringJuniT4Runner.class)
@Builder(value=AnnotionBuilder.class)
@TestExecutionListeners(value=SpringLog4jListener.class,mergeMode=MergeMode.MERGE_WITH_DEFAULTS)
public class BaseSpringTest {
	@Autowired
	protected ApplicationContext wac;

}
