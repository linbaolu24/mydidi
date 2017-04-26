package cn.com.didi.user.login2.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.State;
import cn.com.didi.user.login2.domain.LoginDto;
import cn.com.didi.user.login2.domain.UserExtDto;
import cn.com.didi.user.login2.service.ILoginService;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.util.MessageConstans;

/**
 * @author xlm
 *
 */
@Service
public class LoginServiceImpl implements ILoginService {
	@Resource
	protected IUserService userService;

	@Override
	public IResult<UserExtDto> login(LoginDto login) {
		UserDto dto = userService.selectUser(login.getPhone(), login.getRole());
		if (dto == null) {
			return ResultFactory.error(MessageConstans.USER_USER_NOT_EXISTS);// 返回用户未注册
		}
		if (!dto.getPassword().equals(login.getPassword())) {
			return ResultFactory.error(MessageConstans.USER_PASSOWRD_NOT_EQUAL);// 返回密码不正确
		}
		if(!State.VALID.getState().equals(dto.getState())){
			return ResultFactory.error(MessageConstans.USER_STATE_NOT_VALID);
		}
		UserLinkIdDto linked = userService.selectUserLinkedId(dto.getAccountId());
		UserExtDto ext = new UserExtDto();
		ext.setUserDto(dto);
		ext.setLinkIdDto(linked);
		return ResultFactory.success(ext);
	}

}
