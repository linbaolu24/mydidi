package cn.com.didi.domain.query;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author xlm
 *
 */
public class TimeInterval implements Serializable{
	public Number getId() {
		return id;
	}

	public void setId(Number id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3121917413500515279L;
	/**
	 * 开始时间
	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 页索引
	 */
	private int pageIndex;
	/**
	 * 页大小
	 */
	private int pageSize;
	/**
	 * 状态
	 */
	private String state;
	/**
	 * 关键字
	 */
	private String key;
	/**
	 * 账户ID
	 */
	private Long accountId;
	/**
	 * 查询ID
	 */
	private Number id;

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

	public String getState() {
		if(StringUtils.isEmpty(state)){
			return null;
		}
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	
}
