package cn.com.didi.order.trade.service.impl.wechat;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.core.property.IConverter;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.domains.WechatPayCustomerReqVo;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.domains.wechat.WechatPayContext;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.SignatureUtils;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.trade.service.ITradeTranscationCallBack;
import cn.com.didi.order.trade.service.ITradeTranscationCallBackFinder;
import cn.com.didi.order.trade.service.IWechatProvider;
import cn.com.didi.order.trade.service.IWechatTradeService;
import cn.com.didi.order.trade.service.IWechatTransferService;
import cn.com.didi.order.trade.util.TradeOperations;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;

@Service
public class WechatTradeServiceImpl implements IWechatTradeService {
	private static Logger LOGGER = LoggerFactory.getLogger(WechatTradeServiceImpl.class);
	@Resource
	protected IOrderService orderService;
	@Resource
	protected ITradeService tradeService;
	@Resource
	protected IAppEnv appProduct;
	@Resource
	protected IWechatTransferService wechatTransferService ;
	@Resource
	protected ITradeTranscationCallBackFinder finder;
	@Resource
	protected IOperationListener<TradeOperations, Object> wechatOperationListener;
	@Resource
	protected IWechatProvider wechatProvider;
	protected Map<String,String> mapName=new HashMap<>();
	{
		mapName.put("packageStr", "package");
	}
	protected IConverter<String, String> nameConvert=new IConverter<String, String>() {
		
		@Override
		public String convert(String source) {
			String value=mapName.get(source);
			if(StringUtils.isEmpty(value)){
				return source;
			}
			if("Y".equals(value)){
				return null;
			}
			return value;
			
		}
	};
	protected int dived = 1 << (31 - 1);
	protected IFilter<Field> field = new IFilter<Field>() {

		@Override
		public boolean filter(Field obj) {
			return Modifier.isStatic(obj.getModifiers());
		}
	};

