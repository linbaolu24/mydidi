package cn.com.didi.order.trade.service;

import java.util.List;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.order.trade.domain.DealDrawListDto;
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
	/**
	 * @param interval
	 * @return
	 */
	public IPage<DealListDto> selectTrades(TimeInterval interval);
	/**
	 * 主要用于平台端查询体现列表
	 * @param interval
	 * @return
	 */
	public IPage<DealListDto> selectDraws(TimeInterval interval);
	/**
	 * 交易失败
	 * @param dealId
	 * @param cause
	 */
	public int fail(Long dealId,String cause);
	/**
	 * 主要用于商户端查询提现列表
	 * @param interval
	 * @return
	 */
	public List<DealDrawListDto> selectDrawList(TimeInterval interval);
	/**
	 * 审核通过
	 * @param dealId
	 * @return
	 */
	public int auditing(DealDto dealId);
	/**
	 * @param deal
	 */
	public void rollBack(DealDto deal);
	/**
	 * @param pay
	 */
	public void draw(DealDto pay);
	/**
	 * @param dealId
	 * @param dest
	 * @param cat 分类
	 * @param source
	 * @return
	 */
	public int updateTradeState(Long dealId,String dest,String cat,String... source);
}
