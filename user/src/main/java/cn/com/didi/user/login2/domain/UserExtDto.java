package cn.com.didi.user.login2.domain;

import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

public class UserExtDto {

	public String alipayAccount() {
		if(linkIdDto==null){
			return null;
		}
		return linkIdDto.getAlipayAccount();
	}

	public String wechatAccount() {
		if(linkIdDto==null){
			return null;
		}
		return linkIdDto.getWechatAccount();
	}

	public String gtCid() {
		if(linkIdDto==null){
			return null;
		}
		return linkIdDto.getGtCid();
	}

	public String ryToken() {
		if(linkIdDto==null){
			return null;
		}
		return linkIdDto.getRyToken();
	}

	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}

	public UserLinkIdDto getLinkIdDto() {
		return linkIdDto;
	}

	public void setLinkIdDto(UserLinkIdDto linkIdDto) {
		this.linkIdDto = linkIdDto;
	}

	private UserDto userDto;
	private UserLinkIdDto linkIdDto;
	
}
