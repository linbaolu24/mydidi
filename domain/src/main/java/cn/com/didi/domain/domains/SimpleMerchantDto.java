package cn.com.didi.domain.domains;

import java.math.BigDecimal;

import cn.com.didi.domain.util.CrEnum;
import cn.com.didi.domain.util.State;

public class SimpleMerchantDto implements IMerchantDto{
	private String masterName;
	private String mci;
	private BigDecimal lat;
	private BigDecimal lng;
	private Long merchantId;
	private String state;
	private String cr;

	public String getMci() {
		return mci;
	}
	public void setMci(String mci) {
		this.mci = mci;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCr() {
		return cr;
	}
	public void setCr(String cr) {
		this.cr = cr;
	}
	@Override
	public boolean validState() {
		return CrEnum.PASSING.codeEqual(cr)&&State.VALID.codeEqual(state);
	}
	
	
}
