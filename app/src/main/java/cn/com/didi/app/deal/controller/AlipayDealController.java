package cn.com.didi.app.deal.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;

import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.domains.AliSynResultDto;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.DomainMessageConstans;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.trade.service.IAliTradeService;

@RestController
public class AlipayDealController extends AbstractDealController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlipayDealController.class);
	private static String ALI_PUBLICK_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwD3LHDruRIHM5FlHukMkQ1KQdtbTBchbkb2k07656m2yL1YAqjB9EVEAJHqN5AE1zwzZv1zhv8OJqzCVSSSAIu3Vlop/LlHOM7Ig1ss2EHjIfFrM45SFtpYNcWxF1S3kb0uol2BMbgYq/SHFxGwMicQn70Jl0+8iMnG5ZNZoqdVgqLV3yQ+vmkAzsURaLwOUPnQMgQz95gRH6wHX7zc2GuQYNs5W8RNxAFqiZTAH1JCLOfnWAq+wSyKdaFHErkWyHWcj3fipIQWz54ZQeu0ZZCGm/39HVkbc4YPiGW1ftDRsf1+H1IFcINemQTzFtYhMihdGBHYeRxmGU1THYSRy3QIDAQAB";
	private static final String SUCESS = "success";
	private static final String FAIL = "fail";
	private static final String OUT_TRADE_NO = "out_trade_no";
	private static final String TOTAL_AMOUNT = "total_amount";
	@Resource
	protected IAliTradeService aliTradeService;

	private static final BigDecimal YB = new BigDecimal(100);

	/*
	 * @RequestMapping(value = "/app/c/order/aliAsnyNotify", method =
	 * RequestMethod.POST) public String alipaySdkNotify(HttpServletRequest
	 * request) throws UnsupportedEncodingException { String charSet =
	 * request.getParameter("charset"); Enumeration<String> names =
	 * request.getParameterNames(); TreeMap map = new TreeMap(); String name;
	 * while (names.hasMoreElements()) { name = names.nextElement();
	 * map.put(name, URLDecoder.decode(request.getParameter(name), charSet)); }
	 * boolean isSuccess = false; try { isSuccess =
	 * AlipaySignature.rsaCheckV1(map, ALI_PUBLICK_KEY, charSet); } catch
	 * (Exception e) { LOGGER.error("阿里验证签名失败" + map.toString(), e); return
	 * FAIL; } // if(AliTrade) if (!isSuccess) { return FAIL; } try { if
	 * ("TRADE_SUCCESS".equalsIgnoreCase((String) map.get("trade_status"))) {
	 * IResult<Void> result = finishDeal(map); if (result != null &&
	 * !result.success()) { return FAIL; } }
	 * 
	 * return SUCESS; } catch (Exception e) { return FAIL; }
	 * 
	 * }
	 */
	@RequestMapping(value = "/app/c/order/aliAsnyNotify", method = RequestMethod.POST)
	public String alipaySdkNotify(HttpServletRequest request) throws UnsupportedEncodingException {
		LOGGER.debug("=================阿里支付异步通知=======================");
		logRequest(request);
		String charSet = request.getParameter("charset");
		Enumeration<String> names = request.getParameterNames();
		TreeMap map = new TreeMap();
		String name;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			map.put(name, request.getParameter(name));
		}
		LOGGER.debug("阿里支付异步通知结果  = 【{}】", map);
		try {
			IResult<Void> result = aliTradeService.asynnotify(map);
			if (result != null && !result.success()) {
				return FAIL;
			}
			return SUCESS;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return FAIL;
		}
	}
	protected void logRequest(HttpServletRequest request){
		if (LOGGER.isDebugEnabled()) {
			Enumeration<String> names = request.getParameterNames();
			String name;
			StringBuilder content = new StringBuilder();
			int i = 0;
			while (names.hasMoreElements()) {
				name = names.nextElement();
				content.append((i == 0 ? "" : "&") + name + "=" + request.getParameter(name));
				i++;
			}
			LOGGER.debug(content.toString());
		}
	}
	/*
	 * @RequestMapping(value = "/app/c/order/finishAlipay", method =
	 * RequestMethod.POST) public IResult alipayResukt(@RequestBody
	 * AliSynResultDto request) throws UnsupportedEncodingException { String
	 * resultStatus = request.getResultStatus(); if
	 * (!resultStatus.equals("9000") && !resultStatus.equals("5000")) {
	 * LOGGER.error("阿里返回失败{},对象为", resultStatus, request); return
	 * ResultFactory.error(DomainMessageConstans.CODE_DEAL_ALI_RESULT_FAIL,
	 * "阿里返回交易失败。"); } Map map = JSON.parseObject(request.getResult(),
	 * Map.class); String charSet = (String) map.get("charset"); boolean
	 * isSuccess = false; try { isSuccess = AlipaySignature.rsaCheckV1(map,
	 * ALI_PUBLICK_KEY, charSet); } catch (Exception e) {
	 * LOGGER.error("阿里验证签名失败" + map.toString(), e); return
	 * ResultFactory.error(DomainMessageConstans.CODE_DEAL_VERIFY_ALI_SIGN_FAIL,
	 * "阿里验证签名失败。"); } IResult<Void> result = finishDeal(map); if (result ==
	 * null || result.success()) { return ResultFactory.success(); } return
	 * ResultFactory.error(result.getCode(), result.getMessage()); }
	 */

	@RequestMapping(value = "/app/c/order/finishAlipay", method = RequestMethod.POST)
	public IResult alipayResukt(@RequestBody AliSynResultDto request) {
		IResult<Void> result = aliTradeService.synnotify(request);
		return result;
	}

	@RequestMapping(value = "/app/c/order/alipay", method = { RequestMethod.POST })
	public IResult alipay(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IResult<AliPAyRequestDto> or = aliTradeService.createOdrerRequest(orderId, accountId, map.getDescription());
		if (or.success()) {
			Map p = new HashMap(1);
			p.put(DomainConstatns.DEALID, or.getData().getDealId());
			p.put(DomainConstatns.ORDERINFO, or.getData().getOrderInfo());
			return ResultFactory.success(p);
		}

		return ResultFactory.error(or.getCode(), or.getMessage());
	}

	protected IResult<Void> finishDeal(Map map) {
		String dealId = (String) map.get(OUT_TRADE_NO);
		String cost = (String) map.get(TOTAL_AMOUNT);
		BigDecimal dCost = new BigDecimal(cost).multiply(YB);
		return finishOrderDeal(new Long(dealId), dCost.intValue());
	}

}
