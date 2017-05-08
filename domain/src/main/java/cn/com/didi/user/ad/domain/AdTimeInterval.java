package cn.com.didi.user.ad.domain;

import java.io.Serializable;

import cn.com.didi.domain.query.TimeInterval;

public class AdTimeInterval extends TimeInterval implements Serializable {
	private String displayPosition;
	private Long adId;

	public String getDisplayPosition() {
		return displayPosition;
	}

	public void setDisplayPosition(String displayPosition) {
		this.displayPosition = displayPosition;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}
	
}
