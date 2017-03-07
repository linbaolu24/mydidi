package cn.com.didi.test.frame;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

public interface Request {
	public ResultActions request();
	public MvcResult sync();
}
