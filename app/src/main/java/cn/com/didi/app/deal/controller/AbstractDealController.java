package cn.com.didi.app.deal.controller;

import javax.annotation.Resource;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.webBase.util.IAccountResolver;

public class AbstractDealController {
	@Resource
	protected ITradeService tradeService;
	@Resource
	protected IOrderService orderService;
	@Resource
	protected IAccountResolver resolver;
	protected  IResult<Void> finishOrderDeal(Long dealId,Integer cost){
		PayResultDto payResult=new PayResultDto();
		payResult.setDealId(dealId);
		payResult.setCost(cost);
		return orderService.finishDeal(payResult);
		
		
	}
	protected void assertOrderId(Long orderId) {
		AssertUtil.assertNotNull(orderId, "订单号");
		AssertUtil.assertNotNegative(orderId, "订单号");
	}
}
