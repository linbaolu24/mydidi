package cn.com.didi.app.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.service.IWechatBaseService;
import cn.com.didi.webBase.util.IAccountResolver;
@RestController
public class WechatBaseController {
	@Resource
	protected IWechatBaseService wechatBaseService;
	@Resource
	protected IAccountResolver accountResolver;
	@RequestMapping(value = "/app/b/wechat/getUser", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public IResult getWechatUser(@RequestBody Map<String, String> userInfo, HttpServletRequest request) {
		String code = userInfo.get("code");
		AssertUtil.assertNotNullAppend(code, "code");
		Long accountId=accountResolver.resolve(request);
		UserWechatDto dto = wechatBaseService.getUserInfo(accountId, code);
		Map<String,String> resultMap=new HashMap<>();
		resultMap.put(DomainConstatns.NICK_NAME, dto.getNickname());
		resultMap.put(DomainConstatns.HEAD_IMG, dto.getNickname());
		return ResultFactory.success(dto);
	}
	
}
