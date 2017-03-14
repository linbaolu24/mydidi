package cn.com.didi.platform.trade.domain;

import java.util.Date;

import cn.com.didi.domain.query.TimeInterval;

public class TradeTimeInterval {
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public TimeInterval toTimeInterval(){
		TimeInterval interval=new TimeInterval();
		interval.setStartTime(startTime);
		interval.setEndTime(endTime);
		interval.setKey(category);
		interval.setPageIndex(pageIndex);
		interval.setPageSize(pageSize);
		return interval;
	}
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	private String category;
	/**
	 * 页索引
	 */
	private int pageIndex;
	/**
	 * 页大小
	 */
	private int pageSize;
	
}
