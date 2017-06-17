package cn.com.didi.platform.order.domain;

import java.math.BigDecimal;

import cn.com.didi.user.users.domain.MerchantDto;

public class OrderMerchantWrapperJAO {
	private MerchantDto mdto;

	public OrderMerchantWrapperJAO(MerchantDto mdto) {
		super();
		this.mdto = mdto;
	}

	public Long getAccountId() {
		return mdto.getAccountId();
	}

	public void setAccountId(Long accountId) {
		mdto.setAccountId(accountId);
	}

	public String getDetailAddress() {
		return mdto.getDetailAddress();
	}

	public void setDetailAddress(String detailAddress) {
		mdto.setDetailAddress(detailAddress);
	}

	public String getMasterName() {
		return mdto.getMastername();
	}

	public void setMasterName(String mastername) {
		mdto.setMastername(mastername);
	}

	public BigDecimal getLng() {
		return mdto.getLng();
	}

	public void setLng(BigDecimal lng) {
		mdto.setLng(lng);
	}

	public BigDecimal getLat() {
		return mdto.getLat();
	}

	public void setLat(BigDecimal lat) {
		mdto.setLat(lat);
	}
	 
}
