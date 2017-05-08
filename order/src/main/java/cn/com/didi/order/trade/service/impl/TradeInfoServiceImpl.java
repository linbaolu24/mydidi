package cn.com.didi.order.trade.service.impl;

import java.util.Date;

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
import cn.com.didi.order.trade.dao.mapper.DealDtoMapper;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
@Service
public class TradeInfoServiceImpl implements ITradeInfoService {
	private static final Long SYSTEM_ACCOUNT = 0L;
	@Resource
	protected DealDtoMapper dealDtoMapper;
	/*@Resource
	protected MerchantRemainingDtoMapper merchantRemainingDtoMapper;*/
	@Resource
	protected IAccountAssetsService accountAssers;

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
			dto.setRemaining((long)source.getAmount().intValue());
			accountAssers.addMerchantDayRemainingDto(dto,pay.isSystemOnly());
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

	@Override
	public int fail(Long dealId, String cause) {
		return dealDtoMapper.updateFail(dealId, cause);
	}

}
