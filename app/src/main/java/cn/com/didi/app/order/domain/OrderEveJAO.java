package cn.com.didi.app.order.domain;

/**
 * @author xlm
 *
 */
public class OrderEveJAO {
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getTextEvaluation() {
		return textEvaluation;
	}
	public void setTextEvaluation(String textEvaluation) {
		this.textEvaluation = textEvaluation;
	}
	public Integer getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(Integer evaluation) {
		this.evaluation = evaluation;
	}
	private Long orderId;
	private String textEvaluation;
	private Integer evaluation;
	
}
