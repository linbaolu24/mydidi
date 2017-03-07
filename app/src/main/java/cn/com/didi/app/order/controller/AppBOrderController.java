package cn.com.didi.app.order.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.orders.domain.OrderDto;

@RestController
public class AppBOrderController extends AppBaseOrderController{
	@RequestMapping(value="/app/c/order/list",method={RequestMethod.POST})
	public IResult orderList(@RequestBody TimeInterval time, HttpServletRequest request){
    	Long accountId = resolver.resolve(request);
    	time.setAccountId(accountId);
    	return ResultFactory.success(orderInfo.selectBOrderList(time));	
    }
	@RequestMapping(value="/app/c/order/detail",method={RequestMethod.POST})
	public IResult orderDetail(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto cou = orderInfo.selectBOrderDetail(orderId, accountId);
		if (cou == null ) {
			return ResultFactory.success();
		}
		return ResultFactory.success(buildBDetail(cou));
	}
}
