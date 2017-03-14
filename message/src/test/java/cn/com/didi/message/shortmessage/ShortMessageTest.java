package cn.com.didi.message.shortmessage;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import cn.com.didi.message.shortmessage.domain.ShortMessgaeDto;
import cn.com.didi.message.shortmessage.service.impl.ShortMessageServiceImpl;
import cn.com.didi.thirdExt.http.SimpleHttpExeService;
import cn.com.didi.thirdExt.http.SimpleHttpService;

public class ShortMessageTest {
	@Test
	public void testShortMessage() throws NoSuchAlgorithmException{
		ShortMessageServiceImpl impl=new ShortMessageServiceImpl();
		
		SimpleHttpExeService exeService=new SimpleHttpExeService();
		exeService.init();
		SimpleHttpService servce=new SimpleHttpService();
		servce.setHttpExe(exeService);
		impl.setHttpService(servce);
		impl.setUserCode("DSKJCF");
		//impl.setUserPass("KJYXabc123");
		impl.setUserPass("SIU789SY");
		impl.setHttpService(servce);
		impl.setChannel("0");
		impl.init();
		ShortMessgaeDto dto=new ShortMessgaeDto();
		dto.setPhones(new String[]{"15858150715"});
		dto.setContent("您的验证码为：1234，30分钟内有效【嘀嘀】");
		impl.sendMessage(dto);
		
	}
}
