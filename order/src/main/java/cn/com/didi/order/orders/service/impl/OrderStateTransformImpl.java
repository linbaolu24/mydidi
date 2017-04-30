package cn.com.didi.order.orders.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.didi.domain.util.BusinessCharge;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.service.IOrderStateTransform;
import cn.com.didi.thirdExt.produce.IAppEnv;

@Service
public class OrderStateTransformImpl implements IOrderStateTransform {
	protected List<OrderStateRecordDto> flow;
	protected OrderStateRecordDto cancel;
	@Resource
	protected IAppEnv appEnv;

	@PostConstruct
	public void init() {
		String json = appEnv.getOrderTransJson();
		flow = JSON.parseArray(json, OrderStateRecordDto.class);
		cancel=new OrderStateRecordDto();
		cancel.setCstate(OrderState.ORDER_STATE_CANNEL.getCode());
	}

	@Override
	public boolean canTransform(String source, String dest, String businessCharge, String businessCategory) {
		return false;
	}

	@Override
	public String transform(String source, String businessCharge, String businessCategory) {
		return null;
	}

	@Override
	public List<OrderStateRecordDto> resolve(OrderDto order, List<OrderStateRecordDto> record) {
		if ("1".equals(order.getCancelFlag()) && BusinessCharge.CHARGE.getCode().equals(order.getBusinessCharge())
				&& (getStateRecord(OrderState.ORDER_STATE_START_SERVICE.getCode(), record) != null)) {
			List<OrderStateRecordDto> states = getStateRecord(OrderState.ORDER_STATE_TAKING.getCode(),
					OrderState.ORDER_STATE_START_SERVICE.getCode());
			states.add(OrderStateRecordDto.newInstance(cancel));
			compound(states, record);
			return states;
		}
		if (OrderState.ORDER_STATE_CANNEL.getCode().equals(order.getState())) {
			return record;
		}
		if (CollectionUtils.isEmpty(flow)) {
			return record;
		}
		if (CollectionUtils.isEmpty(record)) {
			return copyFlow();
		}
		OrderStateRecordDto last = record.get(record.size() - 1);
		int i = 0;
		for (OrderStateRecordDto one : flow) {

			if (one.getCstate().equals(last.getCstate())) {
				break;
			}
			i++;
		}
		if (i + 1 >= flow.size()) {
			return record;
		}
		List<OrderStateRecordDto> newRecord = new ArrayList<>(record);
		for (int j = i + 1; j < flow.size(); j++) {
			newRecord.add(OrderStateRecordDto.newInstance(flow.get(j)));
		}
		return newRecord;
	}

	protected List<OrderStateRecordDto> copyFlow() {
		List copyed = new ArrayList(flow.size());
		for (OrderStateRecordDto one : flow) {
			copyed.add(OrderStateRecordDto.newInstance(one));
		}
		return copyed;
	}

	protected List<OrderStateRecordDto> getStateRecord(String... states) {
		List copyed = new ArrayList(states.length+1);
		for (String oneState : states) {
			for (OrderStateRecordDto one : flow) {
				if (one.getCstate().equals(oneState))
					copyed.add(OrderStateRecordDto.newInstance(one));
			}
		}
		return copyed;
	}

	protected OrderStateRecordDto getStateRecord(String states, List<OrderStateRecordDto> arrays) {

		for (OrderStateRecordDto one : arrays) {
			if (one.getCstate().equals(states)) {
				return one;
			}
		}

		return null;
	}

	protected void compound(List<OrderStateRecordDto> states, List<OrderStateRecordDto> record) {
		String state = null;
		for (OrderStateRecordDto dto : states) {
			state = dto.getCstate();
			if (OrderState.ORDER_STATE_CANNEL.getCode().equals(state)) {
				state = OrderState.ORDER_STATE_PENDING_CHARGE.getCode();
			}
			for (OrderStateRecordDto one : record) {
				if (one.getCstate().equals(state)) {
					dto.setUpdateTime(one.getUpdateTime());
					dto.setOrderId(one.getOrderId());
					break;
				}

			}
		}
	}

}
