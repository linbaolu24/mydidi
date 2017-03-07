package cn.com.didi.user.item.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SlsSericeExtDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5028971719948649959L;
	private SlServiceDto dto;
	private List<SlsCityDto> cityList;

	public SlsSericeExtDto() {
		this(true);
	}

	public SlsSericeExtDto(boolean isInit) {
		if (isInit) {
			dto = new SlServiceDto();
		}
	}

	public SlServiceDto dto() {
		return dto;
	}

	public void dto(SlServiceDto dto) {
		this.dto = dto;
	}

	public List<SlsCityDto> getCityList() {
		return cityList;
	}

	public void setCityList(List<SlsCityDto> cityList) {
		this.cityList = cityList;
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

	public String getState() {
		return dto.getState();
	}

	public int hashCode() {
		return dto.hashCode();
	}

	public void setState(String state) {
		dto.setState(state);
	}

	public Date getCreateTime() {
		return dto.getCreateTime();
	}

	public void setCreateTime(Date createTime) {
		dto.setCreateTime(createTime);
	}

	public Integer getFlsId() {
		return dto.getFlsId();
	}

	public void setFlsId(Integer flsId) {
		dto.setFlsId(flsId);
	}

	public String getBusinessCategory() {
		return dto.getBusinessCategory();
	}

	public void setBusinessCategory(String businessCategory) {
		dto.setBusinessCategory(businessCategory);
	}

	public String getCity() {
		return dto.getCity();
	}

	public void setCity(String city) {
		dto.setCity(city);
	}

	public String getBusinessCharge() {
		return dto.getBusinessCharge();
	}

	public void setBusinessCharge(String businessCharge) {
		dto.setBusinessCharge(businessCharge);
	}

	public boolean equals(Object obj) {
		return dto.equals(obj);
	}

	public String toString() {
		return dto.toString();
	}

}
