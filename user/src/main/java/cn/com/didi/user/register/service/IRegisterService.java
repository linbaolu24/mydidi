package cn.com.didi.user.register.service;

import cn.com.didi.user.register.domain.RegisterDto;

public interface IRegisterService {
	public Long sendVc(String phone);

	public void resetPassword(RegisterDto dto);

	public Long register(RegisterDto dto);

	public void changePassword(Long accoutId,String newPassword,String oldPassword);
	/**
	 * 判断是否已注册
	 * @param phone
	 * @param role
	 * @return
	 */
	public boolean registered(String phone,String role);
}
