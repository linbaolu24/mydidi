package cn.com.didi.order.orders.domain;

public class OrderPromptDto implements java.io.Serializable{
	public static final OrderPromptDto ZERO=new OrderPromptDto();
	/**
	 * 
	 */
	private static final long serialVersionUID = -497916337290330579L;
	public int getPsoCount() {
		return psoCount;
	}
	public void setPsoCount(int psoCount) {
		this.psoCount = psoCount;
	}
	public int getUpoCount() {
		return upoCount;
	}
	public void setUpoCount(int upoCount) {
		this.upoCount = upoCount;
	}
	/**
	 * 待服务订单数量
	 */
	private int psoCount;
	/**
	 * 待支付订单数量
	 */
	private int upoCount;
	
}
