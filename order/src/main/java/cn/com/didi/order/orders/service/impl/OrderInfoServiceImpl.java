package cn.com.didi.order.orders.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.orders.dao.mapper.OrderDtoMapper;
import cn.com.didi.order.orders.dao.mapper.OrderStateRecordDtoMapper;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.domain.OrderPromptDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDtoExample;
import cn.com.didi.order.orders.service.IOrderInfoService;
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
		return orderStateRecordDtoMapper.selectByExample(example);
	}

	@Override
	public List<OrderListDto> selectBOrderList(TimeInterval interval) {
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
		if (order.getOct() == null) {
			order.setOct(new Date());
		}
		orderMapper.insertSelective(order);
		return order.getOrderId();
	}

	@Override
	public OrderDto selectOrderSubjectInformation(Long orderId) {
		if(orderId==null){
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
			addStateUpdate(orderId, destState, date, sourceState);
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
			count = orderMapper.updateOrderStateAndCostCancelFalg(orderId, destState, sourceState, cost,"1");
		} else {
			count = orderMapper.updateOrderCancelEndState(orderId, destState, sourceState, date,"1");
		}
		if (count != 0) {
			addStateUpdate(orderId, destState, date, sourceState);
		}
		return 0;
	}

	/**
	 * @param orderId
	 * @param dealId
	 * @return
	 */
	public int updateOrderDealId(Long orderId,Long dealId){
		if(orderId==null){
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

}
