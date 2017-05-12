package cn.com.didi.user.ad.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.util.AdCategoryEnum;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.DisplayPositionEnum;
import cn.com.didi.domain.util.State;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.ad.dao.mapper.AdDtoMapper;
import cn.com.didi.user.ad.dao.mapper.AdPicDtoMapper;
import cn.com.didi.user.ad.dao.mapper.AdReportDtoMapper;
import cn.com.didi.user.ad.dao.mapper.AdvertRecordDtoMapper;
import cn.com.didi.user.ad.dao.mapper.DpDtoMapper;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.AdPicDtoExample;
import cn.com.didi.user.ad.domain.AdReportDto;
import cn.com.didi.user.ad.domain.AdTimeInterval;
import cn.com.didi.user.ad.domain.AdvertRecordDto;
import cn.com.didi.user.ad.domain.DpDto;
import cn.com.didi.user.ad.domain.DpDtoExample;
import cn.com.didi.user.ad.service.IAdListener;
import cn.com.didi.user.ad.service.IAdService;
import cn.com.didi.user.ad.util.AdUtils;
@Service
public class AdServiceImpl implements  IAdService ,ApplicationListener<ContextRefreshedEvent>,ApplicationContextAware {
	private static final Logger LOGGER=LoggerFactory.getLogger(AdServiceImpl.class);
	@Resource
	protected AdDtoMapper adMapper;
	@Resource
	protected AdPicDtoMapper adPicMapper;
	@Resource
	protected DpDtoMapper dpMapper;
	@Resource
	protected AdReportDtoMapper reportMapper;
	
	protected ApplicationContext context;
	protected IAdListener listener;
	@Resource
	protected IAppEnv appEnv;
	@Resource
	protected AdvertRecordDtoMapper adRecordMapper;
	
	protected DpDtoExample stateExample;
	public AdServiceImpl(){
		stateExample =new DpDtoExample();
		DpDtoExample.Criteria cri= stateExample.createCriteria();
		cri.andStateEqualTo(State.VALID.getState());
	}
	@Override
	@Transactional
	public void add(AdDto adDto, List<AdPicDto> picList) {
		if(adDto==null){
			return;
		}
		adMapper.insertSelective(adDto);
		if(!CollectionUtils.isEmpty(picList)){
			for(int i=0;i<picList.size();i++){
				picList.get(i).setAdId(adDto.getAdId());
				adPicMapper.insertSelective(picList.get(i));
			}
		}
	}

