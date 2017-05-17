package cn.com.didi.user.item.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.BusinessCharge;
import cn.com.didi.domain.util.ServiceState;
import cn.com.didi.domain.util.VisualMark;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.item.dao.mapper.FlServiceDtoMapper;
import cn.com.didi.user.item.dao.mapper.SlServiceDtoMapper;
import cn.com.didi.user.item.dao.mapper.SlsCityDtoMapper;
import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.FlServiceItemDto;
import cn.com.didi.user.item.domain.FlsItemDto;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.domain.SlServiceDtoExample;
import cn.com.didi.user.item.domain.SlsCityDto;
import cn.com.didi.user.item.domain.SlsCityDtoKey;
import cn.com.didi.user.item.domain.SlsItemDto;
import cn.com.didi.user.item.domain.SlsSericeExtDto;
import cn.com.didi.user.item.service.IItemService;

@Service
public class ItemServiceImpl implements IItemService {
	@Resource
	protected FlServiceDtoMapper flsMapper;
	@Resource
	protected SlServiceDtoMapper slsMapper;
	@Resource
	protected SlsCityDtoMapper slsCityapper;

	@Override
	public IPage<FlServiceDto> selectFls(TimeInterval interval) {
		PageBounds bounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<FlServiceDto> list = (PageList<FlServiceDto>) flsMapper.selectFls(interval, bounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	public IPage<SlServiceDto> selectSls(TimeInterval interval) {
		PageBounds bounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<SlServiceDto> list = (PageList<SlServiceDto>) slsMapper.selectSls(interval, bounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	@Transactional
	public void addFlService(FlServiceDto flServiceDto) {
		if (flServiceDto == null) {
			return;
		}
		if (flServiceDto.getCreateTime() == null) {
			flServiceDto.setCreateTime(new Date());
		}
		if(flServiceDto.getDisplayOrder()==null){
			flServiceDto.setDisplayOrder(0);
		}
		Integer sId = flServiceDto.getServiceId();
		flServiceDto.setServiceId(null);
		flServiceDto.setSlsNum(0);
		flsMapper.insertSelective(flServiceDto);
		if (sId != null) {
			flsMapper.updateDisplayOrder(flServiceDto.getServiceId(), flServiceDto.getDisplayOrder());
		}
	}

	@Override
	public List<FlServiceDto> selectAllFlService() {
		return flsMapper.selectAllFlService();
	}
	
	public List<FlServiceDto> selectAllFlService(String state) {
		return flsMapper.selectAllStateFlService(state);
	}
	
	

	@Override
	public Integer selectMaxFlsDisplayOrder() {
		Integer order= flsMapper.selectMaxFlsDisplayOrder();
		return order==null?0:order;
	}

	@Override
	public Integer selectMaxSlsDisplayOrder(Integer flsId) {
		Integer order= slsMapper.selectMaxSlsDisplayOrder(flsId);
		return order==null?0:order;
	}

	@Override
	@Transactional
	public void addSlsService(SlsSericeExtDto dto) {
		if (dto == null) {
			return;
		}
		addSlsService(dto.dto(), dto.getCityList());
	}

	@Override
	@Transactional
	public void addSlsService(SlServiceDto dto, List<SlsCityDto> cityList) {
		
		if (dto == null) {
			return;
		}
		if(StringUtils.isEmpty(dto.getBusinessCategory())){
			dto.setBusinessCategory(BusinessCategory.SELF.getCode());
		}
		if (dto.getCreateTime() == null) {
			dto.setCreateTime(new Date());
		}
		if(dto.getDisplayOrder()==null){
			dto.setDisplayOrder(0);
		}
		if(StringUtils.isEmpty(dto.getBusinessCharge())){
			dto.setBusinessCharge(BusinessCategory.THIRD.getCode().equals(dto.getBusinessCategory())?BusinessCharge.CHARGE.getCode():BusinessCharge.Free.getCode());
		}
		updateFlsCount(dto.getFlsId());
		Integer sId = dto.getServiceId();
		dto.setServiceId(null);
		slsMapper.insertSelective(dto);
		if (!CollectionUtils.isEmpty(cityList)) {
			for (SlsCityDto one : cityList) {
				one.setServiceId(dto.getServiceId());
				one.setCreateTime(dto.getCreateTime());
				slsCityapper.insertSelective(one);
			}
		}
		if (sId != null) {
			slsMapper.updateDisplayOrder(dto.getServiceId(), dto.getDisplayOrder(), dto.getFlsId());
		}
	}

	protected void updateFlsCount(Integer flsId) {
		if (flsId == null) {
			return;
		}
		flsMapper.updateFlsCount(flsId);

	}

	@Override
	public void updateSlsState(Integer serviceId, String state) {
		if (serviceId == null) {
			return;
		}
		SlServiceDto dto = new SlServiceDto();
		dto.setServiceId(serviceId);
		dto.setState(state);
		slsMapper.updateByPrimaryKeySelective(dto);

	}

	@Override
	public void updateFlsState(Integer serviceId, String state) {
		if (serviceId == null) {
			return;
		}
		FlServiceDto dto = new FlServiceDto();
		dto.setServiceId(serviceId);
		dto.setState(state);
		flsMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public List<SlServiceDto> selectSls(Integer flsId) {
		return selectSls(flsId,null);
	}
	
	public List<SlServiceDto> selectSls(Integer flsId,String state) {
		if (flsId == null) {
			return null;
		}
		SlServiceDtoExample example = new SlServiceDtoExample();
		SlServiceDtoExample.Criteria cri = example.createCriteria();
		cri.andFlsIdEqualTo(flsId);
		if(!StringUtils.isEmpty(state)){
			cri.andStateEqualTo(state);
		}
		return slsMapper.selectByExample(example);
	}
	
	public List<SlServiceDto> selectSls(List<Integer> flsId,String state) {
		if (flsId == null) {
			return null;
		}
		SlServiceDtoExample example = new SlServiceDtoExample();
		SlServiceDtoExample.Criteria cri = example.createCriteria();
		cri.andFlsIdIn(flsId);
		if(!StringUtils.isEmpty(state)){
			cri.andStateEqualTo(state);
		}
		example.setOrderByClause("display_Order asc ,create_Time DESC");
		return slsMapper.selectByExample(example);
	}

	@Override
	public List<FlServiceItemDto> selectItems() {
		List<FlServiceItemDto> list = flsMapper.selectItems();
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<FlServiceItemDto> arrayList = new ArrayList<>(list.size());
		for (FlServiceItemDto one : list) {
			if (one.getSlsId() == null) {
				continue;
			}
			arrayList.add(one);
		}
		return arrayList.isEmpty() ? null : arrayList;
	}

	@Override
	public List<SlsItemDto> selectSlItems(Integer flsId) {
		List<SlServiceDto> dto = selectSls(flsId,ServiceState.NORMAL.getCode());
		return toSlsItems(dto);
	}
	
	protected List<SlsItemDto> toSlsItems(List<SlServiceDto> dto){
		if (CollectionUtils.isEmpty(dto)) {
			return null;
		}
		List<SlsItemDto> items = SlsItemDto.wrap(dto);
		List<SlsCityDtoKey> list = slsCityapper.selectSlsCity(dto);
		if (CollectionUtils.isEmpty(list)) {
			return items;
		}
		for (SlsCityDtoKey one : list) {
			for (int i = 0; i < items.size(); i++) {
				if(items.get(i).addSlsCity(one)){
					break;
				}
			}
		}
		return items;
	}

	@Override
	public SlServiceDto selectSlService(Integer slsId) {
		return slsMapper.selectByPrimaryKey(slsId);
	}

	@Override
	public List<SlsItemDto> selectSlItems(List<Integer> flsId) {
		List<SlServiceDto> slsServiceDto=selectSls(flsId,ServiceState.NORMAL.getCode());
		return toSlsItems(slsServiceDto);
	}

	@Override
	public List<FlsItemDto> selectAllFlsItem() {
		List<FlServiceDto> flServiceList=selectAllFlService(ServiceState.NORMAL.getCode());
		if(CollectionUtils.isEmpty(flServiceList)){
			return null;
		}
		List<Integer> flIdList=toServiceIdList(flServiceList);
		List<SlsItemDto> slsItems=selectSlItems(flIdList);
	
		List<FlsItemDto> flItem=FlsItemDto.wrap(flServiceList);
		if(CollectionUtils.isEmpty(slsItems)){
			return flItem;
		}
		for (SlsItemDto one : slsItems) {
			for (int i = 0; i < flItem.size(); i++) {
				if(flItem.get(i).addSlsItem(one)){
					break;
				}
			}
		}
		return flItem;
		 
	}
	protected List<Integer> toServiceIdList(List<FlServiceDto> list){
		List<Integer> lists=new ArrayList(list.size());
		FlServiceDto one;
		for(int i=0;i<list.size();i++){
			one=list.get(i);
			if(one.getSlsNum()>0||VisualMark.VISUAL.getCode().equals(one.getVirtualFlag())){
			lists.add(list.get(i).getServiceId());
			}
		}
		return lists;
	}

	@Override
	public List<SlServiceDto> selectSlsList(List<Integer> slsIdS) {
		SlServiceDtoExample example = new SlServiceDtoExample();
		SlServiceDtoExample.Criteria cri = example.createCriteria();
		cri.andServiceIdIn(slsIdS);
		return slsMapper.selectByExample(example);
	}

}
