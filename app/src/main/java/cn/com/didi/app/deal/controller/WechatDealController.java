package cn.com.didi.app.deal.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.deal.domain.AliPayJAO;
import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.domains.wechat.WechatPayContext;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.trade.service.IWechatTradeService;

@RestController
public class WechatDealController extends AbstractDealController {
	private static final String SUCCESS = "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
	private static final String ERROR_START = "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[";
	private static final String ERROR_END = "]]></return_msg></xml>";
	private static final Logger LOGGER=LoggerFactory.getLogger(WechatDealController.class);
	@Resource
	protected IWechatTradeService wechatTradeService;

	@RequestMapping(value = "/app/c/order/wechatAsnyNotify", method = RequestMethod.POST)
	@ResponseBody
	public String wechatAsnyNotify(@RequestBody String requestStr) {
		LOGGER.debug("微信异步通知{}",requestStr);
		try {
			IResult<WechatPayNotifyReturnVO> result = wechatTradeService.asynnotify(requestStr);
			if (result.success()) {
				return SUCCESS;
			}else{
				LOGGER.error("支付返回错误:code{}, message{}",result.getCode(),result.getMessage());
				return ERROR_START+result.getMessage()+ERROR_END;
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ERROR_START+"异常"+ERROR_END;
		}

	}
	@RequestMapping(value = "/app/trade/{type}/wechatAsnyNotify", method = RequestMethod.POST)
	public String alipayTypedSdkNotify(@RequestBody String requestStr,@PathVariable(value="type") String type) throws UnsupportedEncodingException {
		LOGGER.debug("type:{}微信异步通知{}",type,requestStr);
		try {
			IResult<WechatPayNotifyReturnVO> result = wechatTradeService.asynnotify(type,requestStr);
			if (result.success()) {
				return SUCCESS;
			}else{
				LOGGER.error("支付返回错误:code{}, message{}",result.getCode(),result.getMessage());
				return ERROR_START+result.getMessage()+ERROR_END;
				
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			return ERROR_START+"异常"+ERROR_END;
		}
	}
	@RequestMapping(value="/app/c/order/wechatPay",method = RequestMethod.POST)
	public IResult orderWechatPay(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IResult<WechatPayContext> result = wechatTradeService.createOdrerRequest(orderId, accountId,
				map.getDescription());
		if (result.success()) {
			WechatPayContext returnVo = result.getData();
			Map maps=toMaps(returnVo);
			return ResultFactory.success(maps);
		}
		return ResultFactory.error(result);
	}
	@RequestMapping(value="/app/trade/pay/wechatPay",method = RequestMethod.POST)
	public IResult wechatPay(@RequestBody AliPayJAO map, HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		IResult<WechatPayContext> result = wechatTradeService.createPayRequest(accountId, map.getType(), map.getObj());
		if (result.success()) {
			WechatPayContext returnVo = result.getData();
			Map maps=toMaps(returnVo);
			return ResultFactory.success(maps);
		}
		return ResultFactory.error(result);
	}
	
	@RequestMapping(value="/app/trade/pay/testwechatPay",method = {RequestMethod.POST, RequestMethod.GET})
	public IResult wechatPay(HttpServletRequest request){
		Long accountId = 2L;
		IResult<WechatPayContext> result = wechatTradeService.createPayRequest(accountId, "deposit", null);
		if (result.success()) {
			WechatPayContext returnVo = result.getData();
			Map maps=toMaps(returnVo);
			return ResultFactory.success(maps);
		}
		return ResultFactory.error(result);
	}
	protected Map toMaps(WechatPayContext returnVo){
		Map<String, Object> resmap = new HashMap<>(4);
		resmap.put(DomainConstatns.DEALID, Long.parseLong(returnVo.getWechatPayCustomerReturnVo().getPartner_trade_no()));
		resmap.put(DomainConstatns.AMOUNT, returnVo.getWechatPayCustomerReturnVo().getCost());
		resmap.put(DomainConstatns.PREPAY_ID,returnVo.getPrepayid());
		resmap.put(DomainConstatns.PARTNERID,returnVo.getPartnerid());
		resmap.put(DomainConstatns.APPID,returnVo.getAppid());
		resmap.put(DomainConstatns.PACKAGEVALUE,returnVo.getPackageStr());
		resmap.put(DomainConstatns.TIMESTAMP,returnVo.getTimestamp());
		resmap.put(DomainConstatns.SIGN,returnVo.getSign());
		resmap.put(DomainConstatns.NONCESTR,returnVo.getNoncestr());
		LOGGER.debug("返回Map={}",resmap);
		return resmap;
	}

}
