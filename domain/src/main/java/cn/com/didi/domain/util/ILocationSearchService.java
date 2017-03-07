package cn.com.didi.domain.util;

import java.util.List;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;

public interface ILocationSearchService {
	public List<IReciverDto> list(Point poi,Integer slsId);
	public IReciverDto match(Point poi,Integer slsId);
}
