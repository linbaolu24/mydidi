package cn.com.didi.domain.domains;

import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.DealDto;

public class PayResultDto {
	public Long getDestAccountId() {
		return destAccountId;
	}
	public void setDestAccountId(Long destAccountId) {
		this.destAccountId = destAccountId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer cost) {
		this.cost = cost;
	}
	public Long getbId() {
		return bId;
	}
	public void setbId(Long bId) {
		this.bId = bId;
	}
	public String getPayAccount() {
		return payAccount;
	}
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
	public PayAccountEnum getAccountEnum() {
		return accountEnum;
	}
	public void setAccountEnum(PayAccountEnum accountEnum) {
		this.accountEnum = accountEnum;
	}
	
	private Long orderId;
	private Long dealId;
	private Integer cost;
	private Long bId;
	/**目标账户ID*/
	private Long destAccountId;
	private String payAccount;
	private PayAccountEnum accountEnum;
	private boolean addRemaining=true;//增加余额
	private boolean systemOnly=true;//是否只增加系统余额
	private DealDto deal;
	/**
	 * 交易系统交易ID
	 */
	private String tradeId;
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public boolean isAddRemaining() {
		return addRemaining;
	}
	public void setAddRemaining(boolean addRemaining) {
		this.addRemaining = addRemaining;
	}
	public DealDto getDeal() {
		return deal;
	}
	public void setDeal(DealDto deal) {
		this.deal = deal;
	}
	public boolean isSystemOnly() {
		return systemOnly;
	}
	public void setSystemOnly(boolean systemOnly) {
		this.systemOnly = systemOnly;
	}
	
}
