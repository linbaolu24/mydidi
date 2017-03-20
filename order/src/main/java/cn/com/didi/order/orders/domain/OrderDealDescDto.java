package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.util.Date;

public class OrderDealDescDto implements Serializable{
    
	@Override
	public String toString() {
		return "OrderDealDescDto [dealId=" + dealId + ", amount=" + amount + ", orderId=" + orderId + ", cname=" + cname
				+ ", description=" + description + ", dealTime=" + dealTime + "]";
	}
	public Date getDealTime() {
		return dealTime;
	}
	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
	private static final long serialVersionUID = 1L;
	  /**
     * 交易id
     */
    private Long dealId;


    /**
     * 单位分
     */
    private Integer amount;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 服务名称
     */
    private String cname;
    /**
     * 需求描述
     */
    private String description;
    private Date dealTime;

    public Long getDealId() {
		return dealId;
	}
	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
