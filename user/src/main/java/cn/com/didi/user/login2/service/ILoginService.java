package cn.com.didi.user.login2.service;

import cn.com.didi.core.property.IResult;
import cn.com.didi.user.login2.domain.LoginDto;
import cn.com.didi.user.login2.domain.UserExtDto;

public interface ILoginService {
	public IResult<UserExtDto> login(LoginDto login);
}
