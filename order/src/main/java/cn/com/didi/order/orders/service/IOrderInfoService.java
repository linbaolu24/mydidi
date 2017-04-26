package cn.com.didi.order.orders.service;

import java.util.List;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.orders.domain.OrderBListDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.domain.OrderPromptDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;

/**
 * 订单信息服务
 * @author xlm
 *
 */
public interface IOrderInfoService {
	/**
	 * @param interval
	 * @return
	 */
	public IPage<OrderListDto> selectOrders(TimeInterval interval);
	/**
	 * @param orderId
	 * @return
	 */
	public OrderDto selectOrder(Long orderId);
	/**
	 * @param mercharId
	 * @return
	 */
	public OrderEvaluationDto calculate(Long mercharId);
	/**
	 * 待服务订单/待支付订单 数量
	 */
	public OrderPromptDto prompt(Long cAccountId);
	/**
	 * @param orderId
	 * @param cid
	 * @return 
	 */
	public Couple<OrderDto, OrderEvaluationDto> selectCOrderDetail(Long orderId,Long cid);
	
	/**
	 * @param orderId
	 * @param cid
	 * @return 
	 */
	public Couple<OrderDto, OrderEvaluationDto> selectOrderDetail(Long orderId);
	/**
	 * @param orderId
	 * @param bid
	 */
	public OrderDto selectBOrderDetail(Long orderId,Long bid);
	/**
	 * @param orderId
	 * @param cid
	 * @return 
	 */
	public OrderDto selectCOrder(Long orderId,Long cid);
	/**
	 * @return
	 */
	public List<OrderStateRecordDto> selectStateRecord(Long orderId);
	
	/**
	 * @return
	 */
	public Couple<OrderDto,List<OrderStateRecordDto>> selectCOrderStateRecordAndResolver(Long orderId, Long cid);

	
	/**查询用户端订单列表*/
	public List<OrderBListDto> selectBOrderList(TimeInterval interval);
	/**
	 * @param interval
	 * @return
	 */
	public List<OrderListDto> selectCOrderList(TimeInterval interval);
	
	/**
	 * @param order
	 * @return
	 */
	public Long addOrder(OrderDto order);  
	
	/**
	 * 查询订单主体信息
	 */
	public OrderDto selectOrderSubjectInformation(Long orderId);
	/**<p>更新订单状态-开始服务</p>
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderStateSs(Long orderId,String destState,String sourceState);
	
	/**<p>更新订单状态-完成服务</p>
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderStateFs(Long orderId,String destState,String sourceState);
	
	/**<p>更新订单状态和接收方信息</p>
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderState(Long orderId,String destState,String sourceState,Long bId);
	
	/**<p>更新订单状态和花费</p>
	 * @param orderId
	 * @param sourceState
	 * @param cost 如果为空 不更新cost
	 * @return
	 */
	public int updateOrderState(Long orderId,String destState,String sourceState,Integer cost);
	
	/**
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderStateAndEvaluation(Long orderId,String destState,String sourceState, int eval, String textEval);
	
	/**<p>更像订单状态为失败</p>
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderFailState(Long orderId,String destState,String sourceState, String fail);
	
	/**<p>更新为取消</p>
	 * @param orderId
	 * @param sourceState
	 * @return
	 */
	public int updateOrderCannelState(Long orderId,String destState,String sourceState,Integer cost);
	
	/**
	 * @param orderId
	 * @param dealId
	 * @return
	 */
	public int updateOrderDealId(Long orderId,Long dealId);
	
	
	/**<p>更新订单状态和花费</p>
	 * @param orderId
	 * @param sourceState
	 * @param cost 如果为空 不更新cost
	 * @return
	 */
	public int updateOrderStateCharge(Long orderId,String destState,String sourceState,Integer cost,String cment);
	/**
	 * @param dto
	 * @param reciverList
	 * @return
	 */
	public int notifyOrder(OrderDto dto,List<IReciverDto> reciverList);
	/**
	 * @param dto
	 * @return
	 */
	public int orderTaking(OrderDto dto);
}