	@Override
	public IResult<WechatPayContext> createOdrerRequest(Long orderId, Long bId, String desc) {
		IOrderRuslt<OrderDealDescDto> orderResult = orderService.createDeal(orderId, bId, PayAccountEnum.WECHATPAY,
				desc);
		if (orderResult != null && !orderResult.success()) {
			return ResultFactory.error(orderResult.getCode(), orderResult.getMessage());
		}
		try {
			WechatPayCustomerReqVo vo = generatorWechatPayRequest(orderResult.getData());//创建请求对象
			IResult<WechatPayContext>  result=payNoraml(vo);
			return result;
		} catch (Exception e) {
			LOGGER.error("" + e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_ERROR);
		}
	}
	protected WechatPayContext generatWechatPayContext(WechatPayCustomerReqVo vo,WechatPayCustomerReturnVo data) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		WechatPayContext context=new WechatPayContext();
		context.setAppid(vo.getAppid());
		context.setNoncestr(vo.getNonce_str());
		context.setPackageStr("Sign=WXPay");
		context.setPartnerid(vo.getMch_id());
		context.setPrepayid(data.getPrepay_id());
		context.setTimestamp(String.valueOf(System.currentTimeMillis()/1000));
		context.setSign(SignatureUtils.getPaySign(context, appProduct.getWechatAppSignedkey(),
				appProduct.getWechatCharSet(), field,nameConvert));
		return context;
	}
	protected IResult<WechatPayContext> payNoraml(WechatPayCustomerReqVo vo) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		IResult<WechatPayCustomerReturnVo>  result=wechatTransferService.transferAppPay(vo);
		if(result.success()){
			result.getData().setPartner_trade_no(vo.getPartner_trade_no());
		}
		WechatPayCustomerReturnVo returnVO=result.getData();
		if(returnVO.verifySuccess()){
			returnVO.setCost(vo.getAmount());
			WechatPayContext context=generatWechatPayContext(vo, returnVO);
			context.setWechatPayCustomerReturnVo(returnVO);
			return ResultFactory.success(context);
		}else{
			String msg=StringUtils.defaultIfEmpty(returnVO.errorMsg(),OrderMessageConstans.DEAL_WECHAT_TYXD_ERROR.getMessage());
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_ERROR.getCode(), msg);
		}
	}
	protected WechatPayCustomerReqVo generatorWechatPayRequest(OrderDealDescDto desc)
			throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		WechatPayCustomerReqVo requestDto=createNormalPayCustomerReqVo(desc.getCname());
		requestDto.setAmount(desc.getAmount());
		requestDto.setPartner_trade_no(String.valueOf(desc.getDealId()));
		requestDto.setSign(wechatTransferService.getAppPaySign(requestDto, appProduct.getWechatAppSignedkey(), appProduct.getWechatCharSet()));
		return requestDto;
	}
	protected WechatPayCustomerReqVo createNormalPayCustomerReqVo(String name) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		return createNormalPayCustomerReqVo(WechatEnum.APP, name);
	}
	protected WechatPayCustomerReqVo createNormalPayCustomerReqVo(WechatEnum type,String name) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException{
		String appId=wechatProvider.getAppId(type);
		//String appSecret=wechatProvider.getAppSecret(type);
		WechatPayCustomerReqVo requestDto = new WechatPayCustomerReqVo();
		requestDto.setAppid(appId);
		requestDto.setNonce_str(String.valueOf(RandomUtils.nextInt() & dived));//随机字符串
		requestDto.setMch_id(appProduct.getWechatMchId());
	
		requestDto.setSpbill_create_ip(appProduct.getIpAdress());
		requestDto.setNotify_url(appProduct.getWechatPayNotifyUrl());
		requestDto.setBody(appProduct.getAppName() + "-" +name);
		requestDto.setTrade_type(appProduct.getWechatTradeType());
		
		return requestDto;
	}
	
	@Override
	public IResult<WechatPayContext> createPayRequest(Long accountId, String type, String obj) {
		ITradeTranscationCallBack<DealDto> callBack = finder.findCreateTranscationalCallBack(accountId, type, obj);
		DealDto dto = new DealDto();
		dto.setSai(accountId);
		dto.setDat(PayAccountEnum.WECHATPAY.getCode());
		dto.setSat(PayAccountEnum.WECHATPAY.getCode());
		dto.setCreateTime(new Date());
		tradeService.createTrade(dto, callBack);
		try {
			WechatPayCustomerReqVo vo=createNormalPayCustomerReqVo(dto.getCment());
			vo.setPartner_trade_no(String.valueOf(dto.getDealId()));
			vo.setAmount(dto.getAmount());
			callBack.popForWechat(vo);
			vo.setSign(wechatTransferService.getAppPaySign(vo, appProduct.getWechatAppSignedkey(), appProduct.getWechatCharSet()));
			
			return payNoraml(vo);
		} catch (IllegalArgumentException | IllegalAccessException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(),e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_ERROR);
		}
	}
	
	
	public IResult<WechatPayNotifyReturnVO> asynnotify(String notifyStr) {
		return asynnotify(notifyStr, null, "订单异步支付");
	}
	
	public IResult<WechatPayNotifyReturnVO> asynnotify(String notifyStr,TranscationalCallBack<PayResultDto> callBack,String tag) {
		LOGGER.debug("Tag:{};====微信返回\n{}=====",tag,notifyStr);
		IResult<WechatPayNotifyReturnVO> notifyResult=wechatTransferService.parseNotifyResponse(notifyStr);
		if(!notifyResult.success()){
			LOGGER.error("Tag:{};解析微信返回失败 code{} message {}",tag, notifyResult.getCode(),notifyResult.getMessage());
		}
		WechatPayNotifyReturnVO returnVO=notifyResult.getData();
		wechatOperationListener.operate(TradeOperations.NOTIFY_START_WECHAT_ASYN, returnVO);
		returnVO.setSource(null);
		boolean verifySign=wechatTransferService.verifySign(returnVO);
		
		if(!verifySign){
			LOGGER.error("Tag:{};验证微信签名失败\n{}",tag,returnVO);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_PAY_NOTIFY_VERIFGY_SIGN_ERROR);
		}
		if(!returnVO.verifySuccess()){
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_PAY_NOTIFY_ERROR,returnVO);
		}
		if (callBack != null) {
			IResult<Void> result = finishOrderDeal(Long.parseLong(returnVO.getOut_trade_no()),
					Integer.parseInt(returnVO.getTotal_fee()), returnVO.getTransaction_id());
			if (result != null && !result.success()) {
				return ResultFactory.error(result.getCode(), result.getMessage());
			}
		} else {
			PayResultDto resultVo=getPayResultDto(returnVO);
			tradeService.finishDeal(resultVo, callBack);
		}
		return ResultFactory.success(returnVO);
	}
	
	
	@Override
	public IResult<WechatPayNotifyReturnVO> asynnotify(String type, String notifyStr) {
		TranscationalCallBack<PayResultDto> payResultCallBack = finder.findFinishTranscationalCallBack(type);
		return asynnotify(notifyStr, payResultCallBack, type);
	}
	/**
	 * @param map
	 * @return
	 */
	protected PayResultDto getPayResultDto(WechatPayNotifyReturnVO map){
		String dealId = map.getOut_trade_no();
		String cost = map.getTotal_fee();
		String trade_no=map.getTransaction_id();
		PayResultDto payResult = new PayResultDto();
		payResult.setDealId(Long.valueOf(dealId));
		payResult.setCost(Integer.parseInt(cost));
		payResult.setAccountEnum(PayAccountEnum.WECHATPAY);
		payResult.setTradeId(trade_no);
		return payResult;
	}
	protected IResult<Void> finishOrderDeal(Long dealId, Integer cost,String trade_no) {
		PayResultDto payResult = new PayResultDto();
		payResult.setDealId(dealId);
		payResult.setCost(cost);
		payResult.setAccountEnum(PayAccountEnum.WECHATPAY);
		payResult.setTradeId(trade_no);
		return orderService.finishDeal(payResult);
	}

	@Override
	public IResult<WechatPayCustomerReturnVo> sendTransForm(DealDto dto) {
		LOGGER.debug("微信提现,对象{}", dto);
		try {
			WechatPayCustomerReqVo requestDto = createNormalPayCustomerReqVo("提现");
			requestDto.setBody(null);
			requestDto.setTrade_type(null);
			requestDto.setAmount(dto.getAmount());
			requestDto.setPartner_trade_no(String.valueOf(dto.getDealId()));
			requestDto.setOpenid(dto.getDa());
			return wechatTransferService.transferForTransferAccounts(requestDto);
		} catch (IllegalArgumentException | IllegalAccessException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TRANSFER_BUILD_REQUEST_ERROR);
		}
	}
}
