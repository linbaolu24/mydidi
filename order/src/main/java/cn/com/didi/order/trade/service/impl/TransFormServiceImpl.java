package cn.com.didi.order.trade.service.impl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.lock.LockManager;
import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.domains.ali.AlipayTransToAccountResponse;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.IAliTradeService;
import cn.com.didi.order.trade.service.ITradeInfoService;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.trade.service.ITransFormService;
import cn.com.didi.order.trade.service.IWechatTradeService;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.produce.IAppEnv;
@Service
public class TransFormServiceImpl implements ITransFormService {
	private static final Logger LOGGER=LoggerFactory.getLogger(TransFormServiceImpl.class);
	@Resource
	protected ITradeInfoService tradeInfoService;
	@Resource
	protected ITradeService tradeService;
	@Resource
	protected LockManager myLockManager;
	@Resource
	protected IAppEnv appEnv;
	@Resource
	protected IAliTradeService aliTradeService;
	@Resource
	protected IWechatTradeService wechatService;
	private int tradeInfoLength=1000;

	@Override
	public void audit(Long dealId, DealEnum deal,String cause) {
		DealDto dto = tradeInfoService.selectDeal(dealId);
		if (dto == null) {
			throw new IllegalArgumentException("提现记录不存在。");
		}
		if(!TradeCategory.OUT.codeEqual(dto.getCategory())){
			throw new IllegalArgumentException("非提现记录不能审核。");
		}
		dto.setCause(cause);
		if (DealEnum.NOT_PASSING.equals(deal)) {
			rollBack(dto);
		} else {
			/*
			 * String lockName = DomainConstatns.LOCK_DRAW_PREFIX + "_" +
			 * dto.getDai(); ILock lock = myLockManager.accquireLock(lockName);
			 * try { lock.lock((long) appEnv.getLockedWait(),
			 * TimeUnit.MILLISECONDS); } finally { lock.unlock(); }
			 */
			auditInternal(dto, deal);
		}
	}
	protected void auditInternal(DealDto dto, DealEnum deal) {
		PayAccountEnum enums = ICodeAble.getCode(PayAccountEnum.values(), dto.getDat());
		if (enums == null) {
			throw new IllegalArgumentException("不正确的目标账户类型。");
		}
		if (enums.equals(PayAccountEnum.ALIPAY)) {
			drawByAlipay(dto);
		} else {
			drawByWechat(dto);
		}

	}

	public void drawByAlipay(DealDto dto) {
		int i = tradeService.preAuditing(dto.getDealId());
		if (i <= 0) {
			throw new IllegalArgumentException("支付宝转账,交易ID"+dto.getDealId()+"预审核锁定失败。");
		}
		IResult<AlipayTransToAccountResponse> result = aliTradeService.sendTransForm(dto);
		AlipayTransToAccountResponse response = result.getData();
		if (!result.success() || (response != null && !response.success())) {
			// 如果结果不正确
			String alMessage = response == null ? "" : response.getMsg();
			String aliSubMessage = response == null ? "" : response.getSub_msg();
			try {
				tradeService.recoverAuditing(dto.getDealId());
			} catch (Exception e) {
				throw new MessageObjectException(
						OrderMessageConstans.DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR, alMessage,
						aliSubMessage);
			}
			throw new MessageObjectException(OrderMessageConstans.DEAL_ALI_TRANSFER_TO_ACCOUNT_EXCEPTION, alMessage,
					aliSubMessage);
		}
		DealDto dealDto = generator(dto.getDealId(), response.getOrder_id(), response.getBody());
		dealDto.setCause(dto.getCause());
		try {
			tradeService.auditing(dealDto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new MessageObjectException(OrderMessageConstans.DEAL_ALI_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR);
		}

	}

	protected DealDto generator(Long dealId, String tradeId, String tradeInfo) {
		DealDto dto = new DealDto();
		dto.setDealId(dealId);
		dto.setTradeId(tradeId);
		if (!StringUtils.isEmpty(tradeInfo)) {
			dto.setTradeinfo(tradeInfo.substring(Math.min(tradeInfo.length(), tradeInfoLength)));
		}
		dto.setState(DealEnum.FINISH.getCode());
		return dto;
	}

	public void drawByWechat(DealDto dto) {
		int i = tradeService.preAuditing(dto.getDealId());
		if (i <= 0) {
			throw new IllegalArgumentException("微信转账,交易ID"+dto.getDealId()+"预审核锁定失败。");
		}
		IResult<WechatPayCustomerReturnVo> result = wechatService.sendTransForm(dto);
				
		WechatPayCustomerReturnVo response = result.getData();
		if (!result.success() || (response != null && response.verifySuccess())) {
			// 如果结果不正确
			String alMessage = response == null ? "" : response.getReturn_msg();
			String aliSubMessage = response == null ? "" : response.getErr_code_des();
			try {
				tradeService.recoverAuditing(dto.getDealId());
			} catch (Exception e) {
				throw new MessageObjectException(
						OrderMessageConstans.DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION_AND_RECOVER_ERROR, alMessage,
						aliSubMessage);
			}
			throw new MessageObjectException(OrderMessageConstans.DEAL_WECHAT_TRANSFER_TO_ACCOUNT_EXCEPTION, alMessage,
					aliSubMessage);
		}
		DealDto dealDto = generator(dto.getDealId(), response.getPartner_trade_no(), null);
		try {
			tradeService.auditing(dealDto);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new MessageObjectException(OrderMessageConstans.DEAL_WECHAT_TRANSFER_TO_ACCOUNT_SUCCESS_UPDATE_STATE_ERROR);
		}
	}
	

	protected void rollBack(DealDto dto) {
		tradeService.rollBack(dto, true);
	}

}
