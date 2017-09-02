package cn.com.didi.order.orders.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.didi.core.property.Couple;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.orders.domain.OrderBListDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderDtoExample;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.domain.OrderPListDto;
import cn.com.didi.order.orders.domain.OrderPromptDto;
import cn.com.didi.user.users.domain.MerchantDto;

public interface OrderDtoMapper {
	int countByExample(OrderDtoExample example);

	int deleteByExample(OrderDtoExample example);

	int deleteByPrimaryKey(Long orderId);

	int insert(OrderDto record);

	int insertSelective(OrderDto record);

	List<OrderDto> selectByExample(OrderDtoExample example);

	OrderDto selectByPrimaryKey(Long orderId);

	int updateByExampleSelective(@Param("record") OrderDto record, @Param("example") OrderDtoExample example);

	int updateByExample(@Param("record") OrderDto record, @Param("example") OrderDtoExample example);

	int updateByPrimaryKeySelective(OrderDto record);

	int updateByPrimaryKey(OrderDto record);

	OrderPromptDto selectPrompt(Long accountId);

	OrderDto selectByPrimaryKeyAndCId(@Param("orderId") Long orderId, @Param("accountId") Long cid);

	OrderDto selectByPrimaryKeyAndBId(@Param("orderId") Long orderId, @Param("accountId") Long bid);
	
	int updateByPrimaryKeySelectiveAndState( OrderDto record);

	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	List<OrderPListDto> selectOrders(@Param("time") TimeInterval interval, RowBounds bounds);
	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	int countOrders(@Param("time") TimeInterval interval);
	
	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	List<OrderPListDto> selectOrdersByUserPhone(@Param("time") TimeInterval interval, RowBounds bounds);
	
	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	int countOrdersByUserPhone(@Param("time") TimeInterval interval);
	
	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	List<OrderListDto> selectCOrderList(@Param("time") TimeInterval interval, RowBounds bounds);

	/**
	 * @param interval
	 * @param bounds
	 * @return
	 */
	List<OrderBListDto> selectBOrderList(@Param("time") TimeInterval interval, RowBounds bounds);

	OrderEvaluationDto selectEvaluation(@Param("accountId") Long bid);
	List<OrderEvaluationDto> selectEvaluationList(List<Long> bid);

	int updateOrderState(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState);

	int updateOrderStateSs(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("date") Date ort);

	int updateOrderStateFs(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("date") Date ort);

	int updateOrderStateAndBId(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("merchant") MerchantDto cid, @Param("ort") Date ort);

	int updateOrderStateAndCost(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("cost") Integer cost );

	int updateOrderStateAndEvaluation(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState,@Param("eval") Integer eval,@Param("textEval") String textEval, @Param("date") Date ort);
	
	
	int updateOrderEndState(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState,@Param("fail") String fail, @Param("date") Date date);
	
	int updateOrderStateAndCostCancelFalg(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("cost") Integer cost,@Param("cannelFlag") String cannelFlag );
	
	
	int updateOrderCancelEndState(@Param("orderId") Long orderId, @Param("destState") String destState,
			@Param("sourceState") String sourceState, @Param("date") Date date,@Param("cannelFlag") String cannelFlag);
	
	public int updateOrderStateCharge(@Param("orderId") Long orderId,  @Param("destState") String destState, 
			@Param("sourceState")  String sourceState,@Param("cost") Integer cost,@Param("cment") String cment,@Param("date") Date date) ;
	int updateOrderDealId(@Param("orderId") Long orderId,@Param("dealId") Long dealId );
	OrderDto selectOrderSubjectInformation(Long orderId);
	
	Long existOrder(OrderDtoExample example);
	 Couple<Integer, Date> selectLastOfst(OrderDtoExample example);
	
}