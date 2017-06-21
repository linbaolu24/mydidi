package cn.com.didi.app.system.controller;

import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.WechatConsts;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.service.IWechatBaseService;

@Controller
public class WechatValidateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WechatValidateController.class);
	@Resource
	protected IWechatBaseService wechatBaseService;

	@RequestMapping(value = "/wechat/developer/validate", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = { "text/plain" })
	@ResponseBody
	public String developerValidate(@RequestParam Map<?, ?> parms, HttpServletRequest request,
			HttpServletResponse response) {
		LOGGER.debug("微信验证参数为:{}", parms);
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		boolean msg = StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)
				|| StringUtils.isEmpty(echostr);
		if (msg) {
			LOGGER.debug("收到微信消息。");
			try {
				String postData = parseHttpRequestToStr(request);
				LOGGER.debug("收到微信消息内容{}。", postData);
				if (!StringUtils.isEmpty(postData)) {
					wechatBaseService.message(postData, WechatEnum.OPEN);
				}

			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		}
		if (msg) {
			LOGGER.debug("处理微信消息结束。");
			return WechatConsts.SUCCESS;
		}
		/**
		 * 基于微信公众号开发，首先需要通过微信来验证成为开发者， 开发者通过检验signature对请求进行校验（下面有校验方式）。
		 * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
		 * 
		 * 加密/校验流程如下： 1. 将token、timestamp、nonce三个参数进行字典序排序 2.
		 * 将三个参数字符串拼接成一个字符串进行sha1加密 3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		 */
		// 获取微信返回的数据

		String returnStr = wechatBaseService.valiadate(signature, timestamp, nonce, echostr);
		LOGGER.debug("微信验证返回参数为:{}和echostr比较为{}", returnStr, echostr.equals(returnStr));
		return returnStr;
	}

	@RequestMapping(value = "/wechat/developer/test", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public IResult test(@RequestBody Map<String, String> userInfo, HttpServletRequest request,
			HttpServletResponse response) {
		String code = userInfo.get("code");
		String accountId = userInfo.get("accountId");
		if (!StringUtils.isEmpty(code)) {
			UserWechatDto dto = wechatBaseService.getUserInfo(Long.parseLong(accountId), code);
			return ResultFactory.success(dto);
		}
		String xml = userInfo.get("xml");
		if (!StringUtils.isEmpty(xml)) {
			String dto = wechatBaseService.message(xml, WechatEnum.OPEN);
			return ResultFactory.success(dto);
		}
		return ResultFactory.success();

	}

	/**
	 * 将request请求转换成字符串
	 * 
	 * @param request
	 * @return
	 */
	public static final String parseHttpRequestToStr(HttpServletRequest request) {
		String reqStr = null;
		int len = request.getContentLength();
		if (len <= 0) {
			LOGGER.error("request请求头为空");
			return null;
		}
		try {
			InputStream inputStream = request.getInputStream();
			byte[] bytes = new byte[len];
			inputStream.read(bytes);
			reqStr = new String(bytes, WechatConsts.WECHAT_CHARSET);
		} catch (Exception ex) {
			LOGGER.error("微信请求转换成字符串出错:", ex);
			return null;
		}
		LOGGER.debug("微信请求转换成字符串结果为:{}" + reqStr);

		return reqStr;
	}

}
