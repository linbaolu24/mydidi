package cn.com.didi.order.trade.service.impl;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alipay.api.internal.util.AlipaySignature;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.domains.AliSynResultDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.util.DomainMessageConstans;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.trade.service.IAliTradeService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.trade.util.AliPayBuilder;
import cn.com.didi.order.util.OrderMessageConstans;

import static cn.com.didi.domain.util.AlipayConstants.*;

/**
 * @author xlm
 *
 */
@Service
public class AliTradeServiceImpl implements IAliTradeService {
	private static Logger LOGGER = LoggerFactory.getLogger(AliTradeServiceImpl.class);
	@Resource
	protected IOrderService orderService;
	@Resource
	protected ITradeService tradeService;
	@Value("${ali.pId}")
	private String pId;
	@Value("${ali.signType:RSA2}")
	private String signType = "RSA2";
	@Value("${ali.appId}")
	private String appId;
	@Value("${ali.notifyUrl}")
	private String notifyUrl;
	@Value("${ali.privateKey}")
	private String priKey;
	private PrivateKey key;
	@Value("${ali.aliPublicKey}")
	private String aliPubkey;
	private PublicKey aliPublicKey;

	@PostConstruct
	public void init() throws Exception {
		byte[] bkey = Base64.decodeBase64(priKey);
		key = SignUtil.getPrivateKeyFromPKCS8(Constans.RSA_ALG, bkey);
		byte[] bPubkey = Base64.decodeBase64(aliPubkey);
		aliPublicKey = SignUtil.getPublickKeyFromX509(Constans.RSA_ALG, bPubkey);
	}

	@Override
	public IResult<AliPAyRequestDto> createOdrerRequest(Long orderId, Long bId,String desc) {
		IOrderRuslt<OrderDealDescDto> orderResult = orderService.createDeal(orderId, bId, PayAccountEnum.ALIPAY,desc);
		if (orderResult != null && !orderResult.success()) {
			return ResultFactory.error(orderResult.getCode(), orderResult.getMessage());
		}
		AliPayBuilder builder = createBuilder(orderResult.getData());
		try {
			AliPAyRequestDto aliDto = builder.build(key);
			return ResultFactory.success(aliDto);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | SignatureException e) {
			LOGGER.error("订单生成签名失败 = {},异常 = {}", orderResult.getData(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_PAY_ORDERINFO_FAIL);
		}
		// builder.

	}

	protected AliPayBuilder createBuilder(OrderDealDescDto desc) {
		AliPayBuilder builder = new AliPayBuilder(true, true);

		builder.bcbody(desc.getDescription());
		builder.bcsubject(desc.getCname());
		builder.appid(appId);
		builder.seller_id(pId);
		builder.charset(Constans.CHARSET_UTF_8);
		builder.bzout_trade_no(desc.getDealId());
		builder.totalAmount(desc.getAmount());
		builder.timestamp(desc.getDealTime());
		return builder;
	}

	@Override
	public IResult<Void> asynnotify(Map<String, String> map) {
		String charset = StringUtils.defaultIfEmpty(map.get(CHARSET), Constans.CHARSET_UTF_8);
		boolean isSuccess = false;
		try {
			isSuccess = AlipaySignature.rsaCheckV1(map, aliPubkey, charset);
		} catch (Exception e) {
			LOGGER.error("阿里异步通知验证签名失败 =   {}  ", map, e);
		}
		if (!isSuccess) {
			return ResultFactory.error(OrderMessageConstans.DEAL_VERIFY_ALI_SIGN_FAIL);
		}
		if (TRADE_SUCCESS.equalsIgnoreCase((String) map.get(TRADE_STATUS))) {
			IResult<Void> result = finishDeal(map);
			return result;
		}
		return ResultFactory.success();

	}

	@Override
	public IResult<Void> synnotify(AliSynResultDto synResultDto) {
		String resultStatus = synResultDto.getResultStatus();
		if (!RESULTSTATUS_SUCCESS.equals(resultStatus) && !RESULTSTATUS_REPEAT.equals(resultStatus)) {
			LOGGER.error("阿里返回失败{},对象为", resultStatus, synResultDto);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_RESULT_FAIL);
		}
		Map map = JSON.parseObject(synResultDto.getResult(), Map.class);
		String response = (String) map.get(ALIPAY_TRADE_APP_PAY_RESPONSE);
		Map resMap = JSON.parseObject(response, Map.class);
		String charset = StringUtils.defaultIfEmpty((String) resMap.get(CHARSET), Constans.CHARSET_UTF_8);
		String sign = (String) map.get(SIGN);
		String signType = (String) map.get(SIGN_TYPE);
		boolean isSuccess = false;
		try {
			isSuccess = SignUtil.verify(getSignAlgFromSignType(signType), aliPublicKey, response.getBytes(charset),
					Base64.decodeBase64(sign));
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | UnsupportedEncodingException e) {
			LOGGER.error("阿里同步通知验证签名失败 =   {}  ", map, e);
		}
		if (!isSuccess) {
			return ResultFactory.error(OrderMessageConstans.DEAL_VERIFY_ALI_SIGN_FAIL);
		}
		IResult<Void> result = finishDeal(map);
		return result;
	}

	protected String getSignAlgFromSignType(String signType) {
		String alg = Constans.SHA1_WITH_RSA;
		if (RSA2.equals(signType)) {
			alg = Constans.SHA256_WITH_RSA;
		}
		return alg;
	}

	protected IResult<Void> finishDeal(Map map) {
		String dealId = (String) map.get(OUT_TRADE_NO);
		String cost = (String) map.get(TOTAL_AMOUNT);
		BigDecimal dCost = new BigDecimal(cost).multiply(Constans.YB);
		return finishOrderDeal(new Long(dealId), dCost.intValue());
	}

	protected void failDeal(Long dealId, String cause) {
		try {
			tradeService.fail(dealId, cause);
		} catch (Exception e) {
			LOGGER.error("订单 {} 更新为失败 {} 时发生错误 ", dealId, cause, e);
		}
	}

	protected IResult<Void> finishOrderDeal(Long dealId, Integer cost) {
		PayResultDto payResult = new PayResultDto();
		payResult.setDealId(dealId);
		payResult.setCost(cost);
		return orderService.finishDeal(payResult);

	}

	public IOrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(IOrderService orderService) {
		this.orderService = orderService;
	}

	public ITradeService getTradeService() {
		return tradeService;
	}

	public void setTradeService(ITradeService tradeService) {
		this.tradeService = tradeService;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public PrivateKey getKey() {
		return key;
	}

	public void setKey(PrivateKey key) {
		this.key = key;
	}

}
