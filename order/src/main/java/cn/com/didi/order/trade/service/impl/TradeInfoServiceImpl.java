package cn.com.didi.order.trade.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.dao.mapper.DealDtoMapper;
import cn.com.didi.order.trade.dao.mapper.MerchantRemainingDtoMapper;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.MerchantRemainingDto;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
@Service
public class TradeInfoServiceImpl implements ITradeInfoService {
	private static final Long SYSTEM_ACCOUNT = 0L;
	@Resource
	protected DealDtoMapper dealDtoMapper;
	@Resource
	protected MerchantRemainingDtoMapper merchantRemainingDtoMapper;

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
		if(dto.getRemain()==null){
			MerchantRemainingDto mrd = new MerchantRemainingDto();
			mrd.setAccountId(SYSTEM_ACCOUNT);
			mrd.setAt(dto.getDat());
			MerchantRemainingDto now=merchantRemainingDtoMapper.selectByPrimaryKey(mrd );
			dto.setRemain(now.getRemaining()+dto.getAmount());
		}
		dealDtoMapper.insert(dto);
		if (deal != null) {
			deal.invoke(dto);
		}
	}

	@Override
	public DealDto selectDeal(Long dealId) {
		return dealDtoMapper.selectByPrimaryKey(dealId);
	}

	@Override
	public int finishDeal(DealDto source, PayResultDto pay, TranscationalCallBack<PayResultDto> deal) {
		int count = dealDtoMapper.updateDealState(pay.getDealId(), "1", source.getState());
		if (count == 0) {
			return count;
		}
		MerchantRemainingDto mrd = new MerchantRemainingDto();
		mrd.setAccountId(SYSTEM_ACCOUNT);
		mrd.setAt(pay.getAccountEnum().getCode());
		mrd.setRemaining(pay.getCost());
		merchantRemainingDtoMapper.updateAddRemaining(mrd);

		mrd.setAccountId(pay.getbId());
		merchantRemainingDtoMapper.updateAddRemaining(mrd);
		if (deal != null) {
			deal.invoke(pay);
		}
		return count;
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

}
