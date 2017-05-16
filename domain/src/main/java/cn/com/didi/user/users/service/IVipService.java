package cn.com.didi.user.users.service;

import cn.com.didi.user.users.domain.VipDescrptionDto;
import cn.com.didi.user.users.domain.VipDto;

public interface IVipService {
	public boolean hasVip(Long accountId,Integer slsId);
	public VipDescrptionDto desc(Long accountId,Integer slsId);
	public void reg(VipDto vipDto,Long dealId);
	public String regInit(Integer slsId);
	public VipDto getDto(Long accountId,Integer slsId);
}
