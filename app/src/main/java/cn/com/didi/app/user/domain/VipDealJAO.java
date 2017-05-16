package cn.com.didi.app.user.domain;

import cn.com.didi.user.users.domain.VipDto;

public class VipDealJAO extends VipDto{
	private Long dealId;

	public Long getDealId() {
		return dealId;
	}

	public void setDealId(Long dealId) {
		this.dealId = dealId;
	}
	
}
