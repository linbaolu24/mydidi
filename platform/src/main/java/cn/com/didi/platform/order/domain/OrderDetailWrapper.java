package cn.com.didi.platform.order.domain;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;

public class OrderDetailWrapper {
	public Long getOrderId() {
		return orderDto.getOrderId();
	}
	public void setOrderId(Long orderId) {
		orderDto.setOrderId(orderId);
	}
	public Integer getSlsId() {
		return orderDto.getSlsId();
	}
	public void setSlsId(Integer slsId) {
		orderDto.setSlsId(slsId);
	}
	public Integer getFlsId() {
		return orderDto.getFlsId();
	}
	public void setFlsId(Integer flsId) {
		orderDto.setFlsId(flsId);
	}
	public String getDescription() {
		return orderDto.getDescription();
	}
	public void setDescription(String description) {
		orderDto.setDescription(description);
	}
	public String getCname() {
		return orderDto.getCname();
	}
	public void setCname(String cname) {
		orderDto.setCname(cname);
	}
	public String getCci() {
		return orderDto.getCci();
	}
	public void setCci(String cci) {
		orderDto.setCci(cci);
	}
	public String getConsumerAddress() {
		return orderDto.getConsumerAddress();
	}
	public void setConsumerAddress(String consumerAddress) {
		orderDto.setConsumerAddress(consumerAddress);
	}
	public String getCas() {
		return orderDto.getCas();
	}
	public void setCas(String cas) {
		orderDto.setCas(cas);
	}
	public String getConsumerName() {
		return orderDto.getConsumerName();
	}
	public void setConsumerName(String consumerName) {
		orderDto.setConsumerName(consumerName);
	}
	public String getState() {
		return orderDto.getState();
	}
	public void setState(String state) {
		orderDto.setState(state);
	}
	public String getCause() {
		return orderDto.getCause();
	}
	public void setCause(String cause) {
		orderDto.setCause(cause);
	}
	public Long getConsumerAccountId() {
		return orderDto.getConsumerAccountId();
	}
	public void setConsumerAccountId(Long consumerAccountId) {
		orderDto.setConsumerAccountId(consumerAccountId);
	}
	public Long getMerchantAccountId() {
		return orderDto.getMerchantAccountId();
	}
	public void setMerchantAccountId(Long merchantAccountId) {
		orderDto.setMerchantAccountId(merchantAccountId);
	}
	public BigDecimal getLng() {
		return orderDto.getLng();
	}
	public void setLng(BigDecimal lng) {
		orderDto.setLng(lng);
	}
	public BigDecimal getLat() {
		return orderDto.getLat();
	}
	public void setLat(BigDecimal lat) {
		orderDto.setLat(lat);
	}
	public String getBusinessCharge() {
		return orderDto.getBusinessCharge();
	}
	public void setBusinessCharge(String businessCharge) {
		orderDto.setBusinessCharge(businessCharge);
	}
	public String getBusinessCategory() {
		return orderDto.getBusinessCategory();
	}
	public void setBusinessCategory(String businessCategory) {
		orderDto.setBusinessCategory(businessCategory);
	}
	public String getMasterName() {
		return orderDto.getMasterName();
	}
	public void setMasterName(String masterName) {
		orderDto.setMasterName(masterName);
	}
	public String getMci() {
		return orderDto.getMci();
	}
	public void setMci(String mci) {
		orderDto.setMci(mci);
	}
	public Integer getCost() {
		return orderDto.getCost();
	}
	public void setCost(Integer cost) {
		orderDto.setCost(cost);
	}
	public Long getDealId() {
		return orderDto.getDealId();
	}
	public void setDealId(Long dealId) {
		orderDto.setDealId(dealId);
	}
	public Date getOrt() {
		return orderDto.getOrt();
	}
	public void setOrt(Date ort) {
		orderDto.setOrt(ort);
	}
	public Date getOct() {
		return orderDto.getOct();
	}
	public void setOct(Date oct) {
		orderDto.setOct(oct);
	}
	public Date getSst() {
		return orderDto.getSst();
	}
	public void setSst(Date sst) {
		orderDto.setSst(sst);
	}
	public Date getOet() {
		return orderDto.getOet();
	}
	public void setOet(Date oet) {
		orderDto.setOet(oet);
	}
	public Date getOfst() {
		return orderDto.getOfst();
	}
	public void setOfst(Date ofst) {
		orderDto.setOfst(ofst);
	}
	public Integer getEvaluation() {
		return orderDto.getEvaluation();
	}
	public void setEvaluation(Integer evaluation) {
		orderDto.setEvaluation(evaluation);
	}
	public String getTextEvaluation() {
		return orderDto.getTextEvaluation();
	}
	public void setTextEvaluation(String textEvaluation) {
		orderDto.setTextEvaluation(textEvaluation);
	}
	public String getCancelFlag() {
		return orderDto.getCancelFlag();
	}
	public void setCancelFlag(String cancelFlag) {
		orderDto.setCancelFlag(cancelFlag);
	}
	public Integer getCommission() {
		return orderDto.getCommission();
	}
	public void setCommission(Integer commission) {
		orderDto.setCommission(commission);
	}
	public Integer getPoundage() {
		return orderDto.getPoundage();
	}
	public void setPoundage(Integer poundage) {
		orderDto.setPoundage(poundage);
	}
	public BigDecimal getMlng() {
		return orderDto.getMlng();
	}
	public void setMlng(BigDecimal mlng) {
		orderDto.setMlng(mlng);
	}
	public BigDecimal getMlat() {
		return orderDto.getMlat();
	}
	public void setMlat(BigDecimal mlat) {
		orderDto.setMlat(mlat);
	}
	public int getTotolEvaluation() {
		return eveDto.getTotolEvaluation();
	}
	public void setTotolEvaluation(int totolEvaluation) {
		eveDto.setTotolEvaluation(totolEvaluation);
	}
	public int getOrderCount() {
		return eveDto.getOrderCount();
	}
	public void setOrderCount(int orderCount) {
		eveDto.setOrderCount(orderCount);
	}
	public BigDecimal getMasterEvaluation() {
		return eveDto.getMasterEvaluation();
	}
	public void setMasterEvaluation(BigDecimal masterEvaluation) {
		eveDto.setMasterEvaluation(masterEvaluation);
	}
	private OrderDto orderDto;
	private OrderEvaluationDto eveDto;
	private String mpp;
	private String merchantName;//商户名称
	public OrderDetailWrapper(OrderDto orderDto, OrderEvaluationDto eveDto) {
		super();
		this.orderDto = orderDto;
		if(eveDto==null){
			eveDto=OrderEvaluationDto.ZERO;
		}
		this.eveDto = eveDto;
	}
	
	public OrderDetailWrapper(OrderDto orderDto, OrderEvaluationDto eveDto,String mpp,String merchantName) {
		super();
		this.orderDto = orderDto;
		if(eveDto==null){
			eveDto=OrderEvaluationDto.ZERO;
		}
		this.eveDto = eveDto;
		this.mpp=mpp;
		this.merchantName=merchantName;
	}
	public String getMpp() {
		return mpp;
	}
	public void setMpp(String mpp) {
		this.mpp = mpp;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
}
