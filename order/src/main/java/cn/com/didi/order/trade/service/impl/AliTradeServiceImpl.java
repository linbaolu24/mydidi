package cn.com.didi.order.trade.service.impl;

import static cn.com.didi.domain.util.AlipayConstants.CHARSET;
import static cn.com.didi.domain.util.AlipayConstants.OUT_TRADE_NO;
import static cn.com.didi.domain.util.AlipayConstants.RESULTSTATUS_REPEAT;
import static cn.com.didi.domain.util.AlipayConstants.RESULTSTATUS_SUCCESS;
import static cn.com.didi.domain.util.AlipayConstants.RSA2;
import static cn.com.didi.domain.util.AlipayConstants.TOTAL_AMOUNT;
import static cn.com.didi.domain.util.AlipayConstants.TRADE_STATUS;
import static cn.com.didi.domain.util.AlipayConstants.TRADE_SUCCESS;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.Date;
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
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;

import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.domains.AliSynResultDto;
import cn.com.didi.domain.domains.AlipayTradeAppPayResponseDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.domains.ali.AlipayTransToAccountResponse;
import cn.com.didi.domain.util.AlipayConstants;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.IAliTradeService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.trade.service.ITradeTranscationCallBack;
import cn.com.didi.order.trade.service.ITradeTranscationCallBackFinder;
import cn.com.didi.order.trade.util.AliPayBuilder;
import cn.com.didi.order.trade.util.TradeOperations;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;

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
	@Resource
	protected ITradeTranscationCallBackFinder finder;
	@Resource
	protected IOperationListener<TradeOperations, Object> aliOperationListener;
	@Resource
	protected IAppEnv myAppEnv;
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
	private AlipayClient alipayClient ;
	
	@PostConstruct
	public void init() throws Exception {
		byte[] bkey = Base64.decodeBase64(priKey);
		key = SignUtil.getPrivateKeyFromPKCS8(Constans.RSA_ALG, bkey);
		byte[] bPubkey = Base64.decodeBase64(aliPubkey);
		aliPublicKey = SignUtil.getPublickKeyFromX509(Constans.RSA_ALG, bPubkey);
		alipayClient= new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",appId
				,priKey,"json","UTF-8",aliPubkey,"RSA2");

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
			failDeal(orderId, orderResult.getData().getDealId(), orderResult.getData().getDealDto(), "阿里订单生成签名失败", null);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_PAY_ORDERINFO_FAIL);
		}
	}

	protected AliPayBuilder createBuilder(OrderDealDescDto desc) {
		AliPayBuilder builder = createNormalBuilder();

		builder.bcbody(desc.getDescription());
		builder.bcsubject(desc.getCname());
		
		builder.bzout_trade_no(desc.getDealId());
		builder.totalAmount(desc.getAmount());
		builder.timestamp(desc.getDealTime());
	/*	builder.appid(appId);
		builder.seller_id(pId);
		builder.charset(Constans.CHARSET_UTF_8.toLowerCase());
		builder.notify_url(notifyUrl); */
		return builder;
	}
	
	protected AliPayBuilder createNormalBuilder() {
		AliPayBuilder builder = new AliPayBuilder(true, false);

		builder.appid(appId);
		builder.seller_id(pId);
		builder.charset(Constans.CHARSET_UTF_8.toLowerCase());
		builder.notify_url(notifyUrl); 
		return builder;
	}

	@Override
	public IResult<Void> asynnotify(Map<String, String> map) {
		
		IResult<Void> result = asynormalVerify(map);
		if (result != null) {
			return result;
		}
		if (TRADE_SUCCESS.equalsIgnoreCase((String) map.get(TRADE_STATUS))||AlipayConstants.TRADE_FINISHED.equalsIgnoreCase((String) map.get(TRADE_STATUS))) {
			LOGGER.debug("=====交易完成操作====");
			result = finishDeal(map);
			return result;
		}else{
			failDeal(map);
		}
		return ResultFactory.success();
	}

	protected <T> IResult asynormalVerify(Map<String, String> map) {
		aliOperationListener.operate(TradeOperations.NOTIFY_START_ALI_ASYN, map);
		String charset = StringUtils.defaultIfEmpty(map.get(CHARSET), Constans.CHARSET_UTF_8);
		boolean isSuccess = false;
		LOGGER.debug("异步通知验证签名");
		try {
			if (myAppEnv.passAliNotifySign()) {
				LOGGER.debug("异步允许验证阿里签名直接通过");
				isSuccess = true;
			} else {
				isSuccess = AlipaySignature.rsaCheckV1(map, aliPubkey, charset, map.get(AlipayConstants.SIGN_TYPE));
			}
		} catch (Exception e) {
			LOGGER.error("阿里异步通知验证签名失败 =   {}  ", map, e);
		}
		if (!isSuccess) {
			LOGGER.error("======验证签名失败===");
			return ResultFactory.error(OrderMessageConstans.DEAL_VERIFY_ALI_SIGN_FAIL);
		}
		return null;
	}

	public IResult<Map> synnotifyVerity(AliSynResultDto synResultDto){
		String resultStatus = synResultDto.getResultStatus();
		if (!RESULTSTATUS_SUCCESS.equals(resultStatus) && !RESULTSTATUS_REPEAT.equals(resultStatus)) {
			LOGGER.error("阿里返回失败{},对象为", resultStatus, synResultDto);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_RESULT_FAIL);
		}
		AlipayTradeAppPayResponseDto map=toAliResonse(synResultDto);
		Map resMap =JSON.parseObject(map.getAlipay_trade_app_pay_response(), Map.class);
		String charset = StringUtils.defaultIfEmpty((String) resMap.get(CHARSET), Constans.CHARSET_UTF_8);
		String sign=map.getSign();
		String signType = (String) map.getSign_type();
		boolean isSuccess = false;
		try {
			if (myAppEnv.passAliNotifySign()) {
				LOGGER.debug("同步允许验证阿里签名直接通过");
				isSuccess = true;
			} else {
				isSuccess = SignUtil.verify(getSignAlgFromSignType(signType), aliPublicKey,
						map.getAlipay_trade_app_pay_response().getBytes(charset), Base64.decodeBase64(sign)) || true;
			}
		} catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException | UnsupportedEncodingException e) {
			LOGGER.error("阿里同步通知验证签名失败 =   {}  ", map, e);
		} 
		if (!isSuccess) {
			LOGGER.error("验证阿里签名不通过 [{}],AlipayTradeAppPayResponseDto=[]",synResultDto,map);
			return ResultFactory.error(OrderMessageConstans.DEAL_VERIFY_ALI_SIGN_FAIL);
		}
		return ResultFactory.success(resMap);

	}

	@Override
	public IResult<Void> synnotify(AliSynResultDto synResultDto) {
		IResult<Map> verifyResult = synnotifyVerity(synResultDto);
		if (verifyResult != null && !verifyResult.success()) {
			return ResultFactory.error(verifyResult.getCode(), verifyResult.getMessage());
		}
		Map resMap = verifyResult.getData();
		IResult<Void> result = finishDeal(resMap);
		if (result == null || result.success()) {
			return ResultFactory.success();
		}
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
		String trade_no=(String) map.get(AlipayConstants.TRADE_NO);
		return finishOrderDeal(new Long(dealId), dCost.intValue(), trade_no);
	}
	protected IResult<Void> failDeal(Map map) {
		String dealId = (String) map.get(OUT_TRADE_NO);
		String cost = (String) map.get(TOTAL_AMOUNT);
		BigDecimal dCost = new BigDecimal(cost).multiply(Constans.YB);
		String trade_no=(String) map.get(AlipayConstants.TRADE_NO);
		String status=(String) map.get(TRADE_STATUS);
		IResult<Void>  result=failDeal(null, new Long(dealId), null, status, null);
		return result;
	}
	/**
	 * @param map
	 * @return
	 */
	protected PayResultDto getPayResultDto(Map map){
		String dealId = (String) map.get(OUT_TRADE_NO);
		String cost = (String) map.get(TOTAL_AMOUNT);
		BigDecimal dCost = new BigDecimal(cost).multiply(Constans.YB);
		String trade_no=(String) map.get(AlipayConstants.TRADE_NO);
		PayResultDto payResult = new PayResultDto();
		payResult.setDealId(Long.valueOf(dealId));
		payResult.setCost(dCost.intValue());
		payResult.setAccountEnum(PayAccountEnum.ALIPAY);
		payResult.setTradeId(trade_no);
		return payResult;
	}

	protected <T> IResult<T> failDeal(Long orderId,Long dealId,DealDto dto, String cause,TranscationalCallBack<PayResultDto> callback) {
		try {
			PayResultDto resultDto=new PayResultDto();
			resultDto.setOrderId(orderId);
			resultDto.setDeal(dto);
			resultDto.setCause(cause);
			resultDto.setDeal(dto);
			IResult result=tradeService.fail(resultDto,callback);
			LOGGER.debug("tradeService.fail 的结果为{},{}",result.getCode(),result.getMessage());
			return (IResult<T>) result;
		} catch (Exception e) {
			LOGGER.error("订单 {} 更新为失败 {} 时发生错误 ", dealId, cause, e);
			return ResultFactory.error(OrderMessageConstans.DEAL_UPDATE_FAIL_ERROR);
		}
	}

	protected IResult<Void> finishOrderDeal(Long dealId, Integer cost,String trade_no) {
		PayResultDto payResult = new PayResultDto();
		payResult.setDealId(dealId);
		payResult.setCost(cost);
		payResult.setAccountEnum(PayAccountEnum.ALIPAY);
		payResult.setTradeId(trade_no);
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
	protected AlipayTradeAppPayResponseDto toAliResonse(AliSynResultDto synResultDto){
		net.sf.json.JSONObject jo=net.sf.json.JSONObject.fromObject(synResultDto.getResult());
		AlipayTradeAppPayResponseDto map =new AlipayTradeAppPayResponseDto();
		map.setAlipay_trade_app_pay_response(jo.optString(AlipayConstants.ALIPAY_TRADE_APP_PAY_RESPONSE));
		map.setSign(jo.optString(AlipayConstants.SIGN));
		map.setSign_type(jo.optString(AlipayConstants.SIGN_TYPE));
		return map;
	}

	@Override
	public IResult<AliPAyRequestDto> createOdrerRequest(Long accountId, String type, String obj) {
		AliPayBuilder builder = createNormalBuilder();
		DealDto dto = new DealDto();
		dto.setSai(accountId);
		dto.setDat(PayAccountEnum.ALIPAY.getCode());
		dto.setSat(PayAccountEnum.ALIPAY.getCode());
		dto.setCreateTime(new Date());
		ITradeTranscationCallBack<DealDto> callBack = finder.findCreateTranscationalCallBack(accountId,type, obj);
		tradeService.createTrade(dto, callBack);
		try {
			builder.bzout_trade_no(dto.getDealId());
			builder.totalAmount(dto.getAmount());
			builder.timestamp(dto.getCreateTime());
			callBack.popForAli(builder);
			AliPAyRequestDto aliDto = builder.build(key);
			return ResultFactory.success(aliDto);
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException | SignatureException e) {
			LOGGER.error("阿里生成签名失败 = {},异常 = {}", dto, e);
			failDeal(null, dto.getDealId(), dto, type+"阿里订单生成签名失败", null);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_PAY_ORDERINFO_FAIL);
		}
	}

	@Override
	public IResult<Void> asynnotify(Map<String, String> map, String type) {
		IResult<Void> result = asynormalVerify(map);
		if (result != null) {
			return result;
		}
		if (TRADE_SUCCESS.equalsIgnoreCase((String) map.get(TRADE_STATUS))
				|| AlipayConstants.TRADE_FINISHED.equalsIgnoreCase((String) map.get(TRADE_STATUS))) {
			TranscationalCallBack<PayResultDto> payResultCallBack = finder.findFinishTranscationalCallBack(type);
			PayResultDto payResult = getPayResultDto(map);
			return tradeService.finishDeal(payResult, payResultCallBack);
		}else{
			failDeal(map);
		}
		return ResultFactory.success();
	}

	@Override
	public IResult<Void> synnotify(String type, AliSynResultDto map) {
		IResult<Map> verifyResult = synnotifyVerity(map);
		if (verifyResult != null && !verifyResult.success()) {
			return ResultFactory.error(verifyResult.getCode(), verifyResult.getMessage());
		}
		Map resMap = verifyResult.getData();
		TranscationalCallBack<PayResultDto> payResultCallBack = finder.findFinishTranscationalCallBack(type);
		PayResultDto payResult = getPayResultDto(resMap);
		return tradeService.finishDeal(payResult, payResultCallBack);
	}
	

	@Override
	public IResult<AlipayTransToAccountResponse> sendTransForm(DealDto dto) {
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
		model.setAmount(NumberUtil.intToDecimal2(dto.getAmount()));
		model.setOutBizNo(String.valueOf(dto.getDealId()));
		model.setPayeeType("ALIPAY_LOGONID");
		model.setPayeeAccount(dto.getDa());
		model.setPayerShowName("嘀嘀服务");
		model.setRemark("提现");
		request.setBizModel(model);
		try {
			AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
			AlipayTransToAccountResponse newResponse=new AlipayTransToAccountResponse();
			pop(response, newResponse);
			return ResultFactory.success(newResponse);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ResultFactory.error(OrderMessageConstans.DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION);
		}
	}
	protected void pop(AlipayFundTransToaccountTransferResponse response,AlipayTransToAccountResponse newResponse){
		newResponse.setBody(response.getBody());
		newResponse.setCode(response.getCode());
		newResponse.setMsg(response.getMsg());
		newResponse.setOrder_id(response.getOrderId());
		newResponse.setOut_biz_no(response.getOutBizNo());
		newResponse.setParams(response.getParams());
		newResponse.setPay_date(response.getPayDate());
		newResponse.setSub_code(response.getSubCode());
		newResponse.setSub_msg(response.getSubMsg());
	}

}
