package cn.com.didi.order.trade.service;

import java.util.Map;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.domains.AliSynResultDto;
import cn.com.didi.domain.domains.ali.AlipayTransToAccountResponse;
import cn.com.didi.order.trade.domain.DealDto;

public interface IAliTradeService {
	/**
	 * @param orderId
	 * @param bId
	 * @return
	 */
	IResult<AliPAyRequestDto> createOdrerRequest(Long orderId, Long bId,String desc);
	/**
	 * @param map
	 * @return
	 */
	IResult<Void> asynnotify(Map<String,String> map);
	/**
	 * @param map
	 * @return
	 */
	IResult<Void> asynnotify(Map<String,String> map,String type);
	/**
	 * @param map
	 * @return
	 */
	IResult<Void> synnotify(AliSynResultDto map);
	/**
	 * @param map
	 * @return
	 */
	IResult<Void> synnotify(String type,AliSynResultDto map);
	/**
	 * @param orderId
	 * @param bId
	 * @return
	 */
	IResult<AliPAyRequestDto> createOdrerRequest(Long accountId,String type,String obj);
	/**
	 * 发送转账到支付宝
	 * @param dto
	 * @return
	 */
	public IResult<AlipayTransToAccountResponse> sendTransForm(DealDto dto);
}
