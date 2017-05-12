package cn.com.didi.app.order.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.order.domain.OrderChargeDto;
import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.query.TimeInterval;
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
		IOrderRuslt<OrderDto> or = orderService.startService(orderId, accountId);
		if (or.success()) {
			return ResultFactory.success(buildBDetail(or.getData()));
		}
		return ResultFactory.error(or.getCode(), or.getMessage());

	}
	@RequestMapping(value = "/app/b/order/finish",method={RequestMethod.POST})
	public IResult finishService(@RequestBody OrderIDJAO map,HttpServletRequest request){
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderDto> or = orderService.finishService(orderId, accountId);
		if (or==null||or.success()) {
			return ResultFactory.success(buildBDetail(or.getData()));
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	} 
	@RequestMapping(value = "/app/b/order/charge",method={RequestMethod.POST})
	public IResult charge(@RequestBody OrderChargeDto map,HttpServletRequest request){
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		AssertUtil.assertNotNull(map.getCost(), "收费");
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderDto> or = orderService.charge(orderId, accountId,map.getCost(),map.getCment());
		if (or==null||or.success()) {
			return ResultFactory.success(buildBDetail(or.getData()));
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	} 
	
	@RequestMapping(value="/app/b/order/finishAndCharge",method={RequestMethod.POST})
	public IResult finishAndCharge(@RequestBody OrderChargeDto map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		AssertUtil.assertNotNull(map.getCost(), "收费");
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderDto> or = orderService.finishServiceAndcharge(orderId, accountId,map.getCost(),map.getCment());
		if (or == null || or.success()) {
			return ResultFactory.success(buildBDetail(or.getData()));
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
	@RequestMapping(value="/app/b/order/orderTaking",method={RequestMethod.POST})
	public IResult orderTaking(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderDto> or = orderService.accept(orderId, accountId);
		if (or == null || or.success()) {
			return ResultFactory.success(buildBDetail(or.getData()));
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
	
	@RequestMapping(value="/app/b/order/listNotifyOrders",method={RequestMethod.POST})
	public IResult listNotifyOrders( HttpServletRequest request){
		Long accountId=resolver.resolve(request);
		return ResultFactory.success(orderInfo.listNotifyOrders(accountId,null));
	}
	
}
