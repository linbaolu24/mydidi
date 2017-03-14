package cn.com.didi.platform.order.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.platform.order.domain.OrderDetailWrapper;
import cn.com.didi.platform.order.domain.OrderIDJAO;

@RestController
public class POrderController {
	@Resource
	protected IOrderInfoService orderInfoService;
	@RequestMapping(value = "/platform/order/list",method={RequestMethod.POST})
	public IResult orderList(@RequestBody TimeInterval timeInterval){
		if(!StringUtils.isEmpty(timeInterval.getKey())){
			timeInterval.setId(Integer.parseInt(timeInterval.getKey()));
		}
		IPage<OrderListDto>  page=orderInfoService.selectOrders(timeInterval);
		return ResultExt.build(page);
		
	}
	@RequestMapping(value = "/platform/order/detail",method={RequestMethod.POST})
	public IResult detail(@RequestBody OrderIDJAO orderIdDto){
		Couple<OrderDto, OrderEvaluationDto>  cou=orderInfoService.selectOrderDetail(orderIdDto.getOrderId());
		if(cou==null||cou.getFirst()==null){
			return ResultFactory.success();
		}
		return ResultFactory.success(new OrderDetailWrapper(cou.getFirst(), cou.getSecond()));
	}
}
