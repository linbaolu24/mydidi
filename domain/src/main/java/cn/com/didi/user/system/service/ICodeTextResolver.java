package cn.com.didi.user.system.service;

import java.util.List;

import cn.com.didi.order.trade.domain.DealDrawListDto;

public interface ICodeTextResolver {
	public void resolverDraw(List<DealDrawListDto> drawList);
}
