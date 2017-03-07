package cn.com.didi.app.order.controller;

import static cn.com.didi.domain.util.DomainConstatns.BUSINESS_CATEGORY;
import static cn.com.didi.domain.util.DomainConstatns.BUSINESS_CHARGE;
import static cn.com.didi.domain.util.DomainConstatns.COST;
import static cn.com.didi.domain.util.DomainConstatns.CSTATE;
import static cn.com.didi.domain.util.DomainConstatns.DESCRIPTION;
import static cn.com.didi.domain.util.DomainConstatns.MASTER_NAME;
import static cn.com.didi.domain.util.DomainConstatns.MCI;
import static cn.com.didi.domain.util.DomainConstatns.OFST;
import static cn.com.didi.domain.util.DomainConstatns.ORDER_ID;
import static cn.com.didi.domain.util.DomainConstatns.ORT;
import static cn.com.didi.domain.util.DomainConstatns.STATE;
import static cn.com.didi.domain.util.DomainConstatns.UPDATE_TIME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.webBase.util.IAccountResolver;
@RestController
public class AppOrderController extends AppBaseOrderController{
	@Resource
	protected IAccountResolver resolver;
	@Resource
	protected IOrderInfoService orderInfo;
	@Resource
	protected IUserService userService;

	@RequestMapping(value = "/app/b/order/prompt", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult prompt(HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		return ResultFactory.success(orderInfo.prompt(accountId));
	}

	@RequestMapping(value = "/app/b/order/detail", method = { RequestMethod.POST })
	public IResult detail(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		Couple<OrderDto, OrderEvaluationDto> cou = orderInfo.selectCOrderDetail(orderId, accountId);
		if (cou == null || cou.getFirst() == null) {
			return ResultFactory.success();
		}
		UserDto userDto = userService.selectUser(cou.getFirst().getMerchantAccountId());
		UserLinkIdDto linkId= userService.selectUserLinkedId(cou.getFirst().getMerchantAccountId());
		return ResultFactory.success(build(cou.getFirst(), cou.getSecond(), userDto, linkId));
	}

	
	@RequestMapping(value = "/app/b/order/state",method = { RequestMethod.POST })
	public IResult state(@RequestBody Map map, HttpServletRequest request){
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto orderDto=orderInfo.selectCOrder(orderId, accountId);
		if(orderDto==null){
			return ResultFactory.success();
		}
		List<OrderStateRecordDto> list=orderInfo.selectStateRecord(orderId);
		return ResultFactory.success(build(orderDto, list));
	}
	
	
	
	@RequestMapping(value = "/api/app/b/order/evaInfo",method = { RequestMethod.POST })
	public IResult evaInfo(@RequestBody Map map, HttpServletRequest request){
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto orderDto=orderInfo.selectCOrder(orderId, accountId);
		if(orderDto==null){
			return ResultFactory.success();
		}
		return ResultFactory.success(build(orderDto));
	}
   
    
    @RequestMapping(value = "/app/b/order/list",method = { RequestMethod.POST })
    public IResult list(@RequestBody TimeInterval time, HttpServletRequest request){
    	Long accountId = resolver.resolve(request);
    	time.setAccountId(accountId);
    	return ResultFactory.success(orderInfo.selectCOrderList(time));
    	
    }
    ///api/app/b/order/publish
}

