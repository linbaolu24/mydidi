package cn.com.didi.app.deal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.trade.service.IWechatTradeService;
@RestController
public class WechatDealController extends AbstractDealController{
	
	@Resource
	protected IWechatTradeService wechatTradeService;
	@RequestMapping(value = "/app/c/order/wechatAsnyNotify", method = RequestMethod.POST)
	@ResponseBody
	public String wechatAsnyNotify(@RequestBody String requestStr){
		return requestStr;
		
	}
	public IResult wechatPay(@RequestBody OrderIDJAO map, HttpServletRequest request){
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IResult<WechatPayCustomerReturnVo> result=wechatTradeService.createOdrerRequest(orderId, accountId, map.getDescription());
		if(result.success()){
			WechatPayCustomerReturnVo returnVo=result.getData();
			Map<String,Object> resmap=new HashMap<>(4);
			resmap.put(DomainConstatns.DEALID, Long.parseLong(returnVo.getPartner_trade_no()));
			resmap.put(DomainConstatns.AMOUNT, returnVo.getCost());
			resmap.put(DomainConstatns.PREPAY_ID, returnVo.getPrepay_id());
		}
		return ResultFactory.error(result);
	}
	
	
}
