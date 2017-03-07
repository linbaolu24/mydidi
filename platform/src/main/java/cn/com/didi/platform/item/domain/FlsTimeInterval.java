package cn.com.didi.platform.item.domain;

import java.util.Date;

import cn.com.didi.domain.query.TimeInterval;

public class FlsTimeInterval {
	private TimeInterval time;

	public FlsTimeInterval() {

	}

	public FlsTimeInterval(boolean isNew) {
		if (isNew) {
			this.time = new TimeInterval();
		}
	}
	public TimeInterval time() {
		return time;
	}

	public void time(TimeInterval time) {
		this.time = time;
	}



	public Date getStartTime() {
		return time.getStartTime();
	}

	public void setStartTime(Date startTime) {
		time.setStartTime(startTime);
	}

	public Date getEndTime() {
		return time.getEndTime();
	}

	public void setEndTime(Date endTime) {
		time.setEndTime(endTime);
	}

	public int getPageIndex() {
		return time.getPageIndex();
	}

	public void setPageIndex(int pageIndex) {
		time.setPageIndex(pageIndex);
	}

	public int getPageSize() {
		return time.getPageSize();
	}

	public void setPageSize(int pageSize) {
		time.setPageSize(pageSize);
	}

	public String getState() {
		return time.getState();
	}

	public void setState(String state) {
		time.setState(state);
	}

	public String getKey() {
		return time.getKey();
	}

	public void setKey(String key) {
		time.setKey(key);
	}

	public Long getAccountId() {
		return time.getAccountId();
	}

	public void setAccountId(Long accountId) {
		time.setAccountId(accountId);
	}

	public Integer getId() {
		return (Integer)time.getId();
	}

	public void setId(Integer id) {
		time.setId(id);
	}
    public Integer getFlsId(){
    	return getId();
    }
    public void setFlsId(Integer flsId){
    	setId(flsId);
    }
}
