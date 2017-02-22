package cn.com.didi.platform.login;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.impl.result.Result;
import cn.com.didi.platform.login.util.VerifyCodeUtils;
import cn.com.didi.user.login.ILoginService;

/**
 * 
 * @author xlm
 *
 */
@RestController
public class LoginController {
	@Resource
	protected ILoginService loginService;
	@RequestMapping(value = "/platform/user/login", method = RequestMethod.POST)
	public Result<Map> login(@RequestBody Map<String,String> map) {
		loginService.login(null);
		return null;
	}
	@RequestMapping(value = "/platform/user/vc", method = RequestMethod.GET)
	public void getVc(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
           
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //生成图片 
        int w = 100, h = 30; 
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode); 
	}
}
