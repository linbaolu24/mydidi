package cn.com.didi.order.trade.service;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;

/**
 * @author xlm
 *
 */
public interface ITradeInfoService {
	/**
	 * @param dto
	 * @param deal
	 */
	public void createTrade(DealDto dto,TranscationalCallBack<DealDto> deal);
	
	/**
	 * @param dealId
	 * @return
	 */
	public DealDto selectDeal(Long dealId);
	/**
	 * @param pay
	 */
	public int finishDeal(DealDto source,PayResultDto pay, TranscationalCallBack<PayResultDto> deal);
	/**
	 * @param dealId
	 * @return
	 */
	Long selectOrderIdFromDeal(Long dealId);
	public IPage<DealListDto> selectTrades(TimeInterval interval);
	/**
	 * 交易失败
	 * @param dealId
	 * @param cause
	 */
	public int fail(Long dealId,String cause);
}
