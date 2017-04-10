package cn.com.didi.domain.util;

import java.util.List;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;

public interface IReciverSearchService {
	/**
	 * 列出附近商家接收者
	 * @param poi
	 * @param slsId
	 * @return
	 */
	public List<IReciverDto> list(Point poi,Integer slsId);
	/**
	 * 匹配附近商家接收者
	 * @param areaCode
	 * @param poi
	 * @param slsId
	 * @return
	 */
	public IReciverDto match(String areaCode,Point poi,Integer slsId);
	
	/**
	 * 从账户ID获取接收者
	 * @param accoutId
	 * @return
	 */
	public IReciverDto match(Long accoutId,Role role);
}
