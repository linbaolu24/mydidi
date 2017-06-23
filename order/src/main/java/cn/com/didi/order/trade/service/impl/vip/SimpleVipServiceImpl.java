package cn.com.didi.order.trade.service.impl.vip;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.domain.util.State;
import cn.com.didi.order.trade.dao.mapper.TemporaryMapper;
import cn.com.didi.order.trade.service.ISimpleVipService;
@Service
public class SimpleVipServiceImpl implements ISimpleVipService{
	@Resource
	protected TemporaryMapper temporaryMapper;
	@Override
	public void updateState(Long accountId,Integer slsId,Long dealId,State state) {
		temporaryMapper.updateVipState(accountId, slsId, dealId, state.getState());
	}
	
}
