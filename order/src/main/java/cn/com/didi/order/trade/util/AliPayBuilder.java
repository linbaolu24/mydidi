package cn.com.didi.order.trade.util;

import static cn.com.didi.domain.util.AlipayConstants.APP_ID;
import static cn.com.didi.domain.util.AlipayConstants.BIZ_CONTENT;
import static cn.com.didi.domain.util.AlipayConstants.BODY;
import static cn.com.didi.domain.util.AlipayConstants.CHARSET;
import static cn.com.didi.domain.util.AlipayConstants.FORMAT;
import static cn.com.didi.domain.util.AlipayConstants.METHOD;
import static cn.com.didi.domain.util.AlipayConstants.NOTIFY_URL;
import static cn.com.didi.domain.util.AlipayConstants.OUT_TRADE_NO;
import static cn.com.didi.domain.util.AlipayConstants.PRODUCT_CODE;
import static cn.com.didi.domain.util.AlipayConstants.RSA2;
import static cn.com.didi.domain.util.AlipayConstants.SELLER_ID;
import static cn.com.didi.domain.util.AlipayConstants.SIGN;
import static cn.com.didi.domain.util.AlipayConstants.SIGN_TYPE;
import static cn.com.didi.domain.util.AlipayConstants.SUBJECT;
import static cn.com.didi.domain.util.AlipayConstants.TIMESTAMP;
import static cn.com.didi.domain.util.AlipayConstants.TOTAL_AMOUNT;
import static cn.com.didi.domain.util.AlipayConstants.VERSION;
import static cn.com.didi.domain.util.AlipayConstants.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.util.AlipayConstants;

public class AliPayBuilder {
	private static final Logger LOGGER=LoggerFactory.getLogger(AliPayBuilder.class);
	private TreeMap<String, String> treeMap;

	private TreeMap<String, String> bizContent;
	private AliPAyRequestDto requestDto;
	private String rsaAL;

	public AliPayBuilder() {
		treeMap = new TreeMap();
		bizContent = new TreeMap();
		requestDto = new AliPAyRequestDto();
	}

	public AliPayBuilder(boolean isDefault, boolean isDefaultTime) {
		this();
		if (isDefault) {
			AliPayBuilder builder = this;
			builder.signType("RSA2");
			builder.version("1.0");
			//builder.format("json");
			builder.charset(Constans.CHARSET_UTF_8.toLowerCase());
			if (isDefaultTime) {
				builder.timestamp(new Date());
			}
			builder.method("alipay.trade.app.pay");
			//builder.bcbody("服务");
			//builder.bcsubject("服务");
			builder.product_code("QUICK_MSECURITY_PAY");
		}
	}

	public void appid(String appId) {
		treeMap.put(APP_ID, appId);

	}

	public void method(String method) {
		treeMap.put(METHOD, method);

	}

	public void format(String format) {
		treeMap.put(FORMAT, format);
	}

	public void charset(String charset) {
		treeMap.put(CHARSET, charset);
	}

	public void timestamp(Date date) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = simple.format(date);
		treeMap.put(TIMESTAMP, dateStr);
	}

	public void signType(String signType) {
		treeMap.put(SIGN_TYPE, signType);
		this.rsaAL = signType;
	}

	public void version(String version) {
		treeMap.put(VERSION, version);
	}

	public void notify_url(String notifyUrl) {
		treeMap.put(NOTIFY_URL, notifyUrl);
		requestDto.setNotifyUrl(notifyUrl);
	}

	public void bcbody(String body) {
		if(StringUtils.isEmpty(body)){
			return;
		}
		bizContent.put(BODY, body);
	}

	public void bcsubject(String subject) {
		if(StringUtils.isEmpty(subject)){
			return;
		}
		bizContent.put(SUBJECT, subject);
	}

	public void bzout_trade_no(Long no) {
		bizContent.put(OUT_TRADE_NO, String.valueOf(no));
		requestDto.setDealId(no);
	}

	public void totalAmount(Integer interget) {
		String value = NumberUtil.intToDecimal2(interget);
		bizContent.put(TOTAL_AMOUNT, value);
		requestDto.setCost(interget);
	}

	public void product_code(String pCode) {
		bizContent.put(PRODUCT_CODE, pCode);
	}

	public void seller_id(String sellerId) {
		bizContent.put(SELLER_ID, sellerId);
		requestDto.setPartnerId(sellerId);
	}

	public AliPAyRequestDto build(PrivateKey privateKey)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, SignatureException {

		String str = JSON.toJSONString(bizContent);
		treeMap.put(BIZ_CONTENT, str);
		String toSign = getSignContent(treeMap);
		LOGGER.debug("待签名字符串为 = {}  ",toSign);
		String chareSet = StringUtils.defaultIfBlank(requestDto.getCharset(), AlipayConstants.DEFAULT_CHARSET);
		byte[] btoSign = toSign.getBytes();
		String signed = sign(btoSign, privateKey);
		LOGGER.debug("签名结果为 = {}",signed);
		requestDto.setSign(signed);
		treeMap.put(SIGN, signed);
		String orderInfo = getUrlEncodeContent(treeMap, chareSet);
		requestDto.setOrderInfo(orderInfo);
		return requestDto;

	}

	public String sign(byte[] toSign, PrivateKey privateKey)
			throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
		String alg = Constans.SHA1_WITH_RSA;
		if (RSA2.equals(rsaAL)) {
			alg =  Constans.SHA256_WITH_RSA;
		}
		byte[] signed = SignUtil.sign(alg, privateKey, toSign);
		return Base64.encodeBase64String(signed);

	}

	public static String getSignContent(Map<String, String> sortedParams) {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = sortedParams.get(key);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				content.append((index == 0 ? "" : "&")).append(key).append("=").append(value);
				index++;
			}
		}
		return content.toString();
	}

	public static String getUrlEncodeContent(Map<String, String> sortedParams, String charset)
			throws UnsupportedEncodingException {
		StringBuffer content = new StringBuffer();
		List<String> keys = new ArrayList<String>(sortedParams.keySet());
		Collections.sort(keys);
		int index = 0;
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = sortedParams.get(key);
			if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
				content.append((index == 0 ? "" : "&")).append(key).append("=")
						.append(URLEncoder.encode(value, charset));
				index++;
			}
		}
		return content.toString();
	}
}
