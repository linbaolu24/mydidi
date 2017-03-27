package cn.com.didi.app.item.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.FlsItemDto;

public class FlsItemWrapper {
	public List<SlsItemWrapper> getSlsList() {
		return slsList;
	}

	public void setSlsList(List<SlsItemWrapper> slsList) {
		this.slsList = slsList;
	}

	public Integer getServiceId() {
		return dto.getServiceId();
	}

	public void setServiceId(Integer serviceId) {
		dto.setServiceId(serviceId);
	}

	public String getCname() {
		return dto.getCname();
	}

	public void setCname(String cname) {
		dto.setCname(cname);
	}

	public Integer getDisplayOrder() {
		return dto.getDisplayOrder();
	}

	public void setDisplayOrder(Integer displayOrder) {
		dto.setDisplayOrder(displayOrder);
	}

	private FlServiceDto dto;
	private List<SlsItemWrapper> slsList;
	public FlsItemWrapper() {
		super();
	}

	public FlsItemWrapper(FlsItemDto dto) {
		super();
		this.dto = dto.getFlsDto();
		this.slsList=SlsItemWrapper.wrap(dto.getItem());
	}

	public static List<FlsItemWrapper> wrap(List<FlsItemDto> flsItelDtomList) {
		if (CollectionUtils.isEmpty(flsItelDtomList)) {
			return null;
		}
		List<FlsItemWrapper> lists = new ArrayList<FlsItemWrapper>(flsItelDtomList.size());
		for (FlsItemDto one : flsItelDtomList) {
			if(one.empty()){
				continue;
			}
			lists.add(new FlsItemWrapper(one));
		}
		return lists;
	}
	
}
