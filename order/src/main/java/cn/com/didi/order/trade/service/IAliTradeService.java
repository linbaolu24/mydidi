package cn.com.didi.order.trade.service;

import java.util.Map;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.domain.domains.AliSynResultDto;

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
}
