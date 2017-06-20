package cn.com.didi.order.trade.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.dao.mapper.DealDtoMapper;
import cn.com.didi.order.trade.domain.DealDrawListDto;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.system.service.ICodeTextResolver;
@Service
public class TradeInfoServiceImpl implements ITradeInfoService {
	private static final Long SYSTEM_ACCOUNT = 0L;
	@Resource
	protected DealDtoMapper dealDtoMapper;
	/*@Resource
	protected MerchantRemainingDtoMapper merchantRemainingDtoMapper;*/
	@Resource
	protected IAccountAssetsService accountAssers;
	@Resource
	protected ICodeTextResolver codeTextResolver;
	@Override
	@Transactional
	public void createTrade(DealDto dto, TranscationalCallBack<DealDto> deal) {
		Date time = dto.getCreateTime();
		if (time == null) {
			dto.setCreateTime(new Date());
		}
		if (dto.getUpdateTime() == null) {
			dto.setUpdateTime(dto.getCreateTime());
		}
		if(dto.getDai()==null){
			dto.setDai(SYSTEM_ACCOUNT);
		}
		deal.invoeBefore(dto);
		/*if(dto.getRemain()==null){
			MerchantRemainingDto mrd = new MerchantRemainingDto();
			mrd.setAccountId(SYSTEM_ACCOUNT);
			mrd.setAt(dto.getDat());
			MerchantRemainingDto now=merchantRemainingDtoMapper.selectByPrimaryKey(mrd );
			dto.setRemain(now.getRemaining()+(dto.getAmount()==null?0:dto.getAmount()));
		}*/
		dealDtoMapper.insertSelective(dto);
		if (deal != null) {
			deal.invoke(dto);
		}
	}

	@Override
	public DealDto selectDeal(Long dealId) {
		return dealDtoMapper.selectByPrimaryKey(dealId);
	}
	/*MerchantRemainingDto mrd = new MerchantRemainingDto();
	mrd.setAccountId(SYSTEM_ACCOUNT);
	mrd.setAt(pay.getAccountEnum().getCode());
	mrd.setRemaining(pay.getCost());
	merchantRemainingDtoMapper.updateAddRemaining(mrd);
	mrd.setAccountId(pay.getbId());
	merchantRemainingDtoMapper.updateAddRemaining(mrd);*/
	@Override
	@Transactional
	public int finishDeal(DealDto source, PayResultDto pay, TranscationalCallBack<PayResultDto> deal) {
		if(deal!=null){
		    deal.invoeBefore(pay);
		}
		Long remain = getSystemRemain(pay.getAccountEnum().getCode());
		remain = remain == null ? 0 + pay.getCost() : remain + pay.getCost();
		int count = dealDtoMapper.updateDealState(pay.getDealId(), DealEnum.FINISH.getCode(), source.getState(), pay.getTradeId(), remain);//
		if (count == 0) {//
			/*
			 * dealDtoMapper.updatePureDealState(pay.getDealId(),
			 * "1",pay.getTradeId());
			 */
			return count;
		} //
		if (pay.isAddRemaining()&&source.getAmount()!=null&&source.getAmount()!=0) {
			MerchantDayRemainingDto dto=new MerchantDayRemainingDto();
			dto.setAccountId(source.getDai());
			dto.setPat(source.getDat());
			dto.setDaytime(DateUtil.getCurrentYYYYMMDD(source.getCreateTime()));
			Long systemReamin=(long)(source.getAmount().intValue());
			dto.setRemaining((long)(source.getAmount().intValue()-(source.getPoundage()==null?0:source.getPoundage())));
			accountAssers.addMerchantDayRemainingDto(dto,pay.isSystemOnly(),systemReamin);
		}
		if (deal != null) {
			deal.invoke(pay);
		}
		return count;
	}
	protected Long getSystemRemain(String dat){
		MerchantDayRemainingDto remain=accountAssers.selectSystemRemaining();
		return remain.getRemaining();
	}
	@Override
	public Long selectOrderIdFromDeal(Long dealId) {
		return dealDtoMapper.selectOrderIdFromDeal(dealId);
	}

	@Override
	public IPage<DealListDto> selectTrades(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<DealListDto> list = (PageList<DealListDto>) dealDtoMapper.selectTrades(interval, pageBounds);
		return new MybatisPaginatorPage<>(list);
	}
	public IPage<DealListDto> selectDraws(TimeInterval interval){
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<DealListDto> list = (PageList<DealListDto>) dealDtoMapper.selectDraws(interval, pageBounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	public int fail(Long dealId, String cause) {
		return dealDtoMapper.updateFail(dealId, cause);
	}
	protected void popNormalDeal(DealDto dto,TradeCategory category){
		Date time = dto.getCreateTime();
		if (time == null) {
			dto.setCreateTime(new Date());
		}
		if (dto.getUpdateTime() == null) {
			dto.setUpdateTime(dto.getCreateTime());
		}
		dto.setCategory(category.getCode());
		dto.setDealType(category.getType());
	}
	public void draw(DealDto deal,TranscationalCallBack<DealDto> callBack){
		popNormalDeal(deal, TradeCategory.OUT);
		accountAssers.decreMerchantDayRemainingIfCan(null);
		dealDtoMapper.insertSelective(deal);
	}

	@Override
	public List<DealDrawListDto> selectDrawList(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), false);
		List<DealDrawListDto> lists=dealDtoMapper.selectDrawList(interval, pageBounds);
		codeTextResolver.resolverDraw(lists);
		return lists;
	}

	@Override
	public int auditing(DealDto dealId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	@Transactional
	public void rollBack(DealDto deal) {
		
	}

	@Override
	@Transactional
	public void draw(DealDto pay) {
		drawInternal(pay);
		
	}
	protected void drawInternal(DealDto pay){
		popNormalDraw(pay);
		MerchantDayRemainingDto mat = getMerchantDayDto(pay);
		boolean ifCan = accountAssers.decreMerchantDayRemainingIfCan(mat);
		if (!ifCan) {
			//TODO 抛出异常
		}
		createTrade(pay, null);
	}
	protected MerchantDayRemainingDto getMerchantDayDto(DealDto pay){
		return null;
	}
	protected void popNormalDraw(DealDto pay){
		pay.setCategory(TradeCategory.OUT.getCode());
		pay.setDealType(TradeCategory.OUT.getType());
		pay.setSai(accountAssers.getSystemAccount());
		pay.setSat(pay.getDat());
		pay.setCreateTime(new Date());
		pay.setCommission(0);
	}

	@Override
	public int updateTradeState(Long dealId, String dest,String cat, String... source) {
		return dealDtoMapper.updateDealStateAndSourceArray(dealId, dest, cat, source);
	}
}
