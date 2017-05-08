package cn.com.didi.order.trade.service;

import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.order.trade.util.AliPayBuilder;

public interface ITradeTranscationCallBack<T> extends TranscationalCallBack<T>{
	void popForAli(AliPayBuilder builder);
}
