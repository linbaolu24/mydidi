package cn.com.didi.user.item.domain;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class FlsItemDto {
	public List<SlsItemDto> getItem() {
		return item;
	}

	public void setItem(List<SlsItemDto> item) {
		this.item = item;
	}

	public FlServiceDto getFlsDto() {
		return flsDto;
	}

	public void setFlsDto(FlServiceDto flsDto) {
		this.flsDto = flsDto;
	}

	public FlsItemDto(FlServiceDto flsDto) {
		super();
		this.flsDto = flsDto;
	}
	
	
	
	public FlsItemDto() {
		super();
	}

	public boolean addSlsItem(SlsItemDto sls) {
		if (flsDto.getServiceId() == null || sls.getDto() == null || sls.getDto().getServiceId() == null
				|| sls.getDto().getFlsId() == null) {
			return false;
		}
		if (flsDto.getServiceId().intValue() == sls.getDto().getFlsId().intValue()) {
			if (item == null) {
				item = new ArrayList<>();
			}
			item.add(sls);
			return true;
		}
		return false;
	}
	
	public static List<FlsItemDto> wrap(List<FlServiceDto> flsDto) {
		if (CollectionUtils.isEmpty(flsDto)) {
			return null;
		}
		List<FlsItemDto> lists = new ArrayList<FlsItemDto>(flsDto.size());
		for (FlServiceDto one : flsDto) {
			lists.add(new FlsItemDto(one));
		}
		return lists;
	}
	public boolean empty(){
		return CollectionUtils.isEmpty(item);
	}
	private FlServiceDto flsDto;
	private List<SlsItemDto> item;
}
