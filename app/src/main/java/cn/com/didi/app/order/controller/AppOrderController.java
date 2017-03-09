package cn.com.didi.app.order.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.order.domain.OrderJAO;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderStateCostDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.service.IItemService;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.webBase.util.IAccountResolver;

@RestController
public class AppOrderController extends AppBaseOrderController {
	@Resource
	protected IAccountResolver resolver;
	@Resource
	protected IOrderInfoService orderInfo;
	@Resource
	protected IUserService userService;
	@Resource
	protected IItemService itemService;
	@Resource
	protected IMerchantService merchantService;

	@RequestMapping(value = "/app/c/order/prompt", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult prompt(HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		return ResultFactory.success(orderInfo.prompt(accountId));
	}

	@RequestMapping(value = "/app/c/order/detail", method = { RequestMethod.POST })
	public IResult detail(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		Couple<OrderDto, OrderEvaluationDto> cou = orderInfo.selectCOrderDetail(orderId, accountId);
		if (cou == null || cou.getFirst() == null) {
			return ResultFactory.success();
		}
		UserDto userDto = userService.selectUser(cou.getFirst().getMerchantAccountId());
		UserLinkIdDto linkId = userService.selectUserLinkedId(cou.getFirst().getMerchantAccountId());
		return ResultFactory.success(build(cou.getFirst(), cou.getSecond(), userDto, linkId));
	}

	@RequestMapping(value = "/app/c/order/state", method = { RequestMethod.POST })
	public IResult state(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto orderDto = orderInfo.selectCOrder(orderId, accountId);
		if (orderDto == null) {
			return ResultFactory.success();
		}
		List<OrderStateRecordDto> list = orderInfo.selectStateRecord(orderId);
		return ResultFactory.success(build(orderDto, list));
	}

	@RequestMapping(value = "/api/app/c/order/evaInfo", method = { RequestMethod.POST })
	public IResult evaInfo(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		OrderDto orderDto = orderInfo.selectCOrder(orderId, accountId);
		if (orderDto == null) {
			return ResultFactory.success();
		}
		return ResultFactory.success(build(orderDto));
	}

	@RequestMapping(value = "/app/c/order/list", method = { RequestMethod.POST })
	public IResult list(@RequestBody TimeInterval time, HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		time.setAccountId(accountId);
		return ResultFactory.success(orderInfo.selectCOrderList(time));

	}

	@RequestMapping(value = "/app/c/order/search", method = RequestMethod.POST)
	public IResult orderSearch(@RequestBody OrderJAO body) {
		String str = body.getLat();
		AssertUtil.assertNotNullAppend(str, "维度");
		str = body.getLng();
		AssertUtil.assertNotNullAppend(str, "经度");
		Integer inter = body.getSlsId();
		AssertUtil.assertNotNullAppend(inter, "二级服务");
		Point center = new Point(body.getLng(), body.getLat());
		List<MerchantAreaDto> list = merchantService.select(center, 5, inter);
		return ResultFactory.success(toMerchantAreaDto(list));
	}

	protected List<Point> toMerchantAreaDto(List<MerchantAreaDto> lists) {
		if (CollectionUtils.isEmpty(lists)) {
			return null;
		}
		List<Point> points = new ArrayList<>(lists.size());
		for (MerchantAreaDto one : lists) {
			points.add(new Point(one.getLng(), one.getLat()));
		}
		return points;
	}

	/// api/app/c/order/publish
	@RequestMapping(value = "/app/c/order/publish", method = { RequestMethod.POST })
	public IResult publish(@RequestBody OrderJAO body, HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		OrderDto order = toOrderDto(body);
		SlServiceDto sls = itemService.selectSlService(body.getSlsId());
		AssertUtil.assertNotNull(sls, "二级服务不存在");
		order.setBusinessCategory(sls.getBusinessCategory());
		order.setBusinessCharge(sls.getBusinessCharge());
		if (StringUtils.isEmpty(order.getCname())) {
			order.setCname(sls.getCname());
		}
		order.setConsumerAccountId(accountId);
		IOrderRuslt<Void> result = orderService.publish(order);
		if (!result.success()) {
			return ResultFactory.error(result.getCode(), result.getMessage());
		}
		Map p = new HashMap();
		p.put(DomainConstatns.ORDER_ID, result.getOrderId());
		return ResultFactory.success(p);
	}

	protected OrderDto toOrderDto(OrderJAO body) {
		OrderDto order = new OrderDto();
		Integer inter = body.getFlsId();
		AssertUtil.assertNotNullAppend(inter, "一级服务");
		order.setFlsId(inter);
		inter = body.getSlsId();
		AssertUtil.assertNotNullAppend(inter, "二级服务");
		order.setSlsId(inter);
		String str = body.getCci();
		AssertUtil.assertNotNullAppend(str, "客户联系方式");
		order.setCci(str);
		str = body.getCas();
		AssertUtil.assertNotNullAppend(str, "客户地区代码");
		order.setCas(body.getCas());
		str = body.getConsumerAddress();
		AssertUtil.assertNotNullAppend(str, "客户地址");
		order.setConsumerAddress(str);

		str = body.getConsumerName();
		AssertUtil.assertNotNullAppend(str, "客户名");
		order.setConsumerName(str);

		str = body.getLat();
		AssertUtil.assertNotNullAppend(str, "维度");
		order.setLat(new BigDecimal(str));

		str = body.getLng();
		AssertUtil.assertNotNullAppend(str, "经度");
		order.setLng(new BigDecimal(str));
		order.setCname(body.getCname());
		return order;
	}

	@RequestMapping(value = "/app/c/order/cancel", method = { RequestMethod.POST })
	public IResult cancel(@RequestBody Map map, HttpServletRequest request) {
		Long orderId = (Long) map.get(DomainConstatns.ORDER_ID);
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderStateCostDto> or = orderService.cannel(orderId, accountId);
		if (or.success()) {
			return ResultFactory.success(or.getData());
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
}
