package cn.com.didi.order.trade.service;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DepositDto;

public interface IDepositService {
	/**
	 * 查询DdepositList
	 * @param interval
	 * @return
	 */
	IPage<DepositDto> selectDepositList(TimeInterval interval);
	/**
	 * @param depositId
	 */
	public void refound(Long depositId);
	/**
	 * @param accountId
	 * @return
	 */
	boolean existDeposit(Long accountId);
	/**
	 * 计算押金
	 * @param accountId
	 * @return
	 */
	Long countDeposit(Long accountId);
	/**
	 * @param dto
	 */
	public void addDepositDto(DepositDto dto);
	/**
	 * @param depositId
	 * @param tradeId
	 */
	public void updateTradeId(Long depositId,Long tradeId);
	
	/**
	 * @param depositId
	 * @param tradeId
	 */
	public void updateTradeState(Long depositId,String tradeState);
	
}
