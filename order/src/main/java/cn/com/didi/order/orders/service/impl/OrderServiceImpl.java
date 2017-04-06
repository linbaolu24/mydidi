package cn.com.didi.order.orders.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.domain.domains.PayResultDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.util.BusinessCharge;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.TradeCategory;
import cn.com.didi.message.push.service.IPushMessageService;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.domain.OrderDealDescDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderDtoOrderInfo;
import cn.com.didi.order.orders.domain.OrderStateDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderLifeListener;
import cn.com.didi.order.orders.service.IOrderNotifyMessageFinder;
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
	@Resource
	protected IOrderNotifyMessageFinder orderMessageFinder;
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
    /**自动分配*/
	public IOrderRuslt<Void> autoDispatch(Long orderId, Long bId) {
		OrderDto info = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> temp = normalCousemerVerify(info, bId);
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
		IReciverDto dto = search.match(info.getCas(), new Point( info.getLng(),info.getLat()), info.getSlsId());
		if (dto == null) {
			// todo没有找到接单人
			orderResult.setCode(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getCode());
			orderResult.setMessage(OrderMessageConstans.ORDER_AUTO_DIS_NO_MASTER.getMessage());
		} else {
			int count=orderInfoService.updateOrderState(info.getOrderId(), OrderState.ORDER_STATE_TAKING.getCode(),
					info.getState(), dto.getAccountId());
			IOrderRuslt<Void> oResult=orderStateChange(count, info, info.getState());
			if(oResult!=null){
				return oResult;
			}
			MessageDto tempMDto=orderMessageFinder.findBTakingMessage(info);
			sendMessage(info, tempMDto, dto);
			tempMDto=orderMessageFinder.findCTakedMessage(info);
			sendMessage(info,tempMDto, false);
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
		IOrderRuslt<Void> temp = normalCousemerVerify(info, bId);
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
			int count=orderInfoService.updateOrderState(orderId, OrderState.ORDER_STATE_NOTIFY.getCode(), info.getState(),(Integer) null);
			IOrderRuslt<Void> oResult=orderStateChange(count, info, info.getState());
			if(oResult!=null){
				return oResult;
			}
			MessageDto tempMDto=orderMessageFinder.findBToTakeMessage(info);
			sendMessage(info,  tempMDto, reciverDtos);
		}
		return orderResult;
	}

	public IOrderRuslt<Void> accept(Long orderId, Long bId) {
		OrderDto info = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> temp = normalMercharVerify(info, bId);
		if (temp != null) {
			return temp;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_TAKING, info.getState())) {
			return null;
		}
		
		int count=orderInfoService.updateOrderState(orderId, OrderState.ORDER_STATE_TAKING.getCode(), info.getState(), info.getMerchantAccountId());
		temp=orderStateChange(count, info, info.getState());
		if(temp!=null){
			return temp;
		}
		temp = new OrderRuslt<>(orderId);
		MessageDto tempMDto=orderMessageFinder.findCTakedMessage(info);
		sendMessage(info,  tempMDto, false);
		return temp;

	}

	public IOrderRuslt<OrderDto> startService(Long orderId, Long mercharId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderDto> orderResult = normalMercharVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		if (isStateRepeat(OrderState.ORDER_STATE_START_SERVICE, order.getState())) {
			return build( order);
		}
		orderResult = new OrderRuslt<>(orderId);
		int count=orderInfoService.updateOrderStateSs(orderId, OrderState.ORDER_STATE_START_SERVICE.getCode(), order.getState());
		orderResult=orderStateChange(count, order, order.getState());
		if(orderResult!=null){
			return orderResult;
		}
		// 发送消息
		MessageDto tempMDto=orderMessageFinder.findBStartMessage(order);
		sendMessage(order, tempMDto, false);
		return build(OrderState.ORDER_STATE_START_SERVICE, order);

	}
	public IOrderRuslt<OrderDto> finishServiceAndcharge(Long orderId,Long mercharId,int cost,String cment,IOrderCallBack<OrderDto> callBack){
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderDto> orderResult = normalMercharVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		OrderState destState =callBack.getDest(order);
		if (isStateRepeat(destState, order.getState())) {
			return build(order);
		}
		OrderState[] fromState=callBack.getFromStates(order, destState);
		if(fromState!=null&&fromState.length>0){
			orderResult= orderConvert(order.getState(), callBack.getMessageStateConvert(order, destState), fromState);
			if(orderResult!=null){
				return orderResult;
			}
		}
		order.setCment(cment);
		order.setCost(cost);
		return callBack.callBack(order, destState);
	}
	
	
	public IOrderRuslt<OrderDto> finishServiceAndcharge(Long orderId,Long mercharId,int cost,String cment){
		return finishServiceAndcharge(orderId,mercharId,cost,cment,pendingAndFinishCallBack);
	}
	
	
	
	
	public IOrderRuslt<OrderDto> finishService(Long orderId, Long mercharId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderDto> orderResult = normalMercharVerify(order, mercharId);
		if (orderResult != null) {
			return orderResult;
		}
		orderResult = new OrderRuslt<>(orderId);
		OrderState destState = OrderState.ORDER_STATE_Pending_EVALUATION;
		if (BusinessCharge.isCharge(order.getBusinessCharge())) {
			destState = OrderState.ORDER_STATE_PENDING_CHARGE;
		}
		if (destState.getCode().equals(order.getState())||
				(!OrderState.ORDER_STATE_FINISH.equals(destState)&&isStateRepeat(destState, order.getState()))) {
			return build(order);//这个判断比较生硬 考虑优化
		}

		int count=orderInfoService.updateOrderStateFs(orderId, destState.getCode(), order.getState());//服务结束更新
		orderResult=orderStateChange(count, order, order.getState());
		if(orderResult!=null){
			return orderResult;
		}
		MessageDto tempMDto=orderMessageFinder.findBFinishMessage(order);
		sendMessage(order,  tempMDto, false);
		return build(destState, order);
		// 发送消息
		// notifyFinishService(info);
	}

	public IOrderRuslt<OrderDto> charge(Long orderId, Long mercharId, int cost,String cment) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderDto> orderResult = normalMercharVerify(order, mercharId);
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
			return build(order);
		}
		orderConvert(order.getState(), OrderMessageConstans.ORDER_NOT_PENDING_CHAREGE_CAN_NOT_CHARGE, OrderState.ORDER_STATE_PENDING_CHARGE);
		int count=orderInfoService.updateOrderStateCharge(orderId, destState.getCode(), order.getState(), iCost,cment);
		orderResult=orderStateChange(count, order, order.getState());
		if(orderResult!=null){
			return orderResult;
		}
		MessageDto tempMDto=orderMessageFinder.findBChargeMessage(order);
		sendMessage(order,  tempMDto, false);
		order.setCment(cment);
		order.setCost(cost);
		return build(destState,order);
		// notifyCharge(info);
	}

	public IOrderRuslt<Void> evaluation(Long orderId, Long bId, int eval, String textEval) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<Void> orderResult = normalCousemerVerify(order, bId,false);
		if (orderResult != null) {
			return orderResult;
		}
		if(OrderState.ORDER_STATE_FINISH.getCode().equals(order.getState())){
			return null;
		}
		orderConvert(order.getState(), OrderMessageConstans.ORDER_NOT_PENDING_EVE, OrderState.ORDER_STATE_PENDING_CHARGE);
		int count=orderInfoService.updateOrderStateAndEvaluation(orderId, OrderState.ORDER_STATE_FINISH.getCode(),
				order.getState(), eval, textEval);
		orderResult=orderStateChange(count, order, order.getState());
		if(orderResult!=null){
			return orderResult;
		}
		return null;
	}

	public IOrderRuslt<Void> timeout(Long orderId, Long bId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		if (order != null && OrderState.ORDER_STATE_FAIL.getCode().equals(order.getState())) {// 不需要重复超时
			return null;
		}
		IOrderRuslt<Void> orderResult = normalCousemerVerify(order, bId);
		if (orderResult != null) {
			return orderResult;
		}
		if (!OrderState.ORDER_STATE_PUBLISH.getCode().equals(order.getState())
				|| !OrderState.ORDER_STATE_NOTIFY.getCode().equals(order.getState())) {
			return new OrderRuslt<>(OrderMessageConstans.ORDER_HAVE_TIKEING.getMessage(),
					OrderMessageConstans.ORDER_HAVE_TIKEING.getCode());
		}
		int count=orderInfoService.updateOrderFailState(orderId, OrderState.ORDER_STATE_FAIL.getCode(), order.getState(),
				"timeout");
		orderResult=orderStateChange(count, order, order.getState());
		if(orderResult!=null){
			return orderResult;
		}
		return null;
	}

	public IOrderRuslt<OrderStateDto> cannel(Long orderId, Long bId) {
		OrderDto order = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderStateDto> orderResult = normalCousemerVerify(order, bId, false);
		if (orderResult != null) {
			// orderResult.setData(null);
			return orderResult;
		}

		if (OrderState.ORDER_STATE_FINISH.isGreatEqual(order.getState())) {// 如果已经完成服务
			return new OrderRuslt<>(OrderMessageConstans.ORDER_SERVICE_FINISH_CANNOT_CANNEL);
		}
		OrderState state = OrderState.ORDER_STATE_CANNEL;
		OrderStateDto stateDto = new OrderStateDto();
		if (BusinessCharge.isCharge(order.getBusinessCharge())) {
			stateDto.setCost(order.getCommission());
			state = OrderState.ORDER_STATE_PENDING_CHARGE;
		}
		stateDto.setState(state.getCode());
		
		if (!OrderState.ORDER_STATE_CANNEL.getCode().equals(order.getState())) {// 如果不是
																				// 重复取消
			int count=orderInfoService.updateOrderCannelState(orderId, state.getCode(), order.getState(), stateDto.getCost());
			/*if (OrderState.ORDER_STATE_CANNEL.equals(state)) {
				// TODO 通知商户订单被取消
				
			}*/
			orderResult=orderStateChange(count, order, order.getState());
			if(orderResult!=null){
				return orderResult;
			}
			order.setState(state.getCode());
			
			MessageDto tempMDto=orderMessageFinder.findCCancelMessage(order);
			sendMessage(order,  tempMDto, true);
		}
		OrderRuslt<OrderStateDto> or = new OrderRuslt<>(orderId);
		or.setData(stateDto);
		return or;
		// notifyCannel(info);
	}


	@Override
	public IOrderRuslt<OrderDealDescDto> createDeal(Long orderId, Long bId, PayAccountEnum payEnum,String desc) {
		OrderDto dto = orderInfoService.selectOrderSubjectInformation(orderId);
		IOrderRuslt<OrderDealDescDto> result = normalCousemerVerify(dto, bId);
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
		deal.setCment(StringUtils.defaultIfEmpty(desc, null));
		tradeService.createTrade(deal, dealTranscationalCallBack);
		result = new OrderRuslt<>("", OrderRuslt.SUCCESS_CODE, null, createOrderDealDescDto(deal, dto));
		return result;
	}
	protected OrderDealDescDto createOrderDealDescDto(DealDto deal,OrderDto order){
		OrderDealDescDto desc=new OrderDealDescDto();
		desc.setAmount(deal.getAmount());
		desc.setCname(order.getCname());
		desc.setDescription(order.getDescription());
		desc.setOrderId(order.getOrderId());
		desc.setDealId(deal.getDealId());
		desc.setDealTime(deal.getCreateTime());
		return desc;
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
			MessageDto tempMDto=orderMessageFinder.findCFinishDealMessage(orderDto);
			sendMessage(orderDto, tempMDto, true);
			return OrderRuslt.successResult();
		}
		return new OrderRuslt<>(deal.getMessage(), deal.getCode());
	}
	
	
	public void noReceiver(IOrderInfo info) {
		notifyNoReceiver(info);
	}
	protected void sendMessage(OrderDto dto, MessageDto messageDto, boolean isMerchant) {
		if(messageDto==null||StringUtils.isEmpty(messageDto.getText())||StringUtils.isEmpty(messageDto.getTitle())){
			return ;
		}
		IReciverDto reciver = isMerchant ? getBReciver(dto) : getCReciver(dto);
		sendMessage(dto, messageDto.getText(), messageDto.getTitle(), reciver);
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
	protected void sendMessage(OrderDto dto,MessageDto messageDto, IReciverDto reciver) {
		if(messageDto==null||StringUtils.isEmpty(messageDto.getText())||StringUtils.isEmpty(messageDto.getTitle())){
			return ;
		}
		sendMessage(dto,messageDto.getText(), messageDto.getTitle(), reciver);
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
	
	protected void sendMessage(OrderDto dto, MessageDto messageDto,List<IReciverDto> lists) {
		if(messageDto==null||StringUtils.isEmpty(messageDto.getText())||StringUtils.isEmpty(messageDto.getTitle())){
			return ;
		}
		sendMessage(dto, messageDto.getText(), messageDto.getTitle(), lists);
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

	protected <T> IOrderRuslt<T> normalMercharVerify(OrderDto order, Long mercharId) {
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

	protected <T> IOrderRuslt<T> normalCousemerVerify(OrderDto order, Long cID) {
		return normalCousemerVerify(order, cID, true);
	}

	protected <T> IOrderRuslt<T> normalCousemerVerify(OrderDto order, Long cID, boolean isFinish) {
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
	protected boolean isRepateFinishDeal(String state) {
		return OrderState.ORDER_STATE_FINISH.getCode().equals(state)
				|| OrderState.ORDER_STATE_Pending_EVALUATION.getCode().equals(state)
				|| OrderState.ORDER_STATE_CANNEL.getCode().equals(state);
	}
	@Resource
	protected void setOrderLifeListener(IOrderLifeListener lifeLisnter){
		setListenerList(Arrays.asList(lifeLisnter));
	}
	
	protected <T> IOrderRuslt<T> orderStateChange(int count,OrderDto dto,String sourceState){
		if(count<=0){
			return new OrderRuslt<T>(OrderMessageConstans.ORDER_UPDATE_STATE_CHANGE);
		}
		return null;
	}
	/**
	 * @param now
	 * @param message
	 * @param arrays
	 * @return
	 */
	public <T> IOrderRuslt<T> orderConvert(String now, Message message, OrderState... arrays) {
		if (arrays == null || arrays.length == 0) {
			return null;
		}
		for (OrderState one : arrays) {
			if (one.getCode().equals(now)) {
				return null;
			}
		}
		return new OrderRuslt<>(message);
	}
	
    public IOrderRuslt<OrderDto> build(OrderState state,OrderDto dto){
		return build(state.getCode(), dto);
	}
	public IOrderRuslt<OrderDto> build(String state,OrderDto dto){
		OrderRuslt<OrderDto> result=new OrderRuslt<>(dto.getOrderId());
		if(!StringUtils.isEmpty(state)){
		dto.setState(state);
		}
		result.setData(dto);
		return result;
	}
	public IOrderRuslt<OrderDto> build(OrderDto dto){
	     return build((String)null,dto);	
	}
	
	
	
	
	
	
	
	/**
	 * @author xlm
	 *
	 * @param <T>
	 */
	public static interface IOrderCallBack<T>{
		IOrderRuslt<T> callBack(OrderDto dto,OrderState dest);
		OrderState getDest(OrderDto dto);
		OrderState[] getFromStates(OrderDto dto,OrderState destState);
		Message getMessageStateConvert(OrderDto dto,OrderState destState);
	}
	public IOrderCallBack<OrderDto> pendingChargeCallBack=new IOrderCallBack<OrderDto>() {

		@Override
		public IOrderRuslt<OrderDto> callBack(OrderDto order, OrderState destState) {
			int count=orderInfoService.updateOrderStateCharge(order.getOrderId(), destState.getCode(), order.getState(), order.getCost(),order.getCment());
			IOrderRuslt<OrderDto> orderResult=orderStateChange(count, order, order.getState());
			if(orderResult!=null){
				return orderResult;
			}
			MessageDto tempMDto=orderMessageFinder.findBChargeMessage(order);
			sendMessage(order,  tempMDto, false);
			return build(destState,order);
		}

		@Override
		public OrderState getDest(OrderDto dto) {
			return OrderState.ORDER_STATE_PENDING_CHARGE;
		}

		@Override
		public OrderState[] getFromStates(OrderDto dto,OrderState destState) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Message getMessageStateConvert(OrderDto dto, OrderState destState) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public IOrderCallBack<OrderDto> finishChargeCallBack=new IOrderCallBack<OrderDto>() {

		@Override
		public IOrderRuslt<OrderDto> callBack(OrderDto dto, OrderState destState) {
			int count=orderInfoService.updateOrderStateFs(dto.getOrderId(), destState.getCode(), dto.getState());
			 IOrderRuslt<OrderDto> orderResult=orderStateChange(count, dto, dto.getState());
			if(orderResult!=null){
				return orderResult;
			}
			MessageDto tempMDto=orderMessageFinder.findBFinishMessage(dto);
			sendMessage(dto,  tempMDto, false);
			return build(destState, dto);
		}

		@Override
		public OrderState getDest(OrderDto dto) {
			return OrderState.ORDER_STATE_FINISH;
		}

		@Override
		public OrderState[] getFromStates(OrderDto dto,OrderState destState) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Message getMessageStateConvert(OrderDto dto, OrderState destState) {
			// TODO Auto-generated method stub
			return null;
		}
	};
	
	public IOrderCallBack<OrderDto> pendingAndFinishCallBack=new IOrderCallBack<OrderDto>() {

		@Override
		public IOrderRuslt<OrderDto> callBack(OrderDto dto, OrderState dest) {
			if(OrderState.ORDER_STATE_PENDING_CHARGE.equals(dest)){
				return pendingChargeCallBack.callBack(dto, dest);
			}else{
				return finishChargeCallBack.callBack(dto, dest);
			}
		}

		@Override
		public OrderState getDest(OrderDto dto) {
			if(BusinessCharge.isCharge(dto.getBusinessCharge())){
				return pendingChargeCallBack.getDest(dto);
			}else{
				return finishChargeCallBack.getDest(dto);
			}
		}

		@Override
		public OrderState[] getFromStates(OrderDto dto,OrderState destState) {
			return null;
		}

		@Override
		public Message getMessageStateConvert(OrderDto dto, OrderState destState) {
			// TODO Auto-generated method stub
			return null;
		}
	};
}
