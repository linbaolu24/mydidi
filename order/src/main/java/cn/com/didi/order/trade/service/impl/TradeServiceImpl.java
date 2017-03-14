package cn.com.didi.order.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayAccountDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.util.OrderMessageConstans;

/**
 * 交易服务实现
 * 
 * @author xlm
 *
 */
@Service
public class TradeServiceImpl implements ITradeService {
	@Resource
	protected ITradeInfoService tradeInfoService;

	@Override
	@Transactional
	public void createTrade(DealDto dto, TranscationalCallBack<DealDto> deal) {
		tradeInfoService.createTrade(dto, deal);
	}

	@Override
	public PayAccountDto getAccountDto(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DealDto selectDeal(Long dealId) {
		return tradeInfoService.selectDeal(dealId);
	}

	@Override
	public IResult<Void> finishDeal(PayResultDto payResult, TranscationalCallBack<PayResultDto> dealCallBack) {
		Long dealId = payResult.getDealId();
		DealDto deal = tradeInfoService.selectDeal(dealId);
		IResult<Void> result = dealExist(deal);
		if (result != null) {
			return result;
		}
		result = dealVerify(payResult, deal);
		if (result != null) {
			return result;
		}
		if(DealEnum.FINISH.getCode().equals(deal.getState())){//如果已经是成功状态的deal
			return null;
		}
		tradeInfoService.finishDeal(deal,payResult, dealCallBack);
		return null;
	}

	protected <T> IResult<T> dealExist(DealDto deal) {
		if (deal == null) {
			//  订单不存在
			return ResultFactory.error(OrderMessageConstans.DEAL_NOT_EXIST);
		}
		return null;
	}

	protected <T> IResult dealVerify(PayResultDto payResult, DealDto deal) {

		if (payResult.getCost() == null || !payResult.getCost().equals(deal.getAmount())) {
			// 金额等不想等
			return ResultFactory.error(OrderMessageConstans.DEAL_ACCOUNT_NOT_EQUAL);
		}
		return null;
	}

	@Override
	public Long selectOrderIdFromDeal(Long dealId) {
		return tradeInfoService.selectOrderIdFromDeal(dealId);
	}

	@Override
	public IPage<DealListDto> selectTrades(TimeInterval interval) {
		return tradeInfoService.selectTrades(interval);
	}

}
