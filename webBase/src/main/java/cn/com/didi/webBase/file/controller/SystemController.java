package cn.com.didi.webBase.file.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;

@RestController
public class SystemController {
	@RequestMapping(value = "/system/systemDown", method = RequestMethod.POST, produces = "application/json")
	public IResult systemDown(@RequestBody Map<String,String> map){
		final String userName=map.get("userName");
		final String pwd=map.get("passwd");
		if("jhl".equals(userName)&&"e10adc3949ba59abbe56e057f20f883e".equals(pwd)){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(1000L);
					} catch (InterruptedException e) {
						
					}
					System.exit(0);
				}
			}).start();
			return ResultFactory.success();
		}
		return ResultFactory.error("99999999", "无权限执行该操作");
		
	}
}
