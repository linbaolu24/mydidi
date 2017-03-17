package cn.com.didi.app.order.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.result.IOrderRuslt;

@RestController
public class AppBOrderController extends AppBaseOrderController {
	@RequestMapping(value = "/app/b/order/list", method = { RequestMethod.POST })
	public IResult orderList(@RequestBody TimeInterval time, HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		time.setAccountId(accountId);
		return ResultFactory.success(orderInfo.selectBOrderList(time));
	}

	@RequestMapping(value = "/app/b/order/detail", method = { RequestMethod.POST })
	public IResult orderDetail(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto cou = orderInfo.selectBOrderDetail(orderId, accountId);
		if (cou == null) {
			return ResultFactory.success();
		}
		return ResultFactory.success(buildBDetail(cou));
	}

	@RequestMapping(value = "/app/b/order/start", method = { RequestMethod.POST })
	public IResult startService(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<Void> or = orderService.startService(orderId, accountId);
		if (or==null||or.success()) {
			return ResultFactory.success();
		}
		return ResultFactory.error(or.getCode(), or.getMessage());

	}
	@RequestMapping(value = "/app/b/order/finish",method={RequestMethod.POST})
	public IResult finishService(@RequestBody OrderIDJAO map,HttpServletRequest request){
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<Void> or = orderService.finishService(orderId, accountId);
		if (or==null||or.success()) {
			return ResultFactory.success();
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	} 
	
	
}
