package cn.com.didi.test;

import cn.com.didi.test.annotion.FilePath;
import cn.com.didi.test.annotion.Uri;
import cn.com.didi.test.spring.BaseSpringWebTest;

/**
 * Unit test for simple App.
 */

public class AppTest extends BaseSpringWebTest {
	//@Test
	@Uri("1111111")
	@FilePath("2222222")
	public void test(){
		System.out.println("======test=====");
	}
}
