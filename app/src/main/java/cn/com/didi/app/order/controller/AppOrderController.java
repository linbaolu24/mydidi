package cn.com.didi.app.order.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.order.domain.OrderEveJAO;
import cn.com.didi.app.order.domain.OrderIDJAO;
import cn.com.didi.app.order.domain.OrderJAO;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.CodeNameConstatns;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderStateDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.service.IItemService;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.service.ICodeDicService;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IMerchantService;

@RestController
public class AppOrderController extends AppBaseOrderController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AppOrderController.class);
	@Resource
	protected IItemService itemService;
	@Resource
	protected IMerchantService merchantService;
	@Resource
	protected ICodeDicService codeDicService;

	@RequestMapping(value = "/app/c/order/prompt", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult prompt(HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		return ResultFactory.success(orderInfo.prompt(accountId));
	}

	@RequestMapping(value = "/app/c/order/detail", method = { RequestMethod.POST })
	public IResult detail(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = map.getOrderId();
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
	public IResult state(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId =  map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		/*OrderDto orderDto = orderInfo.selectCOrder(orderId, accountId);
		if (orderDto == null) {
			return ResultFactory.success();
		}
		List<OrderStateRecordDto> list = orderInfo.selectStateRecord(orderId);*/
		Couple<OrderDto,List<OrderStateRecordDto>> cou=orderInfo.selectCOrderStateRecordAndResolver(orderId, accountId);
		if(cou==null){
			LOGGER.error("订单号 {} 消费者ID {}不存在对应订单信息,返回成功",orderId,accountId);
			return ResultFactory.success();
		}
		OrderDto orderDto =cou.getFirst();
		List<OrderStateRecordDto> list=cou.getSecond();
		List<CodeDictionaryDto>  dto=codeDicService.selectCodes(CodeNameConstatns.ORDER_STATE_TEXT_CODE_NAME);
		return ResultFactory.success(build(orderDto, list,dto));
	}

	@RequestMapping(value = "/app/c/order/evaInfo", method = { RequestMethod.POST })
	public IResult evaInfo(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = map.getOrderId();
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
		Date date=new Date();
		order.setOct(date);
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
		p.put(DomainConstatns.OCT, date);
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
		//AssertUtil.assertNotNullAppend(str, "客户地区代码");
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
	public IResult cancel(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long)  map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderStateDto> or = orderService.cannel(orderId, accountId);
		if (or==null||or.success()) {
			return ResultFactory.success(or.getData());
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
	
	@RequestMapping(value = "/app/c/order/timeout", method = { RequestMethod.POST })
	public IResult timeout(@RequestBody OrderIDJAO map, HttpServletRequest request) {
		Long orderId = (Long)  map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<Void> or = orderService.timeout(orderId, accountId);
		if (or==null||or.success()) {
			return ResultFactory.success();
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
	
	@RequestMapping(value = "app/c/order/evaluate", method = { RequestMethod.POST })
	public IResult evaluate(@RequestBody OrderEveJAO map, HttpServletRequest request) {
		Long orderId = (Long)  map.getOrderId();
		assertOrderId(orderId);
		AssertUtil.assertNotNull(map.getEvaluation(),"星级评价");
		Long accountId = resolver.resolve(request);
		IOrderRuslt<Void> or = orderService.evaluation(orderId, accountId,map.getEvaluation(),map.getTextEvaluation());
		if (or==null||or.success()) {
			return ResultFactory.success();
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	}
	/*@RequestMapping(value = "/app/c/order/alipay",method={RequestMethod.POST})
	public IResult alipay(@RequestBody OrderIDJAO map,HttpServletRequest request){
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<OrderDealDescDto> or = orderService.createDeal(orderId, accountId, PayAccountEnum.ALIPAY,map.getDescription());
		if (or.success()) {
			Map p=new HashMap(1);
			p.put(DomainConstatns.DEALID, or.getData().getDealId());
			return ResultFactory.success(p);
		}
		
		return ResultFactory.error(or.getCode(), or.getMessage());
	} */
	/*@RequestMapping(value = "/app/c/order/finishAlipay",method={RequestMethod.POST})
	public IResult finishAlipay(@RequestBody OrderIDJAO map,HttpServletRequest request){
		//TODO 结果不正确
		Long orderId = (Long) map.getOrderId();
		assertOrderId(orderId);
		Long accountId = resolver.resolve(request);
		IOrderRuslt<Long> or = orderService.createDeal(orderId, accountId, PayAccountEnum.ALIPAY);
		if (or.success()) {
			Map p=new HashMap(1);
			p.put(DomainConstatns.DEALID, or.getData());
			return ResultFactory.success(p);
		}
		return ResultFactory.error(or.getCode(), or.getMessage());
	} */
}
