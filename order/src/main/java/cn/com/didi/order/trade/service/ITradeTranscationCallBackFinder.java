package cn.com.didi.order.trade.service;

import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.order.trade.domain.DealDto;

public interface ITradeTranscationCallBackFinder {
	/**
	 * @param type
	 * @param key
	 * @return
	 */
	ITradeTranscationCallBack<DealDto> findCreateTranscationalCallBack(Long accountId,String type,String key);
	/**
	 * <p>需要自己根据PayResultDto 进行判断</p>
	 * @param type
	 * @param key
	 * @return
	 */
	TranscationalCallBack<PayResultDto> findFinishTranscationalCallBack(String type);
	/**
	 * @param type
	 * @return
	 */
	TranscationalCallBack<PayResultDto> findFailTranscationalCallBack(String type);
}
