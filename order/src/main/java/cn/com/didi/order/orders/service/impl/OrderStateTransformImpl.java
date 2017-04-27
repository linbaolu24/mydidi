package cn.com.didi.order.orders.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.didi.domain.util.OrderState;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.service.IOrderStateTransform;
import cn.com.didi.thirdExt.produce.IAppEnv;
@Service
public class OrderStateTransformImpl implements IOrderStateTransform {
	protected List<OrderStateRecordDto> flow;
	@Resource
	protected IAppEnv appEnv;
	@PostConstruct
	public void init(){
		String json=appEnv.getOrderTransJson();
		flow=JSON.parseArray(json, OrderStateRecordDto.class);
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
		if (OrderState.ORDER_STATE_CANNEL.getCode().equals(order.getState())) {
			return record;
		}
		if (CollectionUtils.isEmpty(flow)) {
			return record;
		}
		if(CollectionUtils.isEmpty(record)){
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
		if(i+1>=flow.size()){
			return record;
		}
		List<OrderStateRecordDto> newRecord=new ArrayList<>(record);
		for(int j=i+1;j<flow.size();j++){
			newRecord.add(OrderStateRecordDto.newInstance(flow.get(j)));
		}
		return newRecord;
	}
	protected List<OrderStateRecordDto> copyFlow(){
		List copyed=new ArrayList(flow.size());
		for(OrderStateRecordDto one:flow){
			copyed.add(OrderStateRecordDto.newInstance(one));
		}
		return copyed;
	}

}
