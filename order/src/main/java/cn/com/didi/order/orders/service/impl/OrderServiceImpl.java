package cn.com.didi.order.orders.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.util.BusinessCharge;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.DomainMessageConstans;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.message.push.service.IPushMessageService;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderDtoOrderInfo;
import cn.com.didi.order.orders.domain.OrderStateCostDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderLifeListener;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.service.ITradeService;
import cn.com.didi.order.util.OrderMessageConstans;

/**
 * 订单服务接口
 * 
 * @author xlm
 *
 */
@Service
public class OrderServiceImpl extends AbstractDecoratAbleMessageOrderService {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IReciverSearchService search;
	@Resource
	private ITradeService tradeService;
	@Resource
	protected IPushMessageService pushMessageService;
	protected TranscationalCallBack<DealDto> dealTranscationalCallBack = new TranscationalCallBack<DealDto>() {

		@Override
		public void invoke(DealDto t) {
			orderInfoService.updateOrderDealId(t.getOrderId(), t.getDealId());
		}
	};
	protected TranscationalCallBack<PayResultDto> dealFinishTranscationalCallBack = new TranscationalCallBack<PayResultDto>() {

		@Override
		public void invoke(PayResultDto t) {
			orderInfoService.updateOrderState(t.getOrderId(), OrderState.ORDER_STATE_Pending_EVALUATION.getCode(),
					OrderState.ORDER_STATE_PENDING_CHARGE.getCode(), (Integer) null);
		}
	};
	
	protected TranscationalCallBack<PayResultDto> dealFinishTranscationalCallBackCannel = new TranscationalCallBack<PayResultDto>() {

		@Override
		public void invoke(PayResultDto t) {
			orderInfoService.updateOrderState(t.getOrderId(), OrderState.ORDER_STATE_CANNEL.getCode(),
					OrderState.ORDER_STATE_PENDING_CHARGE.getCode(), (Integer) null);
		}
	};

	public IOrderRuslt<Void> publish(OrderDto dto) {
		dto.setState(OrderState.ORDER_STATE_PUBLISH.getCode());
		orderInfoService.addOrder(dto);
		OrderRuslt<Void> orderResult = new OrderRuslt<Void>(dto.getOrderId());
		OrderDtoOrderInfo info = new OrderDtoOrderInfo();
		info.setDto(dto);
		notifyPublish(info, orderResult);
		return orderResult;
	}

