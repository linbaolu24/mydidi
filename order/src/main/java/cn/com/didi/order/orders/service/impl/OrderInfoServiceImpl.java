package cn.com.didi.order.orders.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.order.orders.dao.mapper.OrderDtoMapper;
import cn.com.didi.order.orders.dao.mapper.OrderStateRecordDtoMapper;
import cn.com.didi.order.orders.domain.OrderBListDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.domain.OrderPromptDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDtoExample;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderStateTransform;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.service.IMerchantService;

@Service
@Primary
public class OrderInfoServiceImpl implements IOrderInfoService {
	@Resource
	protected OrderDtoMapper orderMapper;
	@Resource
	protected OrderStateRecordDtoMapper orderStateRecordDtoMapper;
	@Resource
	protected IMerchantService merchantService;
	
	@Resource
	protected IOrderStateTransform orderStateTransform;

	public IPage<OrderListDto> selectOrders(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<OrderListDto> list = (PageList<OrderListDto>) orderMapper.selectOrders(interval, pageBounds);
		return new MybatisPaginatorPage<>(list);
	}

	public OrderDto selectOrder(Long orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	public OrderEvaluationDto calculate(Long mercharId) {
		OrderEvaluationDto dto = orderMapper.selectEvaluation(mercharId);
		if (dto.getOrderCount() == 0) {
			dto.setMasterEvaluation(BigDecimal.ZERO);

		} else {
			dto.setMasterEvaluation(new BigDecimal(dto.getTotolEvaluation()).divide(new BigDecimal(dto.getOrderCount()),
					1, RoundingMode.HALF_UP));
		}
		return dto;
	}

	public OrderPromptDto prompt(Long cAccountId) {
		OrderPromptDto dto = orderMapper.selectPrompt(cAccountId);
		return dto == null ? OrderPromptDto.ZERO : dto;
	}

	public Couple<OrderDto, OrderEvaluationDto> selectCOrderDetail(Long orderId, Long cid) {
		if (orderId == null || cid == null) {
			return null;
		}
		OrderDto dto = selectCOrder(orderId, cid);
		if (dto == null) {
			return null;
		}
		OrderEvaluationDto eve = calculate(dto.getMerchantAccountId());
		return new Couple<OrderDto, OrderEvaluationDto>(dto, eve);

	}

	public OrderDto selectBOrderDetail(Long orderId, Long bid) {
		if (orderId == null || bid == null) {
			return null;
		}
		OrderDto dto = orderMapper.selectByPrimaryKeyAndBId(orderId, bid);
		return dto;
	}

	@Override
	public OrderDto selectCOrder(Long orderId, Long cid) {
		return orderMapper.selectByPrimaryKeyAndCId(orderId, cid);
	}

	@Override
	public List<OrderStateRecordDto> selectStateRecord(Long orderId) {
		OrderStateRecordDtoExample example = new OrderStateRecordDtoExample();
		OrderStateRecordDtoExample.Criteria cri = example.createCriteria();
		cri.andOrderIdEqualTo(orderId);
		example.setOrderByClause("update_time ASC");
		return orderStateRecordDtoMapper.selectByExample(example);
	}
	
	@Override
	public Couple<OrderDto,List<OrderStateRecordDto>> selectCOrderStateRecordAndResolver(Long orderId, Long cid) {
		
		OrderDto order=selectCOrder(orderId, cid);
		if(order==null){
			return null;
		}
		
		OrderStateRecordDtoExample example = new OrderStateRecordDtoExample();
		OrderStateRecordDtoExample.Criteria cri = example.createCriteria();
		cri.andOrderIdEqualTo(orderId);
		example.setOrderByClause("update_time ASC");
		List<OrderStateRecordDto> list=orderStateRecordDtoMapper.selectByExample(example);
		list =orderStateTransform.resolve(order, list);
		 return new Couple<OrderDto, List<OrderStateRecordDto>>(order, list);
	}
	
	

	@Override
	public List<OrderBListDto> selectBOrderList(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), false);
		return orderMapper.selectBOrderList(interval, pageBounds);
	}

	@Override
	public List<OrderListDto> selectCOrderList(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), false);
		return orderMapper.selectCOrderList(interval, pageBounds);
	}

	@Override
	public Long addOrder(OrderDto order) {
		if (order == null) {
			return null;
		}
		int i = 0;
		if (order.getOct() == null) {
			order.setOct(new Date());
		}
		while (i < 3) {
			generatorOrderId(order);
			int count =orderMapper.insertSelective(order);
			if(count!=0){
				return order.getOrderId();
			}
			i++;
		}
		throw new MessageObjectException(OrderMessageConstans.ORDER_ID_CONFLICT);
		
	}
	protected void generatorOrderId(OrderDto dto){
		
		dto.setOrderId( System.currentTimeMillis()*(1000000)+(dto.getConsumerAccountId()%900+100)*1000+RandomUtils.nextInt(10000));
	}
	@Override
	public OrderDto selectOrderSubjectInformation(Long orderId) {
		if (orderId == null) {
			return null;
		}
		return orderMapper.selectOrderSubjectInformation(orderId);
	}

	@Override
	@Transactional
	public int updateOrderStateSs(Long orderId, String destState, String sourceState) {
		Date date = new Date();
		int count = orderMapper.updateOrderStateSs(orderId, destState, sourceState, date);
		if (count > 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;
	}

	@Override
	@Transactional
	public int updateOrderStateFs(Long orderId, String destState, String sourceState) {
		Date date = new Date();
		int count = orderMapper.updateOrderStateFs(orderId, destState, sourceState, date);
		if (count > 0) {
			if (OrderState.ORDER_STATE_Pending_EVALUATION.getCode().equals(destState)) {
				addStateUpdate(orderId, OrderState.ORDER_STATE_PENDING_CHARGE.getCode(), date, sourceState);
				addStateUpdate(orderId, OrderState.ORDER_STATE_Pending_EVALUATION.getCode(), date,
						OrderState.ORDER_STATE_PENDING_CHARGE.getCode());
			} else {
				addStateUpdate(orderId, destState, date, sourceState);
			}
		}
		return count;
	}

	protected void addStateUpdate(Long orderId, String destState, Date date, String sourceState) {
		OrderStateRecordDto dto = new OrderStateRecordDto();
		dto.setBstate(sourceState);
		dto.setCstate(destState);
		dto.setOrderId(orderId);
		dto.setUpdateTime(date);
		orderStateRecordDtoMapper.insertSelective(dto);
	}

	@Override
	@Transactional
	public int updateOrderState(Long orderId, String destState, String sourceState, Long bId) {
		int count = 0;
		Date date = new Date();
		MerchantDto md = merchantService.selectMerchant(bId);
		count = orderMapper.updateOrderStateAndBId(orderId, destState, sourceState, md, date);
		if (count != 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;
	}

	@Override
	@Transactional
	public int updateOrderState(Long orderId, String destState, String sourceState, Integer cost) {
		int count = 0;
		if (cost == null) {
			count = orderMapper.updateOrderState(orderId, destState, sourceState);
		} else {
			count = orderMapper.updateOrderStateAndCost(orderId, destState, sourceState, cost);
		}
		if (count != 0) {
			addStateUpdate(orderId, destState, new Date(), sourceState);
		}
		return count;
	}

	@Override
	@Transactional
	public int updateOrderStateAndEvaluation(Long orderId, String destState, String sourceState, int eval,
			String textEval) {
		Date date = new Date();
		int count = orderMapper.updateOrderStateAndEvaluation(orderId, destState, sourceState, eval, textEval, date);
		if (count != 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;
	}

	@Override
	@Transactional
	public int updateOrderFailState(Long orderId, String destState, String sourceState, String fail) {
		Date date = new Date();

		int count = orderMapper.updateOrderEndState(orderId, destState, sourceState, fail, date);
		if (count != 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;
	}

	@Override
	@Transactional
	public int updateOrderCannelState(Long orderId, String destState, String sourceState, Integer cost) {
		int count = 0;
		Date date = new Date();
		if (cost != null) {
			count = orderMapper.updateOrderStateAndCostCancelFalg(orderId, destState, sourceState, cost, "1");
		} else {
			count = orderMapper.updateOrderCancelEndState(orderId, destState, sourceState, date, "1");
		}
		if (count != 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;
	}

	/**
	 * @param orderId
	 * @param dealId
	 * @return
	 */
	public int updateOrderDealId(Long orderId, Long dealId) {
		if (orderId == null) {
			return 0;
		}
		return orderMapper.updateOrderDealId(orderId, dealId);
	}

	@Override
	public Couple<OrderDto, OrderEvaluationDto> selectOrderDetail(Long orderId) {
		if (orderId == null) {
			return null;
		}
		OrderDto dto = selectOrder(orderId);
		if (dto == null) {
			return null;
		}
		OrderEvaluationDto eve = calculate(dto.getMerchantAccountId());
		return new Couple<OrderDto, OrderEvaluationDto>(dto, eve);
	}

	@Override
	public int updateOrderStateCharge(Long orderId, String destState, String sourceState, Integer cost, String cment) {
		int count = orderMapper.updateOrderStateCharge(orderId, destState, sourceState, cost, cment);
		if (count != 0 && !sourceState.equals(destState)) {
			Date date = new Date();
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return count;

	}

	@Override
	public int notifyOrder(OrderDto dto, List<IReciverDto> reciverList) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	public int orderTaking(OrderDto dto) {
		OrderDto news=new OrderDto();
		news.setMerchantAccountId(dto.getMerchantAccountId());
		news.setMci(dto.getMci());
		news.setMasterName(dto.getMasterName());
		news.setSourceState(StringUtils.defaultIfEmpty(dto.getSourceState(),dto.getState()));
		news.setState(OrderState.ORDER_STATE_TAKING.getCode());
		news.setMlat(dto.getMlat());
		news.setMlng(dto.getMlng());
		news.setOrderId(dto.getOrderId());
		int count=orderMapper.updateByPrimaryKeySelectiveAndState(news);
		if(count<=0){
			return count;
		}
		if (count != 0) {
			addStateUpdate(news.getOrderId(), OrderState.ORDER_STATE_TAKING.getCode(), new Date(), news.getSourceState());
		}
		return count;
	}

}
