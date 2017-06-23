package cn.com.didi.user.users.service;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.users.domain.VipDescrptionDto;
import cn.com.didi.user.users.domain.VipDto;

public interface IVipService {
	public boolean hasVip(Long accountId,Integer slsId);
	public boolean hasVip(VipDto vip);
	public VipDescrptionDto desc(Long accountId,Integer slsId);
	public void reg(VipDto vipDto,Long dealId);
	public void preReg(VipDto vipDto,Long dealId);
	public String regInit(Integer slsId);
	public VipDto getDto(Long accountId,Integer slsId);
	public IPage<VipDto> listPage(TimeInterval interval);
	public void updateVip(Long accountId,Integer slsId,String cname,String bpn,String pp);
	public void setVipFee(Integer slsId,int fee);
	public int getVipFee(Integer slsId);
}