	public IOrderRuslt<Void> autoDispatch(Long orderId, Long bId) {
		OrderDto info = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> temp = normalBVerify(info, bId);
		if (temp != null) {
			return temp;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_TAKING, info.getState())) {
			return null;
		}
		OrderRuslt<Void> orderResult = new OrderRuslt<Void>(info.getOrderId());
		/*
		 * if (OrderState.ORDER_STATE_PUBLISH.isLess(info.getState())) { //
		 * 订单状态已完成分派 返回成功即可 return orderResult; }
		 */
		IReciverDto dto = search.match(info.getCas(), new Point(info.getLat(), info.getLng()), info.getSlsId());
		if (dto == null) {
			// todo没有找到接单人
			orderResult.setCode(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getCode());
			orderResult.setMessage(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getMessage());
		} else {
			orderInfoService.updateOrderState(info.getOrderId(), OrderState.ORDER_STATE_TAKING.getCode(),
					info.getState(), dto.getAccountId());
			sendMessage(info, "您有新的订单", "您有新的订单", dto);
			sendMessage(info, "您的订单已接单", "您的订单已接单", false);
			// 对接单人发送消息,和订单人发送消息
		}
		// notifyAutoDispatch(info, orderResult, dto);
		return orderResult;
	}

	public void reAutoDispatch(IOrderInfo info) {
		notifyReAutoDispatch(info);
	}

	public IOrderRuslt<Void> notifyOrder(Long orderId, Long bId) {
		OrderDto info = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> temp = normalBVerify(info, bId);
		if (temp != null) {
			return temp;
		}
		if(isStateRepeat(OrderState.ORDER_STATE_NOTIFY, info.getState())){
			return null;
		}
		OrderRuslt<Void> orderResult = new OrderRuslt<Void>(info.getOrderId());
		List<IReciverDto> reciverDtos = search.list(new Point(info.getLat(), info.getLng()), info.getSlsId());
		if (CollectionUtils.isEmpty(reciverDtos)) {
			// todo 没有找到接单人
			orderResult.setCode(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getCode());
			orderResult.setMessage(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getMessage());
		}else{
			orderInfoService.updateOrderState(orderId, OrderState.ORDER_STATE_NOTIFY.getCode(), info.getState(),(Integer) null);
			sendMessage(info,  "您有新的订单", "您有新的订单", reciverDtos);
		}
		return orderResult;
	}

	public IOrderRuslt<Void> accept(Long orderId, Long bId) {
		OrderDto info = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> temp = normalCVerify(info, bId);
		if (temp != null) {
			return temp;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_TAKING, info.getState())) {
			return null;
		}
		temp = new OrderRuslt<>(orderId);
		orderInfoService.updateOrderState(orderId, OrderState.ORDER_STATE_TAKING.getCode(), info.getState(), info.getMerchantAccountId());
		sendMessage(info,  "您的订单已被接单", "您的订单已被接单", false);
		return temp;

	}

	public IOrderRuslt<Void> startService(Long orderId, Long mercharId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_START_SERVICE, order.getState())) {
			return null;
		}
		orderResult = new OrderRuslt<>(orderId);
		orderInfoService.updateOrderStateSs(orderId, OrderState.ORDER_STATE_START_SERVICE.getCode(), order.getState());
		// 发送消息
		sendMessage(order,  "您的订单师傅开始服务", "您的订单师傅开始服务", false);
		return orderResult;

	}

	public IOrderRuslt<Void> finishService(Long orderId, Long mercharId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		orderResult = new OrderRuslt<>(orderId);
		OrderState destState = OrderState.ORDER_STATE_FINISH;
		if (BusinessCharge.isCharge(order.getBusinessCharge())) {
			destState = OrderState.ORDER_STATE_Pending_EVALUATION;
		}
		if (isStateRepeat(destState, order.getState())) {
			return null;
		}

		orderInfoService.updateOrderStateFs(orderId, destState.getCode(), order.getState());
		// TODO 发送消息
		sendMessage(order,  "您的订单师傅完成服务", "您的订单师傅完成服务", false);
		return orderResult;
		// 发送消息
		// notifyFinishService(info);
	}

	public IOrderRuslt<Void> charge(Long orderId, Long mercharId, int cost) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		OrderState destState = OrderState.ORDER_STATE_PENDING_CHARGE;
		Integer iCost = null;
		if (!BusinessCharge.isCharge(order.getBusinessCharge())) {
			// TODO 非收费服务不需要发起收款
			destState = OrderState.ORDER_STATE_Pending_EVALUATION;
		} else {
			iCost = cost;
		}
		if (isStateRepeat(destState, order.getState())) {
			return null;
		}
		orderInfoService.updateOrderState(orderId, destState.getCode(), order.getState(), iCost);
		sendMessage(order,  "您的订单师傅发起收费", "您的订单师傅发起收费", false);
		// 更新状态
		// 发送消息
		return null;
		// notifyCharge(info);
	}

	public IOrderRuslt<Void> evaluation(Long orderId, Long bId, int eval, String textEval) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCVerify(order, bId);
		if (orderResult != null) {
			return orderResult;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_FINISH, order.getState())) {
			return null;
		}
		orderInfoService.updateOrderStateAndEvaluation(orderId, OrderState.ORDER_STATE_FINISH.getCode(),
				order.getState(), eval, textEval);
		return null;
	}

	public IOrderRuslt<Void> timeout(Long orderId, Long bId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		if (order != null && OrderState.ORDER_STATE_FAIL.getCode().equals(order.getState())) {// 不需要重复超时
			return null;
		}
		IOrderRuslt<Void> orderResult = normalBVerify(order, bId);
		if (orderResult != null) {
			return orderResult;
		}
		if (!OrderState.ORDER_STATE_PUBLISH.getCode().equals(order.getState())
				|| !OrderState.ORDER_STATE_NOTIFY.getCode().equals(order.getState())) {
			return new OrderRuslt<>(OrderMessageConstans.ORDER_HAVE_TIKEING.getMessage(),
					OrderMessageConstans.ORDER_HAVE_TIKEING.getCode());
		}
		orderInfoService.updateOrderFailState(orderId, OrderState.ORDER_STATE_FAIL.getCode(), order.getState(),
				"timeout");
		return null;
	}

	public IOrderRuslt<OrderStateCostDto> cannel(Long orderId, Long bId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderStateCostDto> orderResult = normalBVerify(order, bId, false);
		if (orderResult != null) {
			// orderResult.setData(null);
			return orderResult;
		}

		if (OrderState.ORDER_STATE_FINISH.isGreatEqual(order.getState())) {// 如果已经完成服务
			return new OrderRuslt<>(OrderMessageConstans.ORDER_SERVICE_FINISH_CANNOT_CANNEL);
		}
		OrderState state = OrderState.ORDER_STATE_CANNEL;
		OrderStateCostDto stateDto = new OrderStateCostDto();
		if (BusinessCharge.isCharge(order.getBusinessCharge())) {
			stateDto.setCost(order.getCommission());
			state = OrderState.ORDER_STATE_PENDING_CHARGE;
		}
		stateDto.setState(state.getCode());
		
		if (!OrderState.ORDER_STATE_CANNEL.getCode().equals(order.getState())) {// 如果不是
																				// 重复取消
			orderInfoService.updateOrderCannelState(orderId, state.getCode(), order.getState(), stateDto.getCost());
			/*if (OrderState.ORDER_STATE_CANNEL.equals(state)) {
				// TODO 通知商户订单被取消
				
			}*/
			order.setState(state.getCode());
			sendMessage(order,  "您的订单已被取消", "您的订单已被取消", false);
		}
		OrderRuslt<OrderStateCostDto> or = new OrderRuslt<>(orderId);
		or.setData(stateDto);
		return or;
		// notifyCannel(info);
	}

	public void noReceiver(IOrderInfo info) {
		notifyNoReceiver(info);
	}

	protected void sendMessage(OrderDto dto, String text, String title, boolean isMerchant) {
		IReciverDto reciver = isMerchant ? getBReciver(dto) : getCReciver(dto);
		sendMessage(dto, text, title, reciver);
		/*if (reciver == null) {
			LOGGER.error("订单{}推送消息,未找到接受方", dto);
		}
		MessageDto messageDto = createMessage(dto, text, title);
		try {
			IResult<Void> result = pushMessageService.push(reciver, messageDto);
			if(result!=null&&!result.success()){
				LOGGER.error("订单{}推送消息,发送消息失败 code{},message{}", dto,result.getCode(),result.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("订单{}推送消息,发送消息失败", dto,e);
		}*/
	}
	
	protected void sendMessage(OrderDto dto, String text, String title, IReciverDto reciver) {
		if (reciver == null) {
			LOGGER.error("订单{}推送消息,未找到接受方", dto);
		}
		MessageDto messageDto = createMessage(dto, text, title);
		try {
			IResult<Void> result = pushMessageService.push(reciver, messageDto);
			if(result!=null&&!result.success()){
				LOGGER.error("订单{}推送消息,发送消息失败 code{},message{}", dto,result.getCode(),result.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("订单{}推送消息,发送消息失败", dto,e);
		}
	}
	
	protected void sendMessage(OrderDto dto, String text, String title,List<IReciverDto> lists) {
		if (CollectionUtils.isEmpty(lists)) {
			LOGGER.error("订单{}推送消息,接受方传入为空", dto);
		}
		MessageDto messageDto = createMessage(dto, text, title);
		try {
			IResult<Void> result = pushMessageService.push(lists, messageDto);
			if(result!=null&&!result.success()){
				LOGGER.error("订单{}推送消息,发送消息失败 code{},message{}", dto,result.getCode(),result.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error("订单{}推送消息,发送消息失败", dto,e);
		}
	}

	protected IReciverDto getBReciver(OrderDto dto) {
		if (dto == null || dto.getMerchantAccountId() == null) {
			return null;
		}
		return search.match(dto.getMerchantAccountId());
	}

	protected IReciverDto getCReciver(OrderDto dto) {
		if (dto == null || dto.getConsumerAccountId() == null) {
			return null;
		}
		return search.match(dto.getConsumerAccountId());
	}

	protected MessageDto createMessage(OrderDto order, String text, String title) {
		MessageDto dto=new MessageDto();
		dto.setText(text);
		dto.setTitle(title);
		Map p=new HashMap();
		p.put(DomainConstatns.ORDER_ID, order.getOrderId());
		p.put(DomainConstatns.STATE, order.getState());
		p.put(DomainConstatns.COST, order.getCost());
		dto.setContent(JSON.toJSONString(p));
		return dto;

	}

	protected <T> IOrderRuslt<T> normalCVerify(OrderDto order, Long mercharId) {
		IOrderRuslt<T> temp = orderExist(order);//
		if (temp != null) {
			return temp;
		}
		temp = verifyMercharId(order.getMerchantAccountId(), mercharId);
		if (temp != null) {
			return temp;
		}
		temp = isFinish(order.getState(), order.getOrderId());
		return temp;
	}

	protected <T> IOrderRuslt<T> normalBVerify(OrderDto order, Long cID) {
		return normalBVerify(order, cID, true);
	}

	protected <T> IOrderRuslt<T> normalBVerify(OrderDto order, Long cID, boolean isFinish) {
		IOrderRuslt<T> temp = orderExist(order);
		if (temp != null) {
			return temp;
		}
		temp = verifyCousemerId(order.getConsumerAccountId(), cID);
		if (temp != null) {
			return temp;
		}
		if (isFinish) {
			temp = isFinish(order.getState(), order.getOrderId());
		}
		return temp;
	}

	/**
	 * 如果订单的商户ID和和当前账户ID不一致
	 * 
	 * @param orderMercharId
	 * @param mercharId
	 * @return
	 */
	protected <T> IOrderRuslt<T> verifyMercharId(Long orderMercharId, Long mercharId) {
		if (orderMercharId != null && !orderMercharId.equals(mercharId)) {
			// 订单商户ID和当前商户ID不一致
			return new OrderRuslt<>(OrderMessageConstans.ORDER_ACCOUTID_NOT_EQUAL.getMessage(),
					OrderMessageConstans.ORDER_ACCOUTID_NOT_EQUAL.getCode());
		}
		return null;
	}

	/**
	 * 订单消费者ID和当前账户ID
	 * 
	 * @param orderCId
	 * @param cId
	 * @return
	 */
	protected <T> IOrderRuslt<T> verifyCousemerId(Long orderCId, Long cId) {
		if (orderCId == null || !orderCId.equals(cId)) {
			// 订单消费者ID和当前消费者ID不一致
			return new OrderRuslt<>(OrderMessageConstans.ORDER_ACCOUTID_NOT_EQUAL.getMessage(),
					OrderMessageConstans.ORDER_ACCOUTID_NOT_EQUAL.getCode());
		}
		return null;
	}

	/**
	 * 订单是否存在
	 * 
	 * @param order
	 * @return
	 */
	protected <T> IOrderRuslt<T> orderExist(OrderDto order) {
		if (order == null) {
			// 订单不存在
			return new OrderRuslt<>(OrderMessageConstans.ORDER_NOT_EXIST.getMessage(),
					OrderMessageConstans.ORDER_NOT_EXIST.getCode());
		}
		return null;
	}

	/**
	 * 订单是否已完成订单
	 * 
	 * @param state
	 * @param orderId
	 * @return
	 */
	protected <T> IOrderRuslt<T> isFinish(String state, Long orderId) {
		if (OrderState.ORDER_STATE_FINISH.isGreatEqual(state)) {
			// 订单已结束
			return new OrderRuslt<>(OrderMessageConstans.ORDER_FINISHED.getMessage(),
					OrderMessageConstans.ORDER_FINISHED.getCode());
		}
		return null;
	}

	/** 判断是否是重复状态,如果是状态重复返回true */
	protected boolean isStateRepeat(OrderState destState, String cState) {
		if (destState.isLess(cState)) {
			return true;
		}
		return false;
	}

	@Override
	public IOrderRuslt<Long> createDeal(Long orderId, Long bId, PayAccountEnum payEnum) {
		OrderDto dto = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Long> result = normalBVerify(dto, bId);
		if (result != null) {
			return result;
		}
		if (!OrderState.ORDER_STATE_PENDING_CHARGE.getCode().equals(dto.getState())) {
			return new OrderRuslt<>(OrderMessageConstans.ORDER_NOT_PENDING_CHARGE.getMessage(),
					OrderMessageConstans.ORDER_NOT_PENDING_CHARGE.getCode());
		}
		if (dto.getDealId() != null) {
			LOGGER.error("订单{}存在对应交易{},将被替换成新的交易记录", dto.getOrderId(), dto.getDealId());
		}
		// PayAccountDto
		// accountDto=tradeService.getAccountDto(dto.getMerchantAccountId());
		DealDto deal = new DealDto();
		deal.setCommission(dto.getCommission());
		TradeCategory category = TradeCategory.IN;
		if ("1".equals(dto.getCancelFlag())) {
			category = TradeCategory.PENALTY;
		}
		deal.setCategory(category.getCode());
		deal.setAmount(dto.getCost());
		deal.setDai(dto.getMerchantAccountId());
		deal.setSat(payEnum.getCode());
		deal.setDat(payEnum.getCode());
		deal.setDealType(category.getType());
		deal.setOrderId(dto.getOrderId());
		deal.setSai(dto.getConsumerAccountId());
		tradeService.createTrade(deal, dealTranscationalCallBack);
		result = new OrderRuslt<>("", OrderRuslt.SUCCESS_CODE, null, deal.getDealId());
		return result;
	}

	@Override
	public IOrderRuslt<Void> finishDeal(PayResultDto dto) {
		if (dto.getOrderId() == null) {
			Long orderId = tradeService.selectOrderIdFromDeal(dto.getDealId());
			if (orderId == null) {
				return new OrderRuslt<>(OrderMessageConstans.ORDER_DEAL_NOT_EXIST);
			}
			dto.setOrderId(orderId);
		}
		OrderDto orderDto = orderInfoService.selectOrderSubjectInformation(dto.getOrderId());
		IOrderRuslt<Void> result = orderExist(orderDto);
		if (result != null) {
			return result;
		}
		// result = isFinish(orderDto.getState(), orderDto.getOrderId());
		if (!OrderState.ORDER_STATE_PENDING_CHARGE.getCode().equals(orderDto.getState())
				&& !isRepateFinishDeal(orderDto.getState())) {
			// 非待付款状态
			return new OrderRuslt<>(OrderMessageConstans.ORDER_NOT_PENDING_CHARGE_FINISH_DEAL);
		}
		dto.setbId(orderDto.getMerchantAccountId());
		IResult<Void> deal = tradeService.finishDeal(dto, "0".equals(orderDto.getCancelFlag())?dealFinishTranscationalCallBack:dealFinishTranscationalCallBackCannel);
		if (deal == null || deal.success()) {
			sendMessage(orderDto, "您的订单用户已完成付款", "您的订单用户已完成付款", true);
			return OrderRuslt.successResult();
		}
		return new OrderRuslt<>(deal.getMessage(), deal.getCode());
	}

	protected boolean isRepateFinishDeal(String state) {
		return OrderState.ORDER_STATE_FINISH.getCode().equals(state)
				|| OrderState.ORDER_STATE_Pending_EVALUATION.getCode().equals(state)
				|| OrderState.ORDER_STATE_CANNEL.getCode().equals(state);
	}
	@Resource
	protected void setOrderLifeListener(IOrderLifeListener lifeLisnter){
		setListenerList(Arrays.asList(lifeLisnter));
	}
}
