package cn.com.didi.order.trade.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.dao.mapper.MerchantDayRemainingDtoMapper;
import cn.com.didi.order.trade.dao.mapper.MerchantRemainingDtoMapper;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.domain.MerchantRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
import cn.com.didi.order.trade.util.MerchantRemainingUtil;
@Service
public class AccountAssertServiceImpl implements IAccountAssetsService{
	private static int DEAULT_DAY=19900101;
	private static final int LOCKED=3;
	private static final Long SYSTEM_ACCOUNT_ID=0L;
	@Resource
	private MerchantDayRemainingDtoMapper myMerchantDayRemainingDtoMapper;
	@Resource
	private MerchantRemainingDtoMapper systemMerchantRemainDtoMapper;
	@Override
	public void addMerchantDayRemainingDto(MerchantDayRemainingDto dto,boolean systemOnly) {
		PayAccountEnum payEnum=cn.com.didi.core.property.ICodeAble.getCode(PayAccountEnum.values(), dto.getPat());
		if(payEnum==null){
			return ;
		}
		if(dto.getDaytime()==null){
			dto.setDaytime(DateUtil.getCurrentYYYYMMDD());
		}
		if(StringUtils.isEmpty(dto.getCategory())){
			dto.setCategory("0");
		}
		if (!systemOnly) {
			myMerchantDayRemainingDtoMapper.saveMerchantDayRemainingDto(dto);
		}
		updateSystemRemain(payEnum, dto.getRemaining());
		//增加系统余额
	}

	@Override
	public List<MerchantDayRemainingDto> listMerchantDay(Long accountId) {
		int maxed=getMaxDay();
		return myMerchantDayRemainingDtoMapper.selectByAccountId(accountId,maxed);
	}
	protected int getMaxDay(){
		return DateUtil.getIntervalYYYYMMDD(LOCKED);
	}
	@Override
	public void rollBackMerchantDayRemainingDto(MerchantDayRemainingDto dto) {
		if(dto==null||dto.getRemaining()==null){
			return;
		}
		dto.setDaytime(DEAULT_DAY);
		myMerchantDayRemainingDtoMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public boolean decreMerchantDayRemainingIfCan(MerchantDayRemainingDto dto) {
		PayAccountEnum payEnum=cn.com.didi.core.property.ICodeAble.getCode(PayAccountEnum.values(), dto.getPat());
		if(payEnum==null){
			return false;
		}
		MerchantDayRemainingDto remain=countRemain(dto.getAccountId(), payEnum);
		if(remain==null||Math.abs(remain.getRemaining())<=Math.abs(dto.getRemaining())){//余额不足
			return false;
		}
		MerchantRemainingUtil.copyProperty(dto, remain);
		if(remain.getRemaining()>0){
			remain.setRemaining(-remain.getRemaining());
		}
		//remain.setCategory("1");
		myMerchantDayRemainingDtoMapper.saveMerchantDayRemainingDto(remain);//总支出有一个总支出的地方,先这样干
		updateSystemRemain(payEnum,(long)remain.getRemaining().intValue());
		return false;
	}
    public void updateSystemRemain(PayAccountEnum payEnum,Integer remain){
    	updateSystemRemain(payEnum,(long)remain.intValue());
	}
	public void updateSystemRemain(PayAccountEnum payEnum,Long remain){
		MerchantRemainingDto dto=new MerchantRemainingDto();
		dto.setAccountId(SYSTEM_ACCOUNT_ID);
		dto.setPat(payEnum.getCode());
		systemMerchantRemainDtoMapper.updateAddRemaining(dto);
	}
	@Override
	public MerchantDayRemainingDto selectSystemRemaining() {
		MerchantDayRemainingDto dto=new MerchantDayRemainingDto();
		Long source= systemMerchantRemainDtoMapper.countRemain(SYSTEM_ACCOUNT_ID);
		dto.setAccountId(SYSTEM_ACCOUNT_ID);
		dto.setRemaining(source);
		return dto;
	}

	@Override
	public List<MerchantDayRemainingDto> countRemain(Long accountId) {
		int maxDay=getMaxDay();
		List<MerchantDayRemainingDto> lists=myMerchantDayRemainingDtoMapper.countByAccountId(accountId, maxDay);
		return lists;
		
	}
	public static void copyProperty(MerchantRemainingDto source,MerchantDayRemainingDto dest){
		dest.setAccountId(source.getAccountId());
		dest.setPat(source.getPat());

		dest.setRemaining(source.getRemaining());
	}

	@Override
	public MerchantDayRemainingDto countRemain(Long accountId, PayAccountEnum payAccountEnum) {
		int maxDay=getMaxDay();
		MerchantDayRemainingDto dto=myMerchantDayRemainingDtoMapper.countByAccountIdAndAt(accountId, maxDay,payAccountEnum.getCode());
		return dto;
	}

}
