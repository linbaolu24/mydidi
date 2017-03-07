package cn.com.didi.user.register.service;

import cn.com.didi.user.register.domain.RegisterDto;

public interface IRegisterService {
	public Long sendVc(String phone);

	public void resetPassword(RegisterDto dto);

	public Long register(RegisterDto dto);

	public void changePassword(Long accoutId,String newPassword,String oldPassword);
}
