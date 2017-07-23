package cn.com.didi.domain.domains;

import cn.com.didi.domain.util.Role;

public class ReciverDto implements IReciverDto{
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public String getReciveId() {
		return reciveId;
	}
	public void setReciveId(String reciveId) {
		this.reciveId = reciveId;
	}
	public String getReciveType() {
		return reciveType;
	}
	public void setReciveType(String reciveType) {
		this.reciveType = reciveType;
	}
	private Long accountId;
	private String reciveId;
	private String reciveType="gt";
	private Role accountType;
	public Role getAccountType() {
		return accountType;
	}
	public void setAccountType(Role accountType) {
		this.accountType = accountType;
	}
	public ReciverDto(Long accountId, String reciveId, String reciveType, Role accountType) {
		super();
		this.accountId = accountId;
		this.reciveId = reciveId;
		this.reciveType = reciveType;
		this.accountType = accountType;
	}
	public ReciverDto() {
		super();
	}
	@Override
	public String toString() {
		return "ReciverDto [accountId=" + accountId + ", reciveId=" + reciveId + ", reciveType=" + reciveType
				+ ", accountType=" + accountType + "]";
	}
	
	
	

}
