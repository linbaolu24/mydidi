package cn.com.didi.app.deal.controller;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.trade.service.ITradeService;

public class AbstractDealController {
	protected ITradeService tradeService;
	protected IOrderService orderService;
	protected  IResult<Void> finishOrderDeal(Long dealId,Integer cost){
		PayResultDto payResult=new PayResultDto();
		payResult.setDealId(dealId);
		payResult.setCost(cost);
		return orderService.finishDeal(payResult);
		
	}
}
