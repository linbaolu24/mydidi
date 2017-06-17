package cn.com.didi.order.trade.domain;

import java.util.Date;

public class DealDrawListDto {
	/**
     * 交易id
     */
    private String dealId;

    /**
     * 0 表示待确认 1表示已确认 2失败
     */
    private String state;
    /**
     * 
     */
    private String stateText;

    /**
     * 单位分
     */
    private Integer amount;

    /**
     * 0表示平台收入 1表示体现 2表示违约金
     */
    private String cause;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 
     */
    private String pat;
	public String getDealId() {
		return dealId;
	}
	public void setDealId(String dealId) {
		this.dealId = dealId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateText() {
		return stateText;
	}
	public void setStateText(String stateText) {
		this.stateText = stateText;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getPat() {
		return pat;
	}
	public void setPat(String pat) {
		this.pat = pat;
	}
	public String getDrawId(){
		if(createTime==null){
			return String.valueOf(dealId);
		}
		return createTime.getTime()+"T"+dealId;
	}
}
