package cn.com.didi.order.trade.service;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayAccountDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;

/**
 * @author xlm
 *
 */
public interface ITradeService {
	/**
	 * 转账
	 */
	//void transferAccounts();
	/**
	 * 收到钱
	 */
	//void charge();
	
	public void createTrade(DealDto dto,TranscationalCallBack<DealDto> deal);
	/**
	 * @param accountId
	 * @return
	 */
	public PayAccountDto getAccountDto(Long accountId);
	
	/**
	 * @param dealId
	 * @return
	 */
	public DealDto selectDeal(Long dealId);
	/**
	 * @param payResult
	 * @return
	 */
	public IResult<Void> finishDeal(PayResultDto payResult,TranscationalCallBack<PayResultDto> deal);
	/**
	 * 交易失败
	 * @param dealId
	 * @param cause
	 */
	public int fail(Long dealId,String cause);
	
	/**
	 * @param dealId
	 * @return
	 */
	public Long selectOrderIdFromDeal(Long dealId);
	
	public IPage<DealListDto> selectTrades(TimeInterval interval);
}
