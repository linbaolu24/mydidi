package cn.com.didi.user.users.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantDto;

public class MerchantUtils {
	public  static  List<Long> toAccoutIdList(List<MerchantDto> merchatList){
		if(CollectionUtils.isEmpty(merchatList)){
			return null;
		}
		List<Long> accoutIdList=new ArrayList<>(merchatList.size());
		for(MerchantDto one:merchatList){
			accoutIdList.add(one.getAccountId());
		}
		return accoutIdList;
	}
	
	public  static  List<Long> toAccoutIdListFromAreaList(List<MerchantAreaDto> merchatList){
		if(CollectionUtils.isEmpty(merchatList)){
			return null;
		}
		List<Long> accoutIdList=new ArrayList<>(merchatList.size());
		for(MerchantAreaDto one:merchatList){
			accoutIdList.add(one.getAccountId());
		}
		return accoutIdList;
	}
}
