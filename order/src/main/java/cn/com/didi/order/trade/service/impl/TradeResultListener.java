package cn.com.didi.order.trade.service.impl;

import static cn.com.didi.domain.util.AlipayConstants.OUT_TRADE_NO;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Catch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.util.AlipayConstants;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.DealRecordDto;
import cn.com.didi.order.trade.service.IDealRecordService;
import cn.com.didi.order.trade.util.TradeOperations;

@Service
public class TradeResultListener implements IOperationListener<TradeOperations, Object> {
	private static final Logger LOGGER = LoggerFactory.getLogger(TradeResultListener.class);
	@Resource
	protected IDealRecordService recordService;

	@Override
	public void operate(TradeOperations operation, Object data) {
		try {
			if (operation == TradeOperations.NOTIFY_START_ALI_ASYN) {

				Map<String, String> map = (Map<String, String>) data;
				LOGGER.debug("记录阿里异步通知结果,{}", map);
				DealRecordDto record = getFromMap(map);
				recordService.addRecord(record);
			}else if(operation==TradeOperations.NOTIFY_START_WECHAT_ASYN){
				WechatPayNotifyReturnVO returnVO=(WechatPayNotifyReturnVO)data;
				DealRecordDto dto=getFromMap(returnVO);
				LOGGER.debug("记录微信步通知结果,{}", returnVO);
				recordService.addRecord(dto);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}

	protected DealRecordDto getFromMap(Map<String, String> map) {
		DealRecordDto recodr = new DealRecordDto();
		String dealId = (String) map.get(OUT_TRADE_NO);
		if (!StringUtils.isEmpty(dealId)) {
			recodr.setDealId(Long.parseLong(dealId));
		}
		String trade_no = (String) map.get(AlipayConstants.TRADE_NO);
		recodr.setSource(map.toString());
		recodr.setCreateTime(new Date());
		recodr.setCategory(PayAccountEnum.ALIPAY.getCode());
		recodr.setSubCategory("a");
		return recodr;

	}
	
	protected DealRecordDto getFromMap(WechatPayNotifyReturnVO map) {
		DealRecordDto recodr = new DealRecordDto();
		String dealId = (String) map.getOut_trade_no();
		if (!StringUtils.isEmpty(dealId)) {
			recodr.setDealId(Long.parseLong(dealId));
		}
		String trade_no = (String) map.getTransaction_id();
		recodr.setSource(map.getSource());
		recodr.setCreateTime(new Date());
		recodr.setCategory(PayAccountEnum.WECHATPAY.getCode());
		recodr.setSubCategory("a");
		return recodr;

	}

}
