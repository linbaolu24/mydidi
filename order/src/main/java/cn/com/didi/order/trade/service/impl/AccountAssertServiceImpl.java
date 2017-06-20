package cn.com.didi.order.trade.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.dao.mapper.DealDtoMapper;
import cn.com.didi.order.trade.dao.mapper.MerchantDayRemainingDtoMapper;
import cn.com.didi.order.trade.dao.mapper.MerchantRemainingDtoMapper;
import cn.com.didi.order.trade.domain.DrawInfoDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.domain.MerchantRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
import cn.com.didi.order.trade.util.MerchantRemainingUtil;
@Service
public class AccountAssertServiceImpl implements IAccountAssetsService{
	private static int DEAULT_DAY=19900101;
	private int LOCKED=-1;
	private int selectLocked=7;
	private static final Long SYSTEM_ACCOUNT_ID=0L;
	@Resource
	private MerchantDayRemainingDtoMapper myMerchantDayRemainingDtoMapper;
	@Resource
	private MerchantRemainingDtoMapper systemMerchantRemainDtoMapper;
	@Resource
	private DealDtoMapper myDealDtoMapper;
	@Override
	public void addMerchantDayRemainingDto(MerchantDayRemainingDto dto,boolean systemOnly) {
		addMerchantDayRemainingDto(dto, systemOnly, dto.getRemaining());
	}
	@Override
	public void addMerchantDayRemainingDto(MerchantDayRemainingDto dto, boolean systemOnly, Long systemRemain) {
		PayAccountEnum payEnum=cn.com.didi.core.property.ICodeAble.getCode(PayAccountEnum.values(), dto.getPat());
		if(payEnum==null){
			return ;
		}
		if(dto.getDaytime()==null){
			dto.setDaytime(getDayTime());
		}
		if(StringUtils.isEmpty(dto.getCategory())){
			dto.setCategory("0");
		}
		if (!systemOnly&&!getSystemAccount().equals(dto.getAccountId())) {
			myMerchantDayRemainingDtoMapper.saveMerchantDayRemainingDto(dto);
		}
		updateSystemRemain(payEnum, systemRemain);//增加系统余额
		
	}
	@Override
	public List<MerchantDayRemainingDto> listMerchantDay(Long accountId) {
		int maxed=getMaxDay();
		return myMerchantDayRemainingDtoMapper.selectByAccountId(accountId,maxed);
	}
	/**包含该天*/
	protected int getMaxDay(){
		if(LOCKED<=0){
			return 209001;
		}
		return DateUtil.getIntervalYYYYMMDD(LOCKED);
	}
	protected Integer getDayTime(){
		if(LOCKED<=0){
			return DEAULT_DAY;
		}
		return DateUtil.getCurrentYYYYMMDD();
	}
	@Override
	public void rollBackMerchantDayRemainingDto(MerchantDayRemainingDto dto,boolean systemOnly) {
		if(dto==null||dto.getRemaining()==null){
			return;
		}
		addMerchantDayRemainingDto(dto, systemOnly);
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
		int maxDay = getMaxDay();
		return countRemain(accountId,maxDay);
	}

	public List<MerchantDayRemainingDto> countRemain(Long accountId, int maxDay) {
		List<MerchantDayRemainingDto> lists = myMerchantDayRemainingDtoMapper.countByAccountId(accountId, maxDay);
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

	@Override
	public Long countFrozeRemain(Long accountId) {
		int maxDay=getMaxDay();
		return countFrozeRemain(accountId, maxDay);
	}

	public Long countFrozeRemain(Long accountId, int maxDay) {

		Long sum = 0L;
		if (lockedSupport()) {
			sum = myMerchantDayRemainingDtoMapper.countFrozeRemain(accountId, maxDay);
		} else {
			Date fromDate=DateUtil.getInterval(selectLocked);
			sum=myDealDtoMapper.countSum(accountId, fromDate, TradeCategory.OUT.getCode());
		}
		return sum == null ? 0 : sum;
	}
	protected boolean lockedSupport(){
		return LOCKED>0;
	}
	@Override
	public DrawInfoDto drawInfo(Long accountId) {
		int maxDay= getMaxDay();
		Long countFrozeRemain=countFrozeRemain(accountId, maxDay);
		List<MerchantDayRemainingDto> lists=countRemain(accountId, maxDay);
		DrawInfoDto infoDto=new DrawInfoDto();
		infoDto.setPending(countFrozeRemain);
		infoDto.setTotal(countFrozeRemain);
		infoDto.setRemainDto(lists);
		if(lists!=null){
			lists.forEach(one->infoDto.setTotal(infoDto.getTotal()+one.getRemaining()));
		}
		return infoDto;
	}
	@Override
	public Long getSystemAccount() {
		return SYSTEM_ACCOUNT_ID;
	}

	

}
