package cn.com.didi.user.item.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.item.dao.mapper.FlServiceDtoMapper;
import cn.com.didi.user.item.dao.mapper.SlServiceDtoMapper;
import cn.com.didi.user.item.dao.mapper.SlsCityDtoMapper;
import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.FlServiceItemDto;
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

	@Override
	public Integer selectMaxFlsDisplayOrder() {
		return flsMapper.selectMaxFlsDisplayOrder();
	}

	@Override
	public Integer selectMaxSlsDisplayOrder(Integer flsId) {
		return slsMapper.selectMaxSlsDisplayOrder(flsId);
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
		if (dto.getCreateTime() == null) {
			dto.setCreateTime(new Date());
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
		if (flsId == null) {
			return null;
		}
		SlServiceDtoExample example = new SlServiceDtoExample();
		SlServiceDtoExample.Criteria cri = example.createCriteria();
		cri.andFlsIdEqualTo(flsId);
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
		List<SlServiceDto> dto = selectSls(flsId);
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
				items.get(i).addSlsCity(one);
			}
		}
		return items;
	}

	@Override
	public SlServiceDto selectSlService(Integer slsId) {
		return slsMapper.selectByPrimaryKey(slsId);
	}

}
