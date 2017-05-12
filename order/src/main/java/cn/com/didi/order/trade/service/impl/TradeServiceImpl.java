package cn.com.didi.order.trade.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayAccountDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
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
	@Resource
	protected IAccountAssetsService accountAssetsService;

	@Override
	@Transactional
	public void createTrade(DealDto dto, TranscationalCallBack<DealDto> deal) {
		if(StringUtils.isEmpty(dto.getState())){
			dto.setState(DealEnum.WAITTING.getCode());
		}
		
		tradeInfoService.createTrade(dto, deal);
		//deal.invoke(dto);
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
		for (int i = 0; i < 3; i++) {
			DealDto deal = tradeInfoService.selectDeal(dealId);
			IResult<Void> result = dealExist(deal);
			if (result != null) {
				return result;
			}
			result = dealVerify(payResult, deal);
			if (result != null) {
				return result;
			}
			if (DealEnum.FINISH.getCode().equals(deal.getState())) {// 如果已经是成功状态的deal
				return null;
			}
			payResult.setDeal(deal);
			int count = tradeInfoService.finishDeal(deal, payResult, dealCallBack);
			if (count > 0) {
				return null;
			}
		}
		return null;//返回错误
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

	@Override
	public int fail(Long dealId, String cause) {
		return tradeInfoService.fail(dealId, cause);
	}

	@Override
	public IResult<Void> draw(DealDto pay) {
		PayAccountEnum accountEnum =ICodeAble.getCode(PayAccountEnum.values(), pay.getDat());
		MerchantDayRemainingDto dto=accountAssetsService.countRemain(pay.getDai(), accountEnum);
		if(dto==null||dto.getRemaining()<(long)pay.getAmount().intValue()){
			//返回账户余额不足
		}
		return null;
	}

	@Override
	public IResult<Void> pendingDraw(Long dealId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IResult<Void> rollBack(DealDto pay, boolean needLock) {
		// TODO Auto-generated method stub
		return null;
	}

}
