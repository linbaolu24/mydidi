package cn.com.didi.app.deal.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;

import cn.com.didi.app.deal.domain.AliResultJAO;

@RestController
public class AlipayDealController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlipayDealController.class);
	private static String ALI_PUBLICK_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwD3LHDruRIHM5FlHukMkQ1KQdtbTBchbkb2k07656m2yL1YAqjB9EVEAJHqN5AE1zwzZv1zhv8OJqzCVSSSAIu3Vlop/LlHOM7Ig1ss2EHjIfFrM45SFtpYNcWxF1S3kb0uol2BMbgYq/SHFxGwMicQn70Jl0+8iMnG5ZNZoqdVgqLV3yQ+vmkAzsURaLwOUPnQMgQz95gRH6wHX7zc2GuQYNs5W8RNxAFqiZTAH1JCLOfnWAq+wSyKdaFHErkWyHWcj3fipIQWz54ZQeu0ZZCGm/39HVkbc4YPiGW1ftDRsf1+H1IFcINemQTzFtYhMihdGBHYeRxmGU1THYSRy3QIDAQAB";
	private static final String SUCESS = "";
	private static final String FAIL = "";

	public String alipaySdkNotify(HttpServletRequest request) throws UnsupportedEncodingException {
		String charSet = request.getParameter("charset");
		Enumeration<String> names = request.getParameterNames();
		TreeMap map = new TreeMap();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			map.put(name, URLDecoder.decode(request.getParameter(name), charSet));
		}
		boolean isSuccess = false;
		try {
			isSuccess = AlipaySignature.rsaCheckV1(map, ALI_PUBLICK_KEY, charSet);
		} catch (Exception e) {
			LOGGER.error("阿里验证签名失败" + map.toString(), e);
			return FAIL;
		}
		if (!isSuccess) {
			return FAIL;
		}
		return SUCESS;

	}

	public String alipayResukt(@RequestBody AliResultJAO request) throws UnsupportedEncodingException {
		Map map = JSON.parseObject(request.getResult(), Map.class);
		String charSet = (String) map.get("charset");
		boolean isSuccess = false;
		try {
			isSuccess = AlipaySignature.rsaCheckV1(map, ALI_PUBLICK_KEY, charSet);
		} catch (Exception e) {
			LOGGER.error("阿里验证签名失败" + map.toString(), e);
			return FAIL;
		}
		if (!isSuccess) {
			return FAIL;
		}
		return SUCESS;

	}

}
