package cn.com.didi.domain.domains;

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
	private String reciveType;
	
	

}
