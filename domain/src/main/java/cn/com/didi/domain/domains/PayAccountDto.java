package cn.com.didi.domain.domains;

public class PayAccountDto {
	public String getAliAccount() {
		return aliAccount;
	}
	public void setAliAccount(String aliAccount) {
		this.aliAccount = aliAccount;
	}
	public String getWechartAccount() {
		return wechartAccount;
	}
	public void setWechartAccount(String wechartAccount) {
		this.wechartAccount = wechartAccount;
	}
	/**
	 * 
	 */
	private String aliAccount;
	/**
	 * 
	 */
	private String wechartAccount;
	
}
