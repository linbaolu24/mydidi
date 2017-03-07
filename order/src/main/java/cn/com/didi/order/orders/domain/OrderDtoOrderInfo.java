package cn.com.didi.order.orders.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.github.miemiedev.mybatis.paginator.domain.Order;

import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.order.IOrderInfo;

public class OrderDtoOrderInfo implements IOrderInfo{
	public Long getOrderId() {
		return dto.getOrderId();
	}

	public void setOrderId(Long orderId) {
		dto.setOrderId(orderId);
	}

	public Integer getSlsId() {
		return dto.getSlsId();
	}

	public void setSlsId(Integer slsId) {
		dto.setSlsId(slsId);
	}

	public Integer getFlsId() {
		return dto.getFlsId();
	}

	public void setFlsId(Integer flsId) {
		dto.setFlsId(flsId);
	}

	public String getDescription() {
		return dto.getDescription();
	}

	public void setDescription(String description) {
		dto.setDescription(description);
	}

	public String getCname() {
		return dto.getCname();
	}

	public void setCname(String cname) {
		dto.setCname(cname);
	}

	public String getCci() {
		return dto.getCci();
	}

	public void setCci(String cci) {
		dto.setCci(cci);
	}

	public String getConsumerAddress() {
		return dto.getConsumerAddress();
	}

	public void setConsumerAddress(String consumerAddress) {
		dto.setConsumerAddress(consumerAddress);
	}

	public String getCas() {
		return dto.getCas();
	}

	public void setCas(String cas) {
		dto.setCas(cas);
	}

	public String getConsumerName() {
		return dto.getConsumerName();
	}

	public void setConsumerName(String consumerName) {
		dto.setConsumerName(consumerName);
	}

	public String getState() {
		return dto.getState();
	}

	public void setState(String state) {
		dto.setState(state);
	}

	public String getCause() {
		return dto.getCause();
	}

	public void setCause(String cause) {
		dto.setCause(cause);
	}

	public Long getConsumerAccountId() {
		return dto.getConsumerAccountId();
	}

	public void setConsumerAccountId(Long consumerAccountId) {
		dto.setConsumerAccountId(consumerAccountId);
	}

	public Long getMerchantAccountId() {
		return dto.getMerchantAccountId();
	}

	public void setMerchantAccountId(Long merchantAccountId) {
		dto.setMerchantAccountId(merchantAccountId);
	}

	public BigDecimal getLng() {
		return dto.getLng();
	}

	public void setLng(BigDecimal lng) {
		dto.setLng(lng);
	}

	public BigDecimal getLat() {
		return dto.getLat();
	}

	public void setLat(BigDecimal lat) {
		dto.setLat(lat);
	}

	public String getBusinessCharge() {
		return dto.getBusinessCharge();
	}

	public void setBusinessCharge(String businessCharge) {
		dto.setBusinessCharge(businessCharge);
	}

	public String getBusinessCategory() {
		return dto.getBusinessCategory();
	}

	public void setBusinessCategory(String businessCategory) {
		dto.setBusinessCategory(businessCategory);
	}

	public String getMasterName() {
		return dto.getMasterName();
	}

	public void setMasterName(String masterName) {
		dto.setMasterName(masterName);
	}

	public String getMci() {
		return dto.getMci();
	}

	public void setMci(String mci) {
		dto.setMci(mci);
	}

	public Integer getCost() {
		return dto.getCost();
	}

	public void setCost(Integer cost) {
		dto.setCost(cost);
	}

	public Long getDealId() {
		return dto.getDealId();
	}

	public void setDealId(Long dealId) {
		dto.setDealId(dealId);
	}

	public Date getOrt() {
		return dto.getOrt();
	}

	public void setOrt(Date ort) {
		dto.setOrt(ort);
	}

	public Date getOct() {
		return dto.getOct();
	}

	public void setOct(Date oct) {
		dto.setOct(oct);
	}

	public Date getSst() {
		return dto.getSst();
	}

	public void setSst(Date sst) {
		dto.setSst(sst);
	}

	public Date getOet() {
		return dto.getOet();
	}

	public void setOet(Date oet) {
		dto.setOet(oet);
	}

	public Date getOfst() {
		return dto.getOfst();
	}

	public void setOfst(Date ofst) {
		dto.setOfst(ofst);
	}

	public Integer getEvaluation() {
		return dto.getEvaluation();
	}

	public void setEvaluation(Integer evaluation) {
		dto.setEvaluation(evaluation);
	}

	public String getTextEvaluation() {
		return dto.getTextEvaluation();
	}

	public void setTextEvaluation(String textEvaluation) {
		dto.setTextEvaluation(textEvaluation);
	}

	public String getCancelFlag() {
		return dto.getCancelFlag();
	}

	public void setCancelFlag(String cancelFlag) {
		dto.setCancelFlag(cancelFlag);
	}

	public Integer getCommission() {
		return dto.getCommission();
	}

	public void setCommission(Integer commission) {
		dto.setCommission(commission);
	}

	public Integer getPoundage() {
		return dto.getPoundage();
	}

	public void setPoundage(Integer poundage) {
		dto.setPoundage(poundage);
	}

	public BigDecimal getMlng() {
		return dto.getMlng();
	}

	public void setMlng(BigDecimal mlng) {
		dto.setMlng(mlng);
	}

	public BigDecimal getMlat() {
		return dto.getMlat();
	}

	public void setMlat(BigDecimal mlat) {
		dto.setMlat(mlat);
	}

	public OrderDto getDto() {
		return dto;
	}

	public void setDto(OrderDto dto) {
		this.dto = dto;
	}

	private OrderDto dto;

	@Override
	public Long getConsumerId() {
		return getConsumerAccountId();
	}

	@Override
	public Long getMerchantId() {
		return getMerchantAccountId();
	}

	@Override
	public boolean selfBusiness() {
		return BusinessCategory.SELF.getCode().equals(getBusinessCategory());
	}
	
}
