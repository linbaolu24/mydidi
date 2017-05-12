package cn.com.didi.app.item.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.domain.domains.CityCodeDto;
import cn.com.didi.user.item.domain.SlsItemDto;

public class SlsItemWrapper {
	public List<CityCodeDto> getCityList() {
		return dto.getCityList();
	}
	public void setCityList(List<CityCodeDto> cityList) {
		 dto.setCityList(cityList);
	}

	public SlsItemWrapper(SlsItemDto dto) {
		super();
		this.dto = dto;
	}
	
	public SlsItemWrapper() {
	}
	
	
	private SlsItemDto dto;
	public Integer getServiceId() {
		return dto.getDto().getServiceId();
	}
	public void setServiceId(Integer serviceId) {
		dto.getDto().setServiceId(serviceId);
	}
	public String getCname() {
		return dto.getDto().getCname();
	}
	public void setCname(String cname) {
		dto.getDto().setCname(cname);
	}
	public Integer getDisplayOrder() {
		return dto.getDto().getDisplayOrder();
	}
	public void setDisplayOrder(Integer displayOrder) {
		dto.getDto().setDisplayOrder(displayOrder);
	}
	public String getBusinessCategory() {
		return dto.getDto().getBusinessCategory();
	}
	public void setBusinessCategory(String businessCategory) {
		dto.getDto().setBusinessCategory(businessCategory);
	}
	public String getBusinessCharge() {
		return dto.getDto().getBusinessCharge();
	}
	 /**
     * 1 表示美容美发
     **/
    public String getSpecialType() {
        return dto.getDto().getSpecialType();
    }

    /**
     * 1 表示美容美发
     **/
    public void setSpecialType(String specialType) {
        dto.getDto().setSpecialType(specialType);
    }
	public static List<SlsItemWrapper> wrap(List<SlsItemDto> slsDto) {
		if (CollectionUtils.isEmpty(slsDto)) {
			return null;
		}
		List<SlsItemWrapper> lists = new ArrayList<SlsItemWrapper>(slsDto.size());
		for (SlsItemDto one : slsDto) {
			lists.add(new SlsItemWrapper(one));
		}
		return lists;
	}
	
}
