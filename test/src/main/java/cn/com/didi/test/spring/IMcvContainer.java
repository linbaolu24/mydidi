package cn.com.didi.test.spring;

import org.springframework.test.web.servlet.MockMvc;

public interface IMcvContainer {
	/**
	 * @return
	 */
	public MockMvc getMvc();
}
