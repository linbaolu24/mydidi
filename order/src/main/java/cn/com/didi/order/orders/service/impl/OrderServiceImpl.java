package cn.com.didi.order.orders.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.util.BusinessCharge;
import cn.com.didi.domain.util.ILocationSearchService;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderDtoOrderInfo;
import cn.com.didi.order.orders.domain.OrderStateCostDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;

/**
 * 订单服务接口
 * 
 * @author xlm
 *
 */
public class OrderServiceImpl extends AbstractDecoratAbleMessageOrderService {
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private ILocationSearchService search;

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
		IReciverDto dto = search.match(new Point(info.getLat(), info.getLng()), info.getSlsId());
		if (dto == null) {
			// todo没有找到接单人
		} else {
			orderInfoService.updateOrderState(info.getOrderId(), OrderState.ORDER_STATE_TAKING.getCode(),
					info.getState(),dto.getAccountId());
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
		OrderRuslt<Void> orderResult = new OrderRuslt<Void>(info.getOrderId());
		List<IReciverDto> reciverDtos = search.list(new Point(info.getLat(), info.getLng()), info.getSlsId());
		if (CollectionUtils.isEmpty(reciverDtos)) {
			// todo 没有找到接单人
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
		return temp;

	}

	public IOrderRuslt<Void> startService(Long orderId, Long mercharId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		orderResult = new OrderRuslt<>(orderId);
		orderInfoService.updateOrderStateSs(orderId, OrderState.ORDER_STATE_START_SERVICE.getCode(), order.getState());
		// 发送消息
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
		if (order != null && OrderState.ORDER_STATE_FAIL.getCode().equals(order.getState())) {
			return null;
		}
		IOrderRuslt<Void> orderResult = normalBVerify(order, bId);
		if (orderResult != null) {
			return orderResult;
		}
		if (!OrderState.ORDER_STATE_PUBLISH.getCode().equals(order.getState())
				|| !OrderState.ORDER_STATE_NOTIFY.getCode().equals(order.getState())) {
			return null;//TODO 如果不是可超时的订单,则认为不需要超时
		}
		return null;
	}

	public IOrderRuslt<OrderStateCostDto> cannel(Long orderId, Long bId){
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt orderResult = normalBVerify(order, bId);
		if (orderResult != null) {
			//orderResult.setData(null);
			return orderResult;
		}
		if(OrderState.ORDER_STATE_FINISH.isGreatEqual(order.getState())){//如果已经完成服务
			//TODO 已经完成服务不能取消
			return null;
		}
		OrderState state=OrderState.ORDER_STATE_CANNEL;
		OrderStateCostDto stateDto=new OrderStateCostDto();
		if(BusinessCharge.isCharge(order.getBusinessCharge())){
			stateDto.setCost(order.getCommission());
			state=OrderState.ORDER_STATE_PENDING_CHARGE;
		}
	    orderInfoService.updateOrderCannelState(orderId, state.getCode(), order.getState(),stateDto.getCost());
	    if(OrderState.ORDER_STATE_CANNEL.equals(state)){
	    	//TODO 通知商户订单被取消
	    }
	    OrderRuslt<OrderStateCostDto> or=new OrderRuslt<>(orderId);
	    or.setData(stateDto);
	    return or;
		//notifyCannel(info);
	}

	public void noReceiver(IOrderInfo info) {
		notifyNoReceiver(info);
	}
	
	
	
	
	protected IReciverDto getBReciver(OrderDto dto) {
		return null;
	}

	protected IReciverDto getCReciver(OrderDto dto) {
		return null;
	}

	protected void sendMessage(Long orderId, String state, int cost, IReciverDto dto) {

	}

	protected void sendMessage(Long orderId, String state, int cost, List<IReciverDto> dto) {

	}

	protected IOrderRuslt<Void> normalCVerify(OrderDto order, Long mercharId) {
		IOrderRuslt<Void> temp = orderExist(order);//
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

	protected IOrderRuslt<Void> normalBVerify(OrderDto order, Long cID) {
		IOrderRuslt<Void> temp = orderExist(order);
		if (temp != null) {
			return temp;
		}
		temp = verifyCousemerId(order.getConsumerAccountId(), cID);
		if (temp != null) {
			return temp;
		}
		temp = isFinish(order.getState(), order.getOrderId());
		return temp;
	}

	/**
	 * 如果订单的商户ID和和当前账户ID不一致
	 * 
	 * @param orderMercharId
	 * @param mercharId
	 * @return
	 */
	protected IOrderRuslt<Void> verifyMercharId(Long orderMercharId, Long mercharId) {
		if (orderMercharId != null && !orderMercharId.equals(mercharId)) {
			// TODO 订单商户ID和当前商户ID不一致
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
	protected IOrderRuslt<Void> verifyCousemerId(Long orderCId, Long cId) {
		if (orderCId == null || !orderCId.equals(cId)) {
			// TODO 订单消费者ID和当前消费者ID不一致
		}
		return null;
	}

	/**
	 * 订单是否存在
	 * 
	 * @param order
	 * @return
	 */
	protected IOrderRuslt<Void> orderExist(OrderDto order) {
		if (order == null) {
			// TOOD 订单不存在
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
	protected IOrderRuslt<Void> isFinish(String state, Long orderId) {
		if (OrderState.ORDER_STATE_FINISH.isGreatEqual(state)) {
			// 订单已结束
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
	public IOrderRuslt<Long> createDeal(Long orderId, Long bId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOrderRuslt<Void> finishDeal(Long orderId, Long dealId, Integer cost, Long bId) {
		// TODO Auto-generated method stub
		return null;
	}
}
