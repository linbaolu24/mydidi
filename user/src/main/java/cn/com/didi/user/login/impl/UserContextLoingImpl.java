package cn.com.didi.user.login.impl;

import cn.com.didi.user.context.IUserContext;
import cn.com.didi.user.users.domain.UserDto;

public class UserContextLoingImpl implements IUserContext{
	private UserDto dto;

	@Override
	public <T> T cast(Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> boolean support(Class<T> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getAccountId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getContactInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProfilePhoto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
