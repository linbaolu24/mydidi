package cn.com.didi.order.trade.service;

import java.util.List;

import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;

/**
 * 账户资产服务
 * @author xlm
 *
 */
public interface IAccountAssetsService {
	/**
	 * @param dto
	 */
	public void addMerchantDayRemainingDto(MerchantDayRemainingDto dto,boolean systemOnly);
	
	/**如果账户余额足够就发生修改
	 * @param dto
	 */
	public boolean decreMerchantDayRemainingIfCan(MerchantDayRemainingDto dto);
	/**
	 * 列出余额列表
	 * @param accountId
	 * @return
	 */
	public List<MerchantDayRemainingDto> listMerchantDay(Long accountId);
	/**
	 * 回滚账户余额
	 * @param dto
	 */
	public void rollBackMerchantDayRemainingDto(MerchantDayRemainingDto dto);
	/**
	 * 查询系统余额
	 * @return
	 */
	public MerchantDayRemainingDto selectSystemRemaining();
	/**
	 * @param accountId
	 * @return
	 */
	public List<MerchantDayRemainingDto> countRemain(Long accountId);
	/**
	 * @param accountId
	 * @param payAccountEnum
	 * @return
	 */
	public MerchantDayRemainingDto countRemain(Long accountId,PayAccountEnum payAccountEnum);
	
}
