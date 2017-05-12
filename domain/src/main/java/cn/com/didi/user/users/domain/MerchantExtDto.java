package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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

	/*public MerchantDto getDto() {
		return dto;
	}*/

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

	public BigDecimal getLng() {
		return dto.getLng();
	}

	public void setLng(BigDecimal lng) {
		dto.setLng(lng);
	}

	public BigDecimal getLat() {
		return dto.getLat();
	}

	public void setLat(BigDecimal lat) {
		dto.setLat(lat);
	}
	public String getMasterName() {
		return dto.getMastername();
	}

	public void setMasterName(String mastername) {
		dto.setMastername(mastername);
	}

	public String getPassword() {
		return dto.getPassword();
	}

	public void setPassword(String password) {
		dto.setPassword(password);
	}

	public String getAlipayAccount() {
		return dto.getAlipayAccount();
	}

	public void setAlipayAccount(String alipayAccount) {
		dto.setAlipayAccount(alipayAccount);
	}

	public String getWechatAccount() {
		return dto.getWechatAccount();
	}

	public void setWechatAccount(String wechatAccount) {
		dto.setWechatAccount(wechatAccount);
	}

	public String getSpecialType() {
		return dto.getSpecialType();
	}

	public void setSpecialType(String specialType) {
		dto.setSpecialType(specialType);
	}

	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String description) {
		dto.setDescription(description);
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

	public String getMerchantLogo() {
		return dto.getMerchantLogo();
	}

	public void setMerchantLogo(String merchantLogo) {
		dto.setMerchantLogo(merchantLogo);
	}

	public String getService() {
		return dto.getService();
	}

	public void setService(String service) {
		dto.setService(service);
	}

}
