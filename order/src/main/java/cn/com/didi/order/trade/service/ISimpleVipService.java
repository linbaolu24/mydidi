package cn.com.didi.order.trade.service;

import cn.com.didi.domain.util.State;

/**
 * @author xlm
 *
 */
public interface ISimpleVipService {
	void updateState(Long accountId,Integer slsId,Long dealId,State state);
}
