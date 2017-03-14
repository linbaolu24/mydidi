package cn.com.didi.order.trade.domain;

import java.util.Date;

public class DealListDto {
	  public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getRemain() {
		return remain;
	}

	public void setRemain(Integer remain) {
		this.remain = remain;
	}

	/**
     * 交易id
     */
    private Long dealId;

    /**
     * 0 表示待确认 1表示已确认 2失败
     */
    private String state;
    /**
     * 0代表收入 1代表支出
     */
    private String dealType;

    /**
     * 单位分
     */
    private Integer amount;

    /**
     * 0表示平台收入 1表示体现 2表示违约金
     */
    private String category;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String comments;

    /**
     * 余额
     */
    private Integer remain;
    
    

}
