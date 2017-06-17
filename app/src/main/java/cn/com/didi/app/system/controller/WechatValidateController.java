package cn.com.didi.app.system.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.order.trade.service.IWechatBaseService;

@RestController
public class WechatValidateController {

    private static final Logger LOG = Logger.getLogger(WechatValidateController.class);
    @Resource
    protected IWechatBaseService wechatBaseService;
    @RequestMapping(value = "/wechat/developer/validate", method = { RequestMethod.GET, RequestMethod.POST })
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
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        return wechatBaseService.valiadate(signature, timestamp, nonce, echostr);
    }

}
