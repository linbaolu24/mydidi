package cn.com.didi.platform.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static cn.com.didi.domain.util.NameConstans.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.DomainMessageConstans;
import cn.com.didi.platform.login.domain.PLoginDto;
import cn.com.didi.platform.login.util.VerifyCodeUtils;
import cn.com.didi.user.login2.domain.LoginDto;
import cn.com.didi.user.login2.domain.UserExtDto;
import cn.com.didi.user.login2.service.ILoginService;
import cn.com.didi.webBase.util.IAccountResolver;

/**
 * 
 * @author xlm
 *
 */
@RestController
public class LoginController {
	@Resource
	protected ILoginService loginService;
	@Resource
	protected IAccountResolver resolver;

	@RequestMapping(value = "/platform/user/login", method = RequestMethod.POST)
	public IResult login(@RequestBody PLoginDto dto, HttpServletRequest request) {
		AssertUtil.assertNotNullAppend(dto.getPassword(), PASSWORD);
		AssertUtil.assertNotNullAppend(dto.getUserName(), USER_NAME);
		AssertUtil.assertNotNullAppend(dto.getVc(), VC);
		String ver=resolver.getAttr(request, DomainConstatns.VER_CODE);
		if(ver==null||dto.getVc()==null||!dto.getVc().equalsIgnoreCase(ver)){
			return ResultFactory.error(DomainMessageConstans.CODE_USER_VC_NOT_EQUAL,"验证码不相等");
		}
		String passWord=DigestUtils.md5Hex(dto.getPassword());
		LoginDto login=new LoginDto();
		login.setPassword(passWord);
		login.setPhone(dto.getUserName());
		login.setRole("P");
		IResult<UserExtDto> result=loginService.login(login);
		if (!result.success()) {
			return ResultFactory.error(result.getCode(), result.getMessage());
		}
		resolver.saveAccount(request, result.getData().getUserDto().getAccountId(),new HashMap(2));
		result.getData().getUserDto().setPassword(null);
		result.getData().getUserDto().setAccountId(null);
		result.getData().getUserDto().setRole(null);
		return ResultFactory.success(result.getData().getUserDto());
	}
	
	
	@RequestMapping(value = "/platform/user/loginout", method = {RequestMethod.POST,RequestMethod.GET})
	public IResult loginout( HttpServletRequest request) {
		resolver.clearAccount(request);
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/user/vc", method = RequestMethod.GET)
	public void getVc(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		// 生成随机字串
		String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
		// 存入会话session
		//HttpSession session = request.getSession(true);
		// 删除以前的
		//session.removeAttribute("verCode");
		//session.setAttribute("verCode", verifyCode.toLowerCase());
		resolver.saveAttr(request,  DomainConstatns.VER_CODE, verifyCode);
		// 生成图片
		int w = 100, h = 30;
		VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
	}
}
