package cn.com.didi.app.system.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.order.trade.service.IWechatBaseService;

@Controller
public class WechatValidateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatValidateController.class);
    @Resource
    protected IWechatBaseService wechatBaseService;
    @RequestMapping(value = "/wechat/developer/validate", method = { RequestMethod.GET, RequestMethod.POST },produces={"text/plain"})
    @ResponseBody
    public String developerValidate(@RequestParam Map<?, ?> parms, HttpServletRequest request,HttpServletResponse  response){
       
        /**
         *  基于微信公众号开发，首先需要通过微信来验证成为开发者，  开发者通过检验signature对请求进行校验（下面有校验方式）。
         *  若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
         *   
         *  加密/校验流程如下：
         *   1. 将token、timestamp、nonce三个参数进行字典序排序
         *   2. 将三个参数字符串拼接成一个字符串进行sha1加密
         *   3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
         */
        // 获取微信返回的数据
    	LOGGER.debug("微信验证参数为:{}",parms);
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String returnStr= wechatBaseService.valiadate(signature, timestamp, nonce, echostr);
    	LOGGER.debug("微信验证返回参数为:{}和echostr比较为{}",returnStr,echostr.equals(returnStr));
    	return returnStr;
    }

}
