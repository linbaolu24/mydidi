package cn.com.didi.order.trade.service;

import cn.com.didi.domain.util.DealEnum;

/**
 * 转账服务
 * @author xlm
 *
 */
public interface ITransFormService {
	public void audit(Long dealId,DealEnum deal);
}
