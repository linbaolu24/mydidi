package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderEvaluationDto implements Serializable{
	public static final OrderEvaluationDto ZERO=new OrderEvaluationDto();
	static{
		ZERO.setMasterEvaluation(BigDecimal.ZERO);
		ZERO.setOrderCount(0);
		ZERO.setTotolEvaluation(0);
	}
	public int getTotolEvaluation() {
		return totolEvaluation;
	}
	public void setTotolEvaluation(int totolEvaluation) {
		this.totolEvaluation = totolEvaluation;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6891269301163876222L;
	private int orderCount;
	private BigDecimal masterEvaluation;
	private int totolEvaluation;
	private Long merchantAccountId;
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	public BigDecimal getMasterEvaluation() {
		return masterEvaluation;
	}
	public void setMasterEvaluation(BigDecimal masterEvaluation) {
		this.masterEvaluation = masterEvaluation;
	}
	public Long getMerchantAccountId() {
		return merchantAccountId;
	}
	public void setMerchantAccountId(Long merchantAccountId) {
		this.merchantAccountId = merchantAccountId;
	}

	public String cal() {
		if (getMasterEvaluation() == null) {
			setMasterEvaluation(new BigDecimal(getTotolEvaluation()).divide(new BigDecimal(getOrderCount()), 1,
					RoundingMode.HALF_UP));
		}
		return getMasterEvaluation().toString();
	}
	
}
