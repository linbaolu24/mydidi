package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.math.BigDecimal;

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

	
}
