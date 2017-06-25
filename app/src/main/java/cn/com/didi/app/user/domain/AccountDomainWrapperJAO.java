package cn.com.didi.app.user.domain;

import java.util.Date;

public class AccountDomainWrapperJAO {
	private AccountDomain domain;
	
	private String reflashToken;
	
	public String getReflashToken() {
		return reflashToken;
	}
	public void setReflashToken(String reflashToken) {
		this.reflashToken = reflashToken;
	}
	/*public String getVcId() {
		return domain.getVcId();
	}
	public void setVcId(String vcId) {
		domain.setVcId(vcId);
	}*/
	public String getProfilePhoto() {
		return domain.getProfilePhoto();
	}
	public void setProfilePhoto(String profilePhoto) {
		domain.setProfilePhoto(profilePhoto);
	}
	public String getAlipayAccount() {
		return domain.getAlipayAccount();
	}
	public void setAlipayAccount(String alipayAccount) {
		domain.setAlipayAccount(alipayAccount);
	}
	public String getWechatAccount() {
		return domain.getWechatAccount();
	}
	public void setWechatAccount(String wechatAccount) {
		domain.setWechatAccount(wechatAccount);
	}
	public String getGtCid() {
		return domain.getGtCid();
	}
	public void setGtCid(String gtCid) {
		domain.setGtCid(gtCid);
	}
	public String getBpn() {
		return domain.getBpn();
	}
	public void setBpn(String bpn) {
		domain.setBpn(bpn);
	}
	public String getRyToken() {
		return domain.getRyToken();
	}
	public void setRyToken(String ryToken) {
		domain.setRyToken(ryToken);
	}
	public Long getAccountId() {
		return domain.getAccountId();
	}
	public void setAccountId(Long accountId) {
		domain.setAccountId(accountId);
	}
	public String getVipFlag() {
		return domain.getVipFlag();
	}
	public void setVipFlag(String vipFlag) {
		domain.setVipFlag(vipFlag);
	}
	public String getRole() {
		return domain.getRole();
	}
	public void setRole(String role) {
		domain.setRole(role);
	}
	public String getArrivalStatus() {
		return domain.getArrivalStatus();
	}
	public void setArrivalStatus(String arrivalStatus) {
		domain.setArrivalStatus(arrivalStatus);
	}
	public String getBusinessCategory() {
		return domain.getBusinessCategory();
	}
	public void setBusinessCategory(String businessCategory) {
		domain.setBusinessCategory(businessCategory);
	}
	public Date getTimeout() {
		return domain.getTimeout();
	}
	public void setTimeout(Date timeout) {
		domain.setTimeout(timeout);
	}
	public AccountDomainWrapperJAO(AccountDomain domain) {
		this.domain = domain;
		
	}

	public AccountDomainWrapperJAO(boolean init) {
		if(init){
			this.domain=new AccountDomain();
		}
	}

	public AccountDomainWrapperJAO() {
		this(true);

	}
	public AccountDomain accountDomain() {
		return domain;
	}
	public void accountDomain(AccountDomain domain) {
		this.domain = domain;
	}
	public AccountDomainWrapperJAO(AccountDomain domain, String reflashToken) {
		super();
		this.domain = domain;
		this.reflashToken = reflashToken;
	}
	
}
