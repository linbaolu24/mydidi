package cn.com.didi.user.register.domain;

import java.io.Serializable;
import java.util.Date;

import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.Role;
import cn.com.didi.domain.util.State;
import cn.com.didi.user.users.domain.UserDto;

public class RegisterDto implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7155341992859218623L;

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVc() {
		return vc;
	}

	public void setVc(String vc) {
		this.vc = vc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getVcId() {
		return vcId;
	}

	public void setVcId(Long vcId) {
		this.vcId = vcId;
	}

	private String phone;
	private String vc;
	private String password;
	private String role;
	private Long vcId;
	/**
	 * 地区代码
	 */
	private String addressCode;

	public UserDto toUserDto(String state,Date createTime) {
		UserDto users = new UserDto();
		users.setAddressCode(getAddressCode());
		users.setBpn(getPhone());
		users.setUserName(getPhone());
		users.setCreateTime(createTime);
		users.setState(state);
		users.setPassword(getPassword());
		users.setRole(role);
		if(Role.BUSINESS.codeEqual(role)){
			users.setBusinessCategory(BusinessCategory.THIRD.getCode());
		}else{
			users.setBusinessCategory(BusinessCategory.SELF.getCode());
		}
		return users;
	}

}
