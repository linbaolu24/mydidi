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
		impl.setUserCode("DSKJYX");
		impl.setUserPass("KJYXabc123");
		impl.setHttpService(servce);
		impl.setChannel("86");
		impl.init();
		ShortMessgaeDto dto=new ShortMessgaeDto();
		dto.setPhones(new String[]{"15858150715"});
		dto.setContent("验证码[123456]【嘀嘀】");
		impl.sendMessage(dto);
		
	}
}
