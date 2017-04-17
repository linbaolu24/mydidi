package cn.com.didi.app.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.user.login2.domain.LoginDto;
import cn.com.didi.user.login2.domain.UserExtDto;
import cn.com.didi.user.login2.service.ILoginService;
import cn.com.didi.user.register.domain.RegisterDto;
import cn.com.didi.user.register.service.IRegisterService;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.webBase.util.IAccountResolver;

@RestController
public class AppUserController {
	@Resource
	protected IAccountResolver resolver;
	@Resource
	protected IRegisterService registerService;
	@Resource
	protected ILoginService tloginService;
	@Resource
	protected IUserService tUserService;

	@RequestMapping(value = "/app/user/register", method = RequestMethod.POST)
	public IResult register(@RequestBody RegisterDto dto) {
		Long accountId = registerService.register(dto);
		Map p = new HashMap(1);
		p.put(DomainConstatns.ACCOUNT_ID, accountId);
		p.put(DomainConstatns.BPN,dto.getPhone());
		return ResultFactory.success(p);
	}

	@RequestMapping(value = "/app/user/sendVc", method = RequestMethod.POST)
	public IResult sendVc(@RequestBody RegisterDto dto) {
		Long vcId = registerService.sendVc(dto.getPhone());
		Map p = new HashMap(1);
		p.put(DomainConstatns.VC_ID, vcId);
		return ResultFactory.success(p);

	}

	@RequestMapping(value = "/app/user/resetPassword", method = RequestMethod.POST)
	public IResult resetPassword(@RequestBody RegisterDto dto) {
		registerService.resetPassword(dto);
		return ResultFactory.success();

	}

	@RequestMapping(value = "/app/user/changePassword", method = RequestMethod.POST)
	public IResult changePassword(@RequestBody Map<String, String> dto, HttpServletRequest request) {
		String password = dto.get(DomainConstatns.PASSWORD);
		String opassword = dto.get(DomainConstatns.OLD_PASSWORD);
		Long accountId = resolver.resolve(request);
		registerService.changePassword(accountId, password, opassword);//
		return ResultFactory.success();

	}

	@RequestMapping(value = "/app/user/login", method = RequestMethod.POST)
	public IResult login(@RequestBody LoginDto login, HttpServletRequest request) {
		long now=System.currentTimeMillis();
		IResult<UserExtDto> result = tloginService.login(login);
		if (!result.success()) {
			return ResultFactory.error(result.getCode(), result.getMessage());
		}
		Map p = new HashMap(4);
		UserExtDto ext = result.getData();
		p.put(DomainConstatns.ACCOUNT_ID, ext.getUserDto().getAccountId());
		p.put(DomainConstatns.PROFILE_PHOTO, ext.getUserDto().getProfilePhoto());
		p.put(DomainConstatns.ALIPAY_ACCOUNT, ext.alipayAccount());
		p.put(DomainConstatns.WECHAT_ACCOUNT, ext.wechatAccount());
		p.put(DomainConstatns.GT_CID, ext.gtCid());
		p.put(DomainConstatns.RY_TOKEN, ext.ryToken());
		p.put(DomainConstatns.BPN,StringUtils.defaultIfEmpty(login.getPhone(),ext.getUserDto().getBpn()));
		Long timeOut=resolver.getSessionTimepout(request);
		Date date=new Date(now+timeOut*1000);
		p.put(DomainConstatns.TIMEOUT,date);
		resolver.saveAccount(request, ext.getUserDto().getAccountId(),p);
		return ResultFactory.success(p);
	}
	@RequestMapping(value = "/app/user/loginout", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult setThirdId(HttpServletRequest request) {
		resolver.clearAccount(request);
		return ResultFactory.success();
	}
	
	
	@RequestMapping(value = "/app/user/reflashToken", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult reflashToken(HttpServletRequest request) {
		long now=System.currentTimeMillis();
		resolver.reflashAccount(request);
		Long timeOut=resolver.getSessionTimepout(request);
		Map p = new HashMap(4);
		Date date=new Date(now+timeOut*1000);
		p.put(DomainConstatns.TIMEOUT,date);
		return ResultFactory.success(p);
	}
	
	
	
	@RequestMapping(value = "/app/user/setThirdId", method = { RequestMethod.POST })
	public IResult setThirdId(@RequestBody  UserLinkIdDto linkedDto,HttpServletRequest request) {
		Long accountId=resolver.resolve(request);
		tUserService.updateLinkedId(accountId, linkedDto.getGtCid(), linkedDto.getRyToken());
		return ResultFactory.success();
	}

}
