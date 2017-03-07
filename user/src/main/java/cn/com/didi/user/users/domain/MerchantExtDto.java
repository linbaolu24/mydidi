package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MerchantExtDto implements Serializable{
	public String getAddress() {
		return dto.getAddress();
	}

	public void setAddress(String address) {
		dto.setAddress(address);
	}

	private MerchantDto dto;
	private List<MerchantServiceDto> serviceList;
	private List<MerchantAreaDto> areaList;

	public MerchantExtDto() {
		this(true);
	}

	public MerchantExtDto(boolean isInit) {
		if (isInit) {
			dto = new MerchantDto();
		}
	}

	public MerchantDto dto() {
		return dto;
	}

	public void dto(MerchantDto dto) {
		this.dto = dto;
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

	public MerchantDto getDto() {
		return dto;
	}

	public Long getAccountId() {
		return dto.getAccountId();
	}

	public void setAccountId(Long accountId) {
		dto.setAccountId(accountId);
	}

	public String getUserName() {
		return dto.getUserName();
	}

	public int hashCode() {
		return dto.hashCode();
	}

	public void setUserName(String userName) {
		dto.setUserName(userName);
	}

	public String getCname() {
		return dto.getCname();
	}

	public void setCname(String cname) {
		dto.setCname(cname);
	}

	public String getDetailAddress() {
		return dto.getDetailAddress();
	}

	public void setDetailAddress(String detailAddress) {
		dto.setDetailAddress(detailAddress);
	}

	public String getAddressCode() {
		return dto.getAddressCode();
	}

	public void setAddressCode(String addressCode) {
		dto.setAddressCode(addressCode);
	}

	public String getState() {
		return dto.getState();
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

	public String getIcImg1() {
		return dto.getIcImg1();
	}

	public void setIcImg1(String icImg1) {
		dto.setIcImg1(icImg1);
	}

	public String getIcImg2() {
		return dto.getIcImg2();
	}

	public void setIcImg2(String icImg2) {
		dto.setIcImg2(icImg2);
	}

	public boolean equals(Object obj) {
		return dto.equals(obj);
	}

	public String getIcImg3() {
		return dto.getIcImg3();
	}

	public void setIcImg3(String icImg3) {
		dto.setIcImg3(icImg3);
	}

	public String getLicenceImg() {
		return dto.getLicenceImg();
	}

	public void setLicenceImg(String licenceImg) {
		dto.setLicenceImg(licenceImg);
	}

	public String getBusinessCategory() {
		return dto.getBusinessCategory();
	}

	public void setBusinessCategory(String businessCategory) {
		dto.setBusinessCategory(businessCategory);
	}

	public String getCr() {
		return dto.getCr();
	}

	public void setCr(String cr) {
		dto.setCr(cr);
	}

	public String getCause() {
		return dto.getCause();
	}

	public void setCause(String cause) {
		dto.setCause(cause);
	}

	public String getContactInformation() {
		return dto.getContactInformation();
	}

	public void setContactInformation(String contactInformation) {
		dto.setContactInformation(contactInformation);
	}

	public String getBpn() {
		return dto.getBpn();
	}

	public void setBpn(String bpn) {
		dto.setBpn(bpn);
	}

	public String getMastername() {
		return dto.getMastername();
	}

	public void setMastername(String mastername) {
		dto.setMastername(mastername);
	}

	public String toString() {
		return dto.toString();
	}

	public String getMpn() {
		return dto.getMpn();
	}

	public void setMpn(String mpn) {
		dto.setMpn(mpn);
	}

}
