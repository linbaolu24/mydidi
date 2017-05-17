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

	public Integer getPoundage() {
		return dto.getPoundage();
	}

	public void setPoundage(Integer poundage) {
		dto.setPoundage(poundage);
	}

	public Integer getCommission() {
		return dto.getCommission();
	}

	public void setCommission(Integer commission) {
		dto.setCommission(commission);
	}

	public String getSpecialType() {
		return dto.getSpecialType();
	}

	public void setSpecialType(String specialType) {
		dto.setSpecialType(specialType);
	}

	public String getExt1() {
		return dto.getExt1();
	}

	public void setExt1(String ext1) {
		dto.setExt1(ext1);
	}

	public String getExt2() {
		return dto.getExt2();
	}

	public void setExt2(String ext2) {
		dto.setExt2(ext2);
	}

}