	@Override
	public IPage<AdDto> selectAdPage(AdTimeInterval interval) {
		
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<AdDto> list = (PageList<AdDto>) adMapper.selectAdPage(interval,pageBounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	@Transactional
	public void updateState(List<IdStateDto> lists) {
		if(CollectionUtils.isEmpty(lists)){
			return ;
		}
		for(IdStateDto one:lists){
			adMapper.updateState(one);
		}
	}

	@Override
	public List<AdReportDto> report(AdTimeInterval interval) {
		if((interval.getEndTime().getTime()-interval.getStartTime().getTime())>=3*24*60*60*1000L){
			return reportMapper.selectReportDay(interval);
		}else{
			return reportMapper.selectReportHour(interval);
		}
	}

	@Override
	public Couple<AdDto, List<AdPicDto>> queryAd(Long accountId,DisplayPositionEnum display) {
		 Couple<AdDto, List<AdPicDto>> couple=queryAdReal(display);
		 if(couple!=null){
			 try{
				 addExposure(couple.getFirst().getAdId());
			 }catch(Exception e){
				 LOGGER.error(e.getMessage(),e);
			 }
		 }
		 return couple;
	}

	public Couple<AdDto, List<AdPicDto>> queryAdReal(DisplayPositionEnum display) {
		Calendar cal=Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		AdDto dto=adMapper.selectAd(display.getCode(), cal.getTime(), hour);
		if(dto==null){
			return null;
		}
		List<AdPicDto> pic=queryAdPicList(dto.getAdId());
		
		return new Couple<AdDto, List<AdPicDto>>(dto, pic);
	}
	public List<AdPicDto> queryAdPicList(Long adId){
		AdPicDtoExample example=new AdPicDtoExample();
		AdPicDtoExample.Criteria cri= example.createCriteria();
		cri.andAdIdEqualTo(adId);
		return adPicMapper.selectByExample(example);
	}

	public List<AdPicDto> queryAdPicList(List<Long> adList, DpDto dp) {
		AdPicDtoExample example = new AdPicDtoExample();
		AdPicDtoExample.Criteria cri = example.createCriteria();
		cri.andAdIdIn(adList);

		if (org.apache.commons.lang.StringUtils.isEmpty(dp.getPhoneType()) && dp.getWidth() != null
				&& dp.getHeight() != null) {
			cri.andWidthEqualTo(dp.getWidth());
			cri.andHeightEqualTo(dp.getHeight());
		}
		if (!StringUtils.isEmpty(dp.getPhoneType())) {
			cri.andPhoneTypeLike("%" + dp.getPhoneType().toUpperCase() + "%");
		}
		return adPicMapper.selectByExample(example);
	}

	@Override
	public List<DpDto> selectAllDp() {
		return dpMapper.selectByExample(stateExample);
	}

	@Transactional
	public void addExposure(Long adId) {
		adMapper.updateAddExposure(adId, 1);
		if (appEnv.isAdRtStatistic()) {
			AdReportDto reportDto = getReportDto(adId, true);
			reportMapper.insertOrupdateExposure(reportDto);
		}
	}
	
	
	@Transactional
	public void addClickRate(Long adId) {
		adMapper.updateAddClickRate(adId, 1);
		if (appEnv.isAdRtStatistic()) {
			AdReportDto reportDto = getReportDto(adId, false);
			reportMapper.updateClickRate(reportDto);
		}
	}

	public AdReportDto getReportDto(Long adId, boolean exporse) {
		AdReportDto report = new AdReportDto();
		report.setAdId(adId);
		if (exporse) {
			report.setExposure(1);
		} else {
			report.setClickRate(1);
		}
		String timeUnit=DateFormatUtils.format(new Date(), "yyyyMMddHH");
		report.setTimeuint(timeUnit);
		report.setExposureDate(timeUnit.substring(0, 8));
		return report;
	}
	@Override
	public List<Couple<AdDto, AdPicDto>> queryAdList(Long accountId,DpDto display) {
		if(display==null||StringUtils.isEmpty(display.getDisplayPosition())){
			return null;
		}
		Calendar cal=Calendar.getInstance();
		int hour=cal.get(Calendar.HOUR_OF_DAY);
		List<AdDto> adDto=adMapper.selectAdList(display.getDisplayPosition(), cal.getTime(), hour);
		if(!CollectionUtils.isEmpty(adDto)){
			return null;
		}
		List<Long> adList=AdUtils.getAdList(adDto);
		List<AdPicDto> adPic=queryAdPicList(adList,display);
		List<Couple<AdDto, AdPicDto>> couple= AdUtils.combine(adDto, adPic, null);
		if(listener!=null){
			listener.fireQueryAdList(accountId,display,couple);
		}
		return couple;
	}
	
	
	
	
	@Override
	@Transactional
	public void addExposure(List<Long> adId) {
		for(Long one:adId){
			addExposure(one);
		}
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(listener==null){
			Map<String, IAdListener> matchingBeans =
					BeanFactoryUtils.beansOfTypeIncludingAncestors(context, IAdListener.class, true, false);
			if(!MapUtils.isEmpty(matchingBeans)){
				listener=matchingBeans.values().iterator().next();
			}else{
				listener=new AdListenerAdapter();
			}
		}
	}
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context=applicationContext;
	}
	@Override
	public void addExposure(Long accountId, DpDto display, List<Long> adId) {
		if(adId!=null){
			AdvertRecordDto dto=new AdvertRecordDto();
			for(Long one:adId){
				AdUtils.popProperty(one, accountId, AdCategoryEnum.EXPOSURE, display, dto);
				addRecord(dto);
			}
		}
	}
	/**
	 * @param accountId
	 * @param display
	 * @param adId
	 */
	public void addExposure(Long accountId, DpDto display, Long adId){
		AdvertRecordDto dto=new AdvertRecordDto();
		AdUtils.popProperty(adId, accountId, AdCategoryEnum.EXPOSURE, display, dto);
		addRecord(dto);
	}

	public void addRecord(AdvertRecordDto dto) {
		if (dto == null || dto.getAdId() == null) {
			return;
		}
		boolean isClick = AdCategoryEnum.CLICK_RATE.codeEqual(dto.getCategory());
		if (isClick) {
			adMapper.updateAddClickRate(dto.getAdId(), 1);
		} else {
			adMapper.updateAddExposure(dto.getAdId(), 1);
		}
		if (appEnv.isAdRtStatistic()) {
			AdReportDto reportDto = getReportDto(dto.getAdId(), isClick);
			reportMapper.insertOrupdateExposure(reportDto);
		}
		adRecordMapper.insertSelective(dto);
	}

	public void addRecord(List<AdvertRecordDto> dto) {
		if (dto == null) {
			return;
		}
		for (int i = 0; i < dto.size(); i++) {
			addRecord(dto);
		}
	}
}
