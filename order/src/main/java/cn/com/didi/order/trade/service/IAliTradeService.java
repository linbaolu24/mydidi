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
	IResult<AliPAyRequestDto> createOdrerRequest(Long orderId, Long bId);
	IResult<Void> asynnotify(Map<String,String> map);
	IResult<Void> synnotify(AliSynResultDto map);
}
