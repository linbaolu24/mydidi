package cn.com.didi.app.order.controller;

import static cn.com.didi.domain.util.DomainConstatns.ALIPAY_ACCOUNT;
import static cn.com.didi.domain.util.DomainConstatns.BUSINESS_CATEGORY;
import static cn.com.didi.domain.util.DomainConstatns.BUSINESS_CHARGE;
import static cn.com.didi.domain.util.DomainConstatns.CONSUMER_ADDRESS;
import static cn.com.didi.domain.util.DomainConstatns.COST;
import static cn.com.didi.domain.util.DomainConstatns.CSTATE;
import static cn.com.didi.domain.util.DomainConstatns.DESCRIPTION;
import static cn.com.didi.domain.util.DomainConstatns.GT_CID;
import static cn.com.didi.domain.util.DomainConstatns.MASTER_EVALUATION;
import static cn.com.didi.domain.util.DomainConstatns.MASTER_NAME;
import static cn.com.didi.domain.util.DomainConstatns.MCI;
import static cn.com.didi.domain.util.DomainConstatns.MPP;
import static cn.com.didi.domain.util.DomainConstatns.OFST;
import static cn.com.didi.domain.util.DomainConstatns.ORDER_COUNT;
import static cn.com.didi.domain.util.DomainConstatns.ORDER_ID;
import static cn.com.didi.domain.util.DomainConstatns.ORT;
import static cn.com.didi.domain.util.DomainConstatns.RY_TOKEN;
import static cn.com.didi.domain.util.DomainConstatns.STATE;
import static cn.com.didi.domain.util.DomainConstatns.S_TEXT;
import static cn.com.didi.domain.util.DomainConstatns.UPDATE_TIME;
import static cn.com.didi.domain.util.DomainConstatns.WECHAT_ACCOUNT;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderRenderService;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.webBase.util.IAccountResolver;

public class AppBaseOrderController {
	@Resource
	protected IAccountResolver resolver;
	@Resource
	protected IOrderInfoService orderInfo;
	@Resource
	protected IUserService userService;
	@Resource
	protected IOrderService orderService;
	@Resource 
	protected IOrderRenderService orderRenderService ;

	protected void assertOrderId(Long orderId) {
		AssertUtil.assertNotNull(orderId, "订单号");
		AssertUtil.assertNotNegative(orderId, "订单号");
	}

	public Map build(OrderDto order, OrderEvaluationDto eve, UserDto userDto, UserLinkIdDto userLink,MerchantDto dto) {
		Map map =build(order);
		map.put(MCI, order.getMci());
		if (eve != null) {
			map.put(ORDER_COUNT, eve == null ? 0 : eve.getOrderCount());
			map.put(MASTER_EVALUATION, eve == null ? "0" : eve.getMasterEvaluation().toString());
		}
		map.put(CONSUMER_ADDRESS, order.getConsumerAddress());
		if (userLink != null) {
			map.put(ALIPAY_ACCOUNT, userLink.getAlipayAccount());
			map.put(WECHAT_ACCOUNT, userLink.getWechatAccount());
			map.put(GT_CID, userLink.getGtCid());
			map.put(RY_TOKEN, userLink.getRyToken());

		}
		if (userDto != null) {
			map.put(MPP, userDto.getProfilePhoto());
		}
		if(dto!=null){
			map.put(DomainConstatns.MERCHANTLOGO, dto.getMerchantLogo());//
			map.put(DomainConstatns.MERCHANT_NAME,dto.getCname());
		}
		map.put(DomainConstatns.CONSUMER_NAME, order.getConsumerName());
		return map;

	}

	@SuppressWarnings("rawtypes")
	public Map build(OrderDto order) {
		Map map = new HashMap(16);
		map.put(ORDER_ID, order.getOrderId());
		map.put(STATE, order.getState());
		String stateText=orderRenderService.renderStateText(order);
		if(!StringUtils.isEmpty(stateText)){
			map.put(DomainConstatns.STATE_TEXT, stateText);
		}
		map.put(ORT, order.getOrt());
		map.put(OFST, order.getOfst());
		map.put(BUSINESS_CATEGORY, order.getBusinessCategory());
		map.put(BUSINESS_CHARGE, order.getBusinessCharge());
		map.put(DESCRIPTION, order.getDescription());
		map.put(COST, order.getCost());
		map.put(MASTER_NAME, order.getMasterName());
		map.put(DomainConstatns.CONSUMER_ADDRESS, order.getConsumerAddress());// 客户地址
		map.put(DomainConstatns.CCI, order.getCci());// 客户联系方式
		map.put(DomainConstatns.CNAME, order.getCname());
		map.put(DomainConstatns.COMMISSION, order.getCommission());
		map.put(DomainConstatns.SPECIALTYPE, order.getSpecialType());
		return map;

	}

	public Map build(OrderDto order, List<OrderStateRecordDto> list,List<CodeDictionaryDto> dto) {
		Map map = new HashMap(16);
		map.put(ORDER_ID, order.getOrderId());
		map.put(STATE, order.getState());
		map.put(DomainConstatns.COMMISSION, order.getCommission());
		// map.put(ORT, order.getOrt());
		// map.put(OFST, order.getOfst());
		// map.put(BUSINESS_CATEGORY, order.getBusinessCategory());
		map.put(COST, order.getCost());
		map.put(BUSINESS_CHARGE, order.getBusinessCharge());
		map.put(DomainConstatns.SPECIALTYPE, order.getSpecialType());//
		map.put(MASTER_NAME, order.getMasterName());
		String stateText=orderRenderService.renderStateText(order);
		if(!StringUtils.isEmpty(stateText)){
			map.put(DomainConstatns.STATE_TEXT, stateText);
		}
		map.put(MCI, order.getMci());
		map.put(DomainConstatns.CANCEL_FLAG, StringUtils.defaultIfEmpty(order.getCancelFlag(),"0"));
		String text="";
		if (!CollectionUtils.isEmpty(list)) {
			List stateList = new ArrayList(list.size());
			map.put("stateList", stateList);
			for (OrderStateRecordDto one : list) {
				Map oneMap = new HashMap();
				oneMap.put(UPDATE_TIME, one.getUpdateTime());
				oneMap.put(CSTATE, one.getCstate());
				text=findText(dto,  one.getCstate());
				oneMap.put(S_TEXT, text);
				stateList.add(oneMap);
			}
		}
		return map;
	}
	protected String findText(List<CodeDictionaryDto> dto,String cstate){
		if(CollectionUtils.isEmpty(dto)){
			return "";
		}
		for(CodeDictionaryDto one:dto){
			if(one.getDcode().equals(cstate)){
				return one.getText();
			}
		}
		return "";
	}
	public Map buildBDetail(OrderDto order) {
		Map map = build(order);
		map.put(DomainConstatns.OCT, order.getOct());// 订单创建时间
		map.put(DomainConstatns.SST, order.getSst());// 开始服务时间
	
		map.put(DomainConstatns.CONSUMER_NAME, order.getConsumerName());// consumerName
																		// 客户名称
																		// String
																		// Y
		map.put(DomainConstatns.CMENT,order.getCment());
		return map;

	}
}
