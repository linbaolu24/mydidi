package cn.com.didi.app.user.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.didi.user.users.domain.MerchantDto;

public class MerchantWrapperJAO {
	private MerchantDto merchantDto;
	private List<MerchantServiceWrapperJAO> serviceList;
	public MerchantWrapperJAO(MerchantDto merchantDto) {
		super();
		this.merchantDto = merchantDto;
	}
	public List<MerchantServiceWrapperJAO> getServiceList() {
		return serviceList;
	}
	public void setServiceList(List<MerchantServiceWrapperJAO> serviceList) {
		this.serviceList = serviceList;
	}
	public Long getAccountId() {
		return merchantDto.getAccountId();
	}
	public void setAccountId(Long accountId) {
		merchantDto.setAccountId(accountId);
	}
	public String getUserName() {
		return merchantDto.getUserName();
	}
	public void setUserName(String userName) {
		merchantDto.setUserName(userName);
	}
	public String getCname() {
		return merchantDto.getCname();
	}
	public void setCname(String cname) {
		merchantDto.setCname(cname);
	}
	public String getDetailAddress() {
		return merchantDto.getDetailAddress();
	}
	public void setDetailAddress(String detailAddress) {
		merchantDto.setDetailAddress(detailAddress);
	}
	public String getAddressCode() {
		return merchantDto.getAddressCode();
	}
	public void setAddressCode(String addressCode) {
		merchantDto.setAddressCode(addressCode);
	}
	public String getState() {
		return merchantDto.getState();
	}
	public void setState(String state) {
		merchantDto.setState(state);
	}
	public Date getCreateTime() {
		return merchantDto.getCreateTime();
	}
	public void setCreateTime(Date createTime) {
		merchantDto.setCreateTime(createTime);
	}
	public String getIcImg1() {
		return merchantDto.getIcImg1();
	}
	public void setIcImg1(String icImg1) {
		merchantDto.setIcImg1(icImg1);
	}
	public String getIcImg2() {
		return merchantDto.getIcImg2();
	}
	public void setIcImg2(String icImg2) {
		merchantDto.setIcImg2(icImg2);
	}
	public String getIcImg3() {
		return merchantDto.getIcImg3();
	}
	public void setIcImg3(String icImg3) {
		merchantDto.setIcImg3(icImg3);
	}
	public String getLicenceImg() {
		return merchantDto.getLicenceImg();
	}
	public void setLicenceImg(String licenceImg) {
		merchantDto.setLicenceImg(licenceImg);
	}
	public String getBusinessCategory() {
		return merchantDto.getBusinessCategory();
	}
	public void setBusinessCategory(String businessCategory) {
		merchantDto.setBusinessCategory(businessCategory);
	}
	public String getCr() {
		return merchantDto.getCr();
	}
	public void setCr(String cr) {
		merchantDto.setCr(cr);
	}
	public String getCause() {
		return merchantDto.getCause();
	}
	public void setCause(String cause) {
		merchantDto.setCause(cause);
	}
	public String getContactInformation() {
		return merchantDto.getContactInformation();
	}
	public void setContactInformation(String contactInformation) {
		merchantDto.setContactInformation(contactInformation);
	}
	public String getBpn() {
		return merchantDto.getBpn();
	}
	public void setBpn(String bpn) {
		merchantDto.setBpn(bpn);
	}
	public String getMastername() {
		return merchantDto.getMastername();
	}
	public void setMastername(String mastername) {
		merchantDto.setMastername(mastername);
	}
	public BigDecimal getLng() {
		return merchantDto.getLng();
	}
	public void setLng(BigDecimal lng) {
		merchantDto.setLng(lng);
	}
	public BigDecimal getLat() {
		return merchantDto.getLat();
	}
	public void setLat(BigDecimal lat) {
		merchantDto.setLat(lat);
	}
	public String getSpecialType() {
		return merchantDto.getSpecialType();
	}
	public void setSpecialType(String specialType) {
		merchantDto.setSpecialType(specialType);
	}
	public String getDescription() {
		return merchantDto.getDescription();
	}
	public void setDescription(String description) {
		merchantDto.setDescription(description);
	}
	public String getExt1() {
		return merchantDto.getExt1();
	}
	public void setExt1(String ext1) {
		merchantDto.setExt1(ext1);
	}
	public String getExt2() {
		return merchantDto.getExt2();
	}
	public void setExt2(String ext2) {
		merchantDto.setExt2(ext2);
	}
	public String getMerchantLogo() {
		return merchantDto.getMerchantLogo();
	}
	public void setMerchantLogo(String merchantLogo) {
		merchantDto.setMerchantLogo(merchantLogo);
	}
	public String getPassword() {
		return merchantDto.getPassword();
	}
	public void setPassword(String password) {
		merchantDto.setPassword(password);
	}
	public String getAlipayAccount() {
		return merchantDto.getAlipayAccount();
	}
	public void setAlipayAccount(String alipayAccount) {
		merchantDto.setAlipayAccount(alipayAccount);
	}
	public void setWechatAccount(String wechatAccount) {
		merchantDto.setWechatAccount(wechatAccount);
	}
	public String getService() {
		return merchantDto.getService();
	}
	public void setService(String service) {
		merchantDto.setService(service);
	}
	public String getMpn() {
		return merchantDto.getMpn();
	}
	public void setMpn(String mpn) {
		merchantDto.setMpn(mpn);
	}
	public String getAddress() {
		return merchantDto.getAddress();
	}
	public void setAddress(String address) {
		merchantDto.setAddress(address);
	}
	public String getWechatAccount() {
		return merchantDto.getWechatAccount();
	}
	public String getWechatName() {
		return merchantDto.getWechatName();
	}
	public void setWechatName(String wechatName) {
		merchantDto.setWechatName(wechatName);
	}
	
}
