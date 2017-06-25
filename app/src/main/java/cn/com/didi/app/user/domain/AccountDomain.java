package cn.com.didi.app.user.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.didi.domain.util.DomainConstatns;

public class AccountDomain {
	private  Long accountId;
	/*private  String vcId;*/
	private  String profilePhoto;
	private  String alipayAccount;
	private  String wechatAccount;
	private  String gtCid;
	private  String bpn;
	private String ryToken;
	private String vipFlag;
	private String role;
	private String arrivalStatus;
	private String businessCategory;
	private Date timeout;
	/*public String getVcId() {
		return vcId;
	}
	public void setVcId(String vcId) {
		this.vcId = vcId;
	}*/
	public String getProfilePhoto() {
		return profilePhoto;
	}
	public void setProfilePhoto(String profilePhoto) {
		this.profilePhoto = profilePhoto;
	}
	public String getAlipayAccount() {
		return alipayAccount;
	}
	public void setAlipayAccount(String alipayAccount) {
		this.alipayAccount = alipayAccount;
	}
	public String getWechatAccount() {
		return wechatAccount;
	}
	public void setWechatAccount(String wechatAccount) {
		this.wechatAccount = wechatAccount;
	}
	public String getGtCid() {
		return gtCid;
	}
	public void setGtCid(String gtCid) {
		this.gtCid = gtCid;
	}
	public String getBpn() {
		return bpn;
	}
	public Map<String,Object> toMap(){
		Map p = new HashMap(4);
		p.put(DomainConstatns.ACCOUNT_ID,getAccountId());
		p.put(DomainConstatns.PROFILE_PHOTO, getProfilePhoto());
		p.put(DomainConstatns.ALIPAY_ACCOUNT, getAlipayAccount());
		p.put(DomainConstatns.WECHAT_ACCOUNT, getWechatAccount());
		p.put(DomainConstatns.GT_CID, getGtCid());
		p.put(DomainConstatns.RY_TOKEN, getRyToken());
		p.put(DomainConstatns.BPN,getBpn());
		p.put(DomainConstatns.ROLE,getRole());
		p.put(DomainConstatns.VIP_FLAG,getVipFlag());
		p.put(DomainConstatns.BUSINESS_CATEGORY,getBusinessCategory());
		p.put(DomainConstatns.ARRIVAL_STATUS,getArrivalStatus());
		return p;
	}
	public void setBpn(String bpn) {
		this.bpn = bpn;
	}
	public String getRyToken() {
		return ryToken;
	}
	public void setRyToken(String ryToken) {
		this.ryToken = ryToken;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getVipFlag() {
		return vipFlag;
	}
	public void setVipFlag(String vipFlag) {
		this.vipFlag = vipFlag;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getArrivalStatus() {
		return arrivalStatus;
	}
	public void setArrivalStatus(String arrivalStatus) {
		this.arrivalStatus = arrivalStatus;
	}
	public String getBusinessCategory() {
		return businessCategory;
	}
	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}
	public Date getTimeout() {
		return timeout;
	}
	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}
	@Override
	public String toString() {
		return "AccountDomain [accountId=" + accountId + ", profilePhoto=" + profilePhoto + ", alipayAccount="
				+ alipayAccount + ", wechatAccount=" + wechatAccount + ", gtCid=" + gtCid + ", bpn=" + bpn
				+ ", ryToken=" + ryToken + ", vipFlag=" + vipFlag + ", role=" + role + ", arrivalStatus="
				+ arrivalStatus + ", businessCategory=" + businessCategory + ", timeout=" + timeout + "]";
	}
	
	
}
