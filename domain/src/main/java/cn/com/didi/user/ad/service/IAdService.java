package cn.com.didi.user.ad.service;

import java.util.List;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.util.DisplayPositionEnum;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.AdReportDto;
import cn.com.didi.user.ad.domain.AdTimeInterval;
import cn.com.didi.user.ad.domain.DpDto;

public interface IAdService {
	/**
	 * @param adDto
	 * @param picList
	 */
	public void add(AdDto adDto,List<AdPicDto> picList);
	/**
	 * @param timeInerval
	 * @return
	 */
	public IPage<AdDto> selectAdPage(AdTimeInterval timeInerval);
	/**
	 * @param lists
	 */
	public void updateState(List<IdStateDto> lists);
	/**
	 * @param interval
	 * @return
	 */
	public List<AdReportDto> report(AdTimeInterval interval);
	/**
	 * @param display
	 * @return
	 */
	public Couple<AdDto, List<AdPicDto>> queryAd(Long accountId,DisplayPositionEnum display);
	
	

	
	/**
	 * @param display
	 * @return
	 */
	public List<Couple<AdDto, AdPicDto>> queryAdList(Long accountId,DpDto  display);
	/**
	 * @return
	 */
	public List<DpDto> selectAllDp();
	/**
	 * @param adId
	 */
	public void addExposure(List<Long> adId);
	
	/**
	 * @param adId
	 */
	public void addExposure(Long accountId, DpDto display,List<Long> adId);
}
