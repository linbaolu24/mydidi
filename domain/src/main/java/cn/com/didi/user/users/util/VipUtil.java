package cn.com.didi.user.users.util;

import java.util.HashMap;
import java.util.Map;

import cn.com.didi.domain.domains.UseAbleDto;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.user.users.domain.VipDto;

public class VipUtil {
	public static Map<String,Object> toMap(VipDto vipDto){
		if(vipDto==null){
			return null;
		}
		Map<String,Object> map=new HashMap<>(4);
		map.put(DomainConstatns.PROFILE_PHOTO,vipDto.getProfilePhoto());
		map.put(DomainConstatns.BPN, vipDto.getBpn());
		map.put(DomainConstatns.CNAME, vipDto.getCname());
		return map;
	}
	public static Map<String,Object> toMap(UseAbleDto<VipDto> vipDto){
		if(vipDto==null){
			return null;
		}
		Map<String,Object> map=toMap(vipDto.getData());
		if(map==null){
			return null;
		}
		map.put(DomainConstatns.USED,vipDto.getUsed());
		map.put(DomainConstatns.COUNT,vipDto.getCount());
		map.put(DomainConstatns.DESCRIPTION,vipDto.getDescription());
		return map;
	}
}
