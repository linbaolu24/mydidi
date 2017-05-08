package cn.com.didi.order.trade.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DepositDto;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.order.trade.service.ITradeTranscationCallBack;
import cn.com.didi.order.trade.service.ITradeTranscationCallBackFinder;
import cn.com.didi.order.trade.util.AliPayBuilder;
import cn.com.didi.thirdExt.produce.IAppEnv;

/**
 * @author xlm
 *
 */
@Service
public class TradeTranscationCallBackFinderImpl implements ITradeTranscationCallBackFinder{
	@Resource
	protected IDepositService depositService;
	@Resource
	protected IAppEnv appEnv;
	@Override
	public ITradeTranscationCallBack<DealDto> findCreateTranscationalCallBack(Long accountId,String type, String key) {
		return new DepositeTranscationalCallBack();
	}

	@Override
	public TranscationalCallBack<PayResultDto> findFinishTranscationalCallBack(String type) {
		return new DepositePayFinishTranscationalCallBack();
	}
	protected class DepositeTranscationalCallBack implements ITradeTranscationCallBack<DealDto>{
		private OrderDealDescDto builder;
	public void invoeBefore(DealDto t){ 
		   t.setAmount(appEnv.getDeposite());
		   t.setCment("押金");
		   t.setSpecialType(SpecialTypeEnum.NORMAL.getCode());
		   t.setDai(appEnv.getSystemAccountId());
		   DepositDto dto=new DepositDto();
		   dto.setPat(t.getSat());
		   dto.setAmount((long)t.getAmount().intValue());
		   dto.setCreateTime(t.getCreateTime());
		   dto.setEndTime(null);
		   dto.setPayState(t.getState());
		   dto.setBpn(null);
		   dto.setSai(t.getSai());
		   t.setCategory(TradeCategory.DEPOSIT.getCode());
		   t.setDealType(TradeCategory.DEPOSIT.getType());
		   depositService.addDepositDto(dto);
		   t.setOrderId(dto.getDepositId());
		}
		public void invoke(DealDto deal) {
			
			depositService.updateTradeId(deal.getOrderId(), deal.getDealId());
		}
		@Override
		public void popForAli(AliPayBuilder builder) {
			builder.bcsubject("押金");
			builder.bcbody("押金");
			builder.notify_url(appEnv.getDepositeAliNotifyUrl());
		}
	
		
	}
	
	protected class DepositePayFinishTranscationalCallBack implements TranscationalCallBack<PayResultDto> {
		public void invoeBefore(PayResultDto pay){
			pay.setAddRemaining(false);
		}
		public void invoke(PayResultDto pay) {
			DealDto deal=pay.getDeal();
			depositService.updateTradeState(deal.getOrderId(), DealEnum.FINISH.getCode());
		}
	
	}

}
