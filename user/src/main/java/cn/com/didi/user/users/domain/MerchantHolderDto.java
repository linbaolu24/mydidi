package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MerchantHolderDto implements Serializable {
	public MerchantDto getDto() {
		return dto;
	}

	public void setDto(MerchantDto dto) {
		this.dto = dto;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<MerchantServiceDto> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<MerchantServiceDto> serviceList) {
		this.serviceList = serviceList;
	}

	public List<MerchantAreaDto> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<MerchantAreaDto> areaList) {
		this.areaList = areaList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = -5276433564059822635L;
	private MerchantDto dto;
	private String address;
	private List<MerchantServiceDto> serviceList;
	private List<MerchantAreaDto> areaList;

	public MerchantExtDto toMerchantExtDto() {
		MerchantExtDto exDto = new MerchantExtDto(false);
		exDto.dto(this.getDto());
		exDto.setAddress(this.getAddress());
		exDto.setAreaList(this.getAreaList());
		return exDto;
	}

	public void fromMerchantExtDto(MerchantExtDto extDto) {
		this.setDto(extDto.getDto());
		this.setAddress(extDto.getAddress());
		this.setAreaList(extDto.getAreaList());
	}

}
