package cn.com.didi.platform.order.controller;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.service.IOrderInfoService;

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
}
