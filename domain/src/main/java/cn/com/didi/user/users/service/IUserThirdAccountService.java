package cn.com.didi.user.users.service;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.IResult;
import cn.com.didi.user.users.domain.UserLinkIdDto;

/**
 * @author xlm
 *
 */
public interface IUserThirdAccountService {
	public IResult<UserLinkIdDto> generatorUserLink(String phone,String role);
	public void generatorUserLink(String phone,String role,UserLinkIdDto linkDto);
	public default UserLinkIdDto generatorUserLinkAndThrow(String phone,String role){
		IResult<UserLinkIdDto> result=generatorUserLink(phone, role);
		if(result.success()){
			return result.getData();
		}
		throw new MessageObjectException(result.getCode(),result.getMessage());
	}
}
