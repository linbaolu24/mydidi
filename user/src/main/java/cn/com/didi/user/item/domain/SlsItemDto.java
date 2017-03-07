package cn.com.didi.user.item.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.domain.domains.CityCodeDto;

public class SlsItemDto implements Serializable {
	public SlServiceDto getDto() {
		return dto;
	}

	public void setDto(SlServiceDto dto) {
		this.dto = dto;
	}

	public List<CityCodeDto> getCityList() {
		return cityList;
	}

	public void setCityList(List<CityCodeDto> cityList) {
		this.cityList = cityList;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3622013873287099203L;
	private SlServiceDto dto;
	private List<CityCodeDto> cityList;

	public void addSlsCity(SlsCityDtoKey cityDto) {
		if (dto.getServiceId() == null || cityDto.getServiceId() == null) {
			return;
		}
		if (dto.getServiceId().intValue() == cityDto.getServiceId().intValue()) {
			if (cityList == null) {
				cityList = new ArrayList<>();
			}
			if (cityDto != null) {
				cityList.add(cityDto.toCityCodeDto());
			}
		}
	}

	public SlsItemDto() {
		super();
	}

	public SlsItemDto(SlServiceDto dto) {
		super();
		this.dto = dto;
	}

	public static List<SlsItemDto> wrap(List<SlServiceDto> slsDto) {
		if (CollectionUtils.isEmpty(slsDto)) {
			return null;
		}
		List<SlsItemDto> lists = new ArrayList<SlsItemDto>(slsDto.size());
		for (SlServiceDto one : slsDto) {
			lists.add(new SlsItemDto(one));
		}
		return lists;
	}
}
