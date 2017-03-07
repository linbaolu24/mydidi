package cn.com.didi.platform.login.domain;

/**
 * @author xlm
 *
 */
public class PLoginDto {
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVc() {
		return vc;
	}
	public void setVc(String vc) {
		this.vc = vc;
	}
	private String userName;
	private String password;	
	private String  vc;	
	

}
