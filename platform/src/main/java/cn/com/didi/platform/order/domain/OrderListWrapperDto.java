package cn.com.didi.platform.order.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.order.orders.domain.OrderListDto;

public class OrderListWrapperDto {
	private OrderListDto dto;

	public Integer getCost() {
		return dto.getCost();
	}

	public String getMci() {
		return dto.getMci();
	}

	public void setCost(Integer cost) {
		dto.setCost(cost);
	}

	public void setMci(String mci) {
		dto.setMci(mci);
	}

	public Date getOct() {
		return dto.getOct();
	}

	public String getMasterName() {
		return dto.getMasterName();
	}

	public void setOct(Date oct) {
		dto.setOct(oct);
	}

	public Date getOrt() {
		return dto.getOrt();
	}

	public void setMasterName(String masterName) {
		dto.setMasterName(masterName);
	}

	public void setOrt(Date ort) {
		dto.setOrt(ort);
	}

	public String getState() {
		return dto.getState();
	}

	public void setState(String state) {
		dto.setState(state);
	}

	public String getSlsId() {
		return dto.getSlsId();
	}

	public void setSlsId(String slsId) {
		dto.setSlsId(slsId);
	}

	public String getCname() {
		return dto.getCname();
	}

	public void setCname(String cname) {
		dto.setCname(cname);
	}

	public String getLat() {
		return dto.getLat();
	}

	public void setLat(String lat) {
		dto.setLat(lat);
	}

	public String getLng() {
		return dto.getLng();
	}

	public void setLng(String lng) {
		dto.setLng(lng);
	}

	public String getBusinessCategory() {
		return dto.getBusinessCategory();
	}

	public void setBusinessCategory(String businessCategory) {
		dto.setBusinessCategory(businessCategory);
	}

	public String getBusinessCharge() {
		return dto.getBusinessCharge();
	}

	public void setBusinessCharge(String businessCharge) {
		dto.setBusinessCharge(businessCharge);
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

	public String getOrderId() {
		return String.valueOf(dto.getOrderId());
	}

	public void setOrderId(String orderId) {
		dto.setOrderId(Long.parseLong(orderId));
	}
	public static OrderListWrapperDto  wrapOrderListWrapperDto(OrderListDto list){
		OrderListWrapperDto dto=new OrderListWrapperDto();
		dto.dto=list;
		return dto;
	}
	
	public static List<OrderListWrapperDto>  wrapOrderListWrapperDto(List<OrderListDto> list){
		if(CollectionUtils.isEmpty(list)){
           return null;
		}
		List<OrderListWrapperDto> lists=new ArrayList<>(list.size());
		for(OrderListDto one:list){
			lists.add(wrapOrderListWrapperDto(one));
		}
		return lists;
	}
}
