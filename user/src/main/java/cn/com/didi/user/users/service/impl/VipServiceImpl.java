package cn.com.didi.user.users.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.State;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
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
		if(accountId==null){
			return false;
		}
		if(slsId==null){
			 slsId=appEnvService.getMfxfSlsId();
		}
		VipDto dto=selectVip(accountId, slsId);
		return hasVip(dto);
		
		
	}
	@Override
	public boolean hasVip(VipDto dto) {
		return dto!=null&&State.VALID.getState().equals(dto.getState());
	}
	public void reg(VipDto vipDto,Long dealId){
		if(vipDto==null||vipDto.getAccountId()==null){
			return ;
		}
		
		DealDto deal=tradeInfoService.selectDeal(dealId);
		if(deal==null||!TradeCategory.DEPOSIT.getCode().equals(deal.getCategory())){
			throw new MessageObjectException(MessageConstans.VIP_NOT_PAY);//未付款不能升级为vip
		}
		boolean isOk=depositeService.existDeposit(deal.getSai(),deal.getOrderId());//判断是否存在
		if(!isOk){
			throw new MessageObjectException(MessageConstans.VIP_PAY_NOT_ARRIVE);
		}
		if(vipDto.getSlsId()==null){
			vipDto.setSlsId(appEnvService.getMfxfSlsId());
		}
		vipDto.setDealId(deal.getDealId());
		vipDto.setPat(deal.getDat());
		vipDto.setState(State.VALID.getState());
		UserDto dto=userService.selectUser(vipDto.getAccountId());
		vipDto.setBusinessCategory(dto.getBusinessCategory());
		vipDto.setRole(dto.getRole());
		vipDto.setPhone(StringUtils.defaultIfBlank(dto.getUserName(), dto.getBpn()));
		if(vipDto.getCreateTime()==null){
			vipDto.setCreateTime(new Date());
		}
		if(vipDto.getUpdateTime()==null){
			vipDto.setUpdateTime(vipDto.getCreateTime());
		}
		vipMapper.insertSelective(vipDto);
	}
	
	
	public void preReg(VipDto vipDto,Long dealId){
		if(vipDto==null||vipDto.getAccountId()==null){
			return ;
		}
		if(vipDto.getSlsId()==null){
			vipDto.setSlsId(appEnvService.getMfxfSlsId());
		}
		if(hasVip(vipDto.getAccountId(),vipDto.getSlsId())){
			throw  new MessageObjectException(MessageConstans.VIP_EXIST_VALID_VIP);
		}
		vipDto.setState(State.UNVALID.getState());//不可用状态
		UserDto dto=userService.selectUser(vipDto.getAccountId());
		vipDto.setBusinessCategory(dto.getBusinessCategory());
		vipDto.setRole(dto.getRole());
		vipDto.setPhone(StringUtils.defaultIfBlank(dto.getUserName(), dto.getBpn()));
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
		DateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
		Date date=DateUtils.addYears(dto.getCreateTime(), 1);
		//format.format(date);
		desc.setUsable(desc.getUsable().replace("#{endTime}",format.format(date) ));
		desc=desc.cloneSelf();
		desc.setVipDto(dto);
		return desc;
		
	}
	protected Integer getDefaultSlsId(){
		return appEnvService.getMfxfSlsId();
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
	@Override
	public IPage<VipDto> listPage(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<VipDto> list = (PageList<VipDto>) vipMapper.selectVips(interval,pageBounds);
		return new MybatisPaginatorPage<>(list);
	}
	@Override
	public void updateVip(Long accountId,Integer sInteger, String cname, String bpn, String pp) {
		if(accountId==null){
			return;
		}
		if(sInteger==null){
			sInteger=appEnvService.getMfxfSlsId();
		}
		VipDto vipDto=new VipDto();
		vipDto.setAccountId(accountId);
		vipDto.setSlsId(sInteger);
		vipDto.setBpn(bpn);
		vipDto.setCname(cname);
		vipDto.setUpdateTime(new Date());
		vipDto.setProfilePhoto(pp);
	    vipMapper.updateByPrimaryKeySelective(vipDto);
		
	}
	@Override
	public void setVipFee(Integer slsId, int fee) {
		appEnvService.changeDeposite(fee);
		
	}
	@Override
	public int getVipFee(Integer slsId) {
		return appEnvService.getDeposite();
	}
	@Override
	public void deleteVip(Long accountId, Integer slsId) {
		VipDtoKey key=new VipDtoKey();
		key.setAccountId(accountId);
		key.setSlsId(slsId);;
		vipMapper.deleteByPrimaryKey(key);
	}
	@Override
	public void upgradeVip(VipDto dto) {
		if(dto.getSlsId()==null){
			dto.setSlsId(appEnvService.getMfxfSlsId());
		}
		dto.setIntervalDay(2);
		vipMapper.upgradeVip(dto);
	}
}
