package cn.com.didi.user.users.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.domain.util.State;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.users.dao.mapper.VipDtoMapper;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;
import cn.com.didi.user.users.domain.VipDto;
import cn.com.didi.user.users.domain.VipDtoKey;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.users.service.IVipService;
import cn.com.didi.user.util.MessageConstans;
@Service
public class VipServiceImpl implements IVipService{
	@Resource
	protected VipDtoMapper vipMapper;
	@Resource
	protected IUserService userService;
	@Resource
	protected IDepositService depositeService;
	@Resource
	protected ITradeInfoService tradeInfoService;
	@Resource
	protected IAppEnv appEnvService;
	@Override
	public boolean hasVip(Long accountId, Integer slsId) {
		VipDto dto=selectVip(accountId, slsId);
		return dto!=null&&State.VALID.getState().equals(dto.getState());
		
	}
	public void reg(VipDto vipDto,Long dealId){
		
		DealDto deal=tradeInfoService.selectDeal(dealId);
		if(deal==null||!TradeCategory.DEPOSIT.getCode().equals(deal.getCategory())){
			throw new MessageObjectException(MessageConstans.VIP_NOT_PAY);//未付款不能升级为vip
		}
		boolean isOk=depositeService.existDeposit(deal.getSai(),deal.getOrderId());//判断是否存在
		if(!isOk){
			throw new MessageObjectException(MessageConstans.VIP_PAY_NOT_ARRIVE);
		}
		vipDto.setState(State.VALID.getState());
		UserDto dto=userService.selectUser(vipDto.getAccountId());
		vipDto.setBusinessCategory(dto.getBusinessCategory());
		vipDto.setRole(dto.getRole());
		if(vipDto.getCreateTime()==null){
			vipDto.setCreateTime(new Date());
		}
		if(vipDto.getUpdateTime()==null){
			vipDto.setUpdateTime(vipDto.getCreateTime());
		}
		vipMapper.insertSelective(vipDto);
	}
	@Override
	public VipDescrptionDto desc(Long accountId,Integer slsId) {
		VipDto dto=selectVip(accountId, slsId);
		if(dto==null){
			return null;
		}
		VipDescrptionDto desc=appEnvService.getVipDesc(slsId);
		desc=desc.cloneSelf();
		desc.setVipDto(dto);
		return desc;
		
	}
	public VipDto selectVip(Long accountId,Integer slsId){
		VipDtoKey key=new VipDtoKey();
		key.setAccountId(accountId);
		key.setSlsId(slsId);
		VipDto dto =vipMapper.selectByPrimaryKey(key);
		return dto;
	}
	@Override
	public String regInit(Integer slsId) {
		return appEnvService.getRegVipDesc(slsId);
		
	}
	@Override
	public VipDto getDto(Long accountId, Integer slsId) {
		return selectVip(accountId, slsId);
	}
}
