package cn.com.didi.platform.trade.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.State;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.trade.service.ITransFormService;
import cn.com.didi.platform.trade.domain.TradeTimeInterval;

@RestController
public class PTradeController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PTradeController.class);
	@Resource
	protected ITradeService tradeService;
	@Resource
	protected ITradeInfoService tradeInfoService;
	@Resource
	protected IDepositService depositeService;
	@Resource
	protected ITransFormService transFormService;

	@RequestMapping(value = "/platform/f/list", method = { RequestMethod.POST })
	public IResult orderList(@RequestBody TradeTimeInterval timeInterval) {

		IPage<DealListDto> page = tradeService.selectTrades(timeInterval.toTimeInterval());
		return ResultExt.build(page);

	}

	@RequestMapping(value = "/platform/draw/list", method = { RequestMethod.POST })
	public IResult drawList(@RequestBody TimeInterval timeInterval) {
		if (!StringUtils.isEmpty(timeInterval.getKey())) {
			try {
				timeInterval.setId(Long.parseLong(timeInterval.getKey()));
			} catch (NumberFormatException e) {
				LOGGER.error(e.getMessage(), e);
				throw new IllegalArgumentException("非法订单号。");
			}
		}
		IPage<DealListDto> page =tradeInfoService.selectDraws(timeInterval);
		return ResultExt.build(page);
		
	}
	@RequestMapping(value = "/platform/draw/audit", method = { RequestMethod.POST })
	public IResult drawAudit(@RequestBody DealListDto dto){
		State states=	ICodeAble.getCode(State.values(), dto.getState()); 
		if(states==null){
			throw new IllegalArgumentException("无效的审核结果。");
		}
		AssertUtil.assertNotNullAppend(dto.getDealId(), "交易ID");
		transFormService.audit(dto.getDealId(), State.VALID==states?DealEnum.FINISH:DealEnum.NOT_PASSING, dto.getCause());
		return ResultFactory.success();
		
	}
	/*	*//**
			 * 押金列表
			 * 
			 * @param timeInterval
			 * @return
			 *//*
			 * @RequestMapping(value = "/platform/deposit/list", method = {
			 * RequestMethod.POST }) public IResult depositList(@RequestBody
			 * TimeInterval timeInterval) { IPage<DepositDto> page =
			 * depositeService.selectDepositList(timeInterval); return
			 * ResultExt.build(page);
			 * 
			 * }
			 */

}
