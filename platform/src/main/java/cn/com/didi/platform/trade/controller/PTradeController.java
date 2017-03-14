package cn.com.didi.platform.trade.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.platform.trade.domain.TradeTimeInterval;

@RestController
public class PTradeController {
	@Resource
	protected ITradeService tradeService;

	@RequestMapping(value = "/platform/f/list", method = { RequestMethod.POST })
	public IResult orderList(@RequestBody TradeTimeInterval timeInterval) {

		IPage<DealListDto> page = tradeService.selectTrades(timeInterval.toTimeInterval());
		return ResultExt.build(page);

	}
}
