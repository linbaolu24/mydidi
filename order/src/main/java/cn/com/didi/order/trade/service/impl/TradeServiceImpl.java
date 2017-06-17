package cn.com.didi.order.trade.service.impl;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.didi.core.lock.ILock;
import cn.com.didi.core.lock.LockManager;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.PayAccountDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.service.IAccountAssetsService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;

/**
 * 交易服务实现
 * 
 * @author xlm
 *
 */
@Service
public class TradeServiceImpl implements ITradeService {
	private static final Logger LOGGER =LoggerFactory.getLogger(TradeServiceImpl.class);
	@Resource
	protected ITradeInfoService tradeInfoService;
	@Resource
	protected IAccountAssetsService accountAssetsService;
	@Resource
	protected LockManager myLockManager;
	@Resource
	protected IAppEnv appEnv;
	protected String drawLockManagerPrefix="tradeDraw";
	
	@Override
	@Transactional
	public void createTrade(DealDto dto, TranscationalCallBack<DealDto> deal) {
		if(StringUtils.isEmpty(dto.getState())){
			dto.setState(DealEnum.WAITTING.getCode());
		}
		tradeInfoService.createTrade(dto, deal);
		//deal.invoke(dto);
	}

	@Override
	public PayAccountDto getAccountDto(Long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DealDto selectDeal(Long dealId) {
		return tradeInfoService.selectDeal(dealId);
	}

	@Override
	public IResult<Void> finishDeal(PayResultDto payResult, TranscationalCallBack<PayResultDto> dealCallBack) {
		Long dealId = payResult.getDealId();
		for (int i = 0; i < 3; i++) {
			DealDto deal = tradeInfoService.selectDeal(dealId);
			IResult<Void> result = dealExist(deal);
			if (result != null) {
				return result;
			}
			result = dealVerify(payResult, deal);
			if (result != null) {
				return result;
			}
			if (DealEnum.FINISH.getCode().equals(deal.getState())) {// 如果已经是成功状态的deal
				return null;
			}
			payResult.setDeal(deal);
			int count = tradeInfoService.finishDeal(deal, payResult, dealCallBack);
			if (count > 0) {
				return null;
			}
		}
		return null;//返回错误
	}

	protected <T> IResult<T> dealExist(DealDto deal) {
		if (deal == null) {
			//  订单不存在
			return ResultFactory.error(OrderMessageConstans.DEAL_NOT_EXIST);
		}
		return null;
	}

	protected <T> IResult dealVerify(PayResultDto payResult, DealDto deal) {

		if (payResult.getCost() == null || !payResult.getCost().equals(deal.getAmount())) {
			// 金额等不想等
			return ResultFactory.error(OrderMessageConstans.DEAL_ACCOUNT_NOT_EQUAL);
		}
		return null;
	}

	@Override
	public Long selectOrderIdFromDeal(Long dealId) {
		return tradeInfoService.selectOrderIdFromDeal(dealId);
	}

	@Override
	public IPage<DealListDto> selectTrades(TimeInterval interval) {
		return tradeInfoService.selectTrades(interval);
	}

	@Override
	public int fail(Long dealId, String cause) {
		return tradeInfoService.fail(dealId, cause);
	}

	@Override
	@Transactional
	public void draw(DealDto pay) {
		if (pay == null || pay.getDai() == null) {
			return;
		}
		String lockName = DomainConstatns.LOCK_DRAW_PREFIX + "_" + pay.getDai();
		ILock lock = myLockManager.accquireLock(lockName);
		boolean locked=false;
		try {
			locked = lock.lock((long) appEnv.getLockedWait(), TimeUnit.MICROSECONDS);
			LOGGER.debug("获取锁{},结果{}", lockName, locked);
			if (locked) {
				drawInternal(pay);
			}
		} finally {
			if (lock != null&&locked) {
				try {
					lock.unlock();
				} catch (Exception e) {
					LOGGER.error("释放锁失败{},异常{}",lockName,e);
				}
			}
		}
	}
	protected void drawInternal(DealDto pay){
		popNormalDraw(pay);
		MerchantDayRemainingDto mat = getMerchantDayDto(pay);
		boolean ifCan = accountAssetsService.decreMerchantDayRemainingIfCan(mat);
		if (!ifCan) {
			//TODO 抛出异常
		}
		tradeInfoService.createTrade(pay, null);
	}
	protected MerchantDayRemainingDto getMerchantDayDto(DealDto pay){
		return null;
	}

	protected void popNormalDraw(DealDto pay) {
		pay.setCategory(TradeCategory.OUT.getCode());
		pay.setDealType(TradeCategory.OUT.getType());
		pay.setSai(accountAssetsService.getSystemAccount());
		pay.setSat(pay.getDat());
		if (pay.getCreateTime() == null) {
			pay.setCreateTime(new Date());
		}
		if (pay.getUpdateTime() == null) {
			pay.setUpdateTime(pay.getCreateTime());
		}
		pay.setState(DealEnum.WAITTING.getCode());
		pay.setCommission(0);
	}

	@Override
	@Transactional
	public void rollBack(DealDto pay, boolean needLock) {
		if (needLock) {
			String lockName = DomainConstatns.LOCK_DRAW_PREFIX + "_" + pay.getDai();
			ILock lock = myLockManager.accquireLock(lockName);
			boolean locked=false;
			try {
				locked = lock.lock((long) appEnv.getLockedWait(), TimeUnit.MICROSECONDS);
				LOGGER.debug("回滚获取锁{},结果{}", lockName, locked);
				if (locked) {
					rollBackInternal(pay);
				}
			} finally {
				if (lock != null&&locked) {
					try {
						lock.unlock();
					} catch (Exception e) {
						LOGGER.error("释放锁失败{},异常{}", lockName, e);
					}
				}
			}
		} else {
			rollBackInternal(pay);
		}
	}
	public void rollBackInternal(DealDto pay){
		tradeInfoService.rollBack(pay);
	}
	@Override
	public int auditing(DealDto dealId) {
		return tradeInfoService.auditing(dealId);
	}

	@Override
	public int preAuditing(Long dealId) {
		return tradeInfoService.updateTradeState(dealId, DealEnum.PRE_AUDIT.getCode(), TradeCategory.OUT.getCode(),
				DealEnum.WAITTING.getCode(), DealEnum.FAIL.getCode());
	}



	@Override
	public int recoverAuditing(Long dealId) {
		return tradeInfoService.updateTradeState(dealId, DealEnum.WAITTING.getCode(), TradeCategory.OUT.getCode(), DealEnum.PRE_AUDIT.getCode());
	}

}
