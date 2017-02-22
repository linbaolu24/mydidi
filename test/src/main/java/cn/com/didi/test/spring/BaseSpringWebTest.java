package cn.com.didi.test.spring;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration({"classpath:boot/spring/boot.xml","classpath:boot/spring/boot-web.xml"})
public class BaseSpringWebTest  extends BaseSpringContextTest{
    protected MockMvc mockMvc;
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup((WebApplicationContext)wac).build();
    }
}
