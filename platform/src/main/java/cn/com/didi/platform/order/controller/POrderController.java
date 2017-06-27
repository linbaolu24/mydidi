package cn.com.didi.platform.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.select.IPageBound;
import cn.com.didi.core.select.impl.SimplePageBound;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.NameConstans;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.domain.OrderListDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.platform.order.domain.OrderDetailWrapper;
import cn.com.didi.platform.order.domain.OrderListWrapperDto;
import cn.com.didi.platform.order.domain.OrderMerchantWrapperJAO;
import cn.com.didi.platform.order.domain.OrderStringIDJAO;
import cn.com.didi.thirdExt.select.ListPage;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;

@RestController
public class POrderController {
	@Resource
	protected IOrderInfoService orderInfoService;
	@Resource
	protected IOrderService orderService;
	@Resource
	protected IUserService userService;
	
	@Resource
	protected IMerchantService merchantService;
	@RequestMapping(value = "/platform/order/list",method={RequestMethod.POST})
	public IResult orderList(@RequestBody TimeInterval timeInterval){
		if(!StringUtils.isEmpty(timeInterval.getKey())){
			timeInterval.setId(Long.parseLong(timeInterval.getKey()));
		}
		IPage<OrderListDto>  page=orderInfoService.selectOrders(timeInterval);
		ListPage<OrderListWrapperDto> listPage=null;
		if(page!=null){
			List<OrderListWrapperDto> lists=OrderListWrapperDto.wrapOrderListWrapperDto(page.getList());
			listPage=new ListPage<>(lists, page.getCount());
		}
		return ResultExt.build(listPage);
		
	}
	@RequestMapping(value = "/platform/order/detail",method={RequestMethod.POST})
	public IResult detail(@RequestBody OrderStringIDJAO orderIdDto){
		Couple<OrderDto, OrderEvaluationDto>  cou=orderInfoService.selectOrderDetail(orderIdDto.getOrderIdLong());
		if(cou==null||cou.getFirst()==null){
			return ResultFactory.success();
		}
		String mpp=null;
		String merchantName=null;
		if(cou.getFirst().getMerchantAccountId()!=null){
			UserDto dto=userService.selectUser(cou.getFirst().getMerchantAccountId());
			MerchantDto mdto=merchantService.selectMerchant(cou.getFirst().getMerchantAccountId());
			mpp=dto.getProfilePhoto();
			merchantName=mdto.getMastername();
		}
		return ResultFactory.success(new OrderDetailWrapper(cou.getFirst(), cou.getSecond(),mpp,merchantName));
	}

	@RequestMapping(value = "/platform/order/initChangeOrder", method = { RequestMethod.POST })
	public IResult initChangeOrder(@RequestBody OrderStringIDJAO orderIdDto) {
		IOrderRuslt<OrderDto> result = orderService.getOrderWithCheckChangeDispatch(orderIdDto.getOrderIdLong());
		if (!result.success()) {
			return ResultFactory.error(result);
		}
		OrderDto order = result.getData();
		Map<String,Object> map=new HashMap<>();
		map.put(DomainConstatns.ORDER_ID, String.valueOf(order.getOrderId()));
		map.put(DomainConstatns.CNAME, order.getCname());
		map.put(DomainConstatns.MASTER_NAME, order.getMasterName());
		map.put(DomainConstatns.SERVICE_ADDRESS, order.getConsumerAddress());
		SimplePageBound bounds = new SimplePageBound();
		bounds.setPageIndex(1);
		bounds.setPageSize(10);

		IPage<MerchantDto> pages = merchantService.selectMerchants(new Point(order.getLng(), order.getLat()), -1,
				order.getSlsId(), bounds);
		if (pages != null&&!CollectionUtils.isEmpty(pages.getList())) {
			List<OrderMerchantWrapperJAO> lists=pages.getList().stream().map(OrderMerchantWrapperJAO::new).collect(Collectors.toList());
			map.put("merchantList", lists);
		}
		return ResultFactory.success(map);
	}
	
	
	@RequestMapping(value = "/platform/order/reassignment", method = { RequestMethod.POST })
	public IResult reassignment(@RequestBody OrderStringIDJAO orderIdDto) {
		AssertUtil.assertNotNull(orderIdDto.getOrderId(), "订单ID");
		AssertUtil.assertNotNull(orderIdDto.getAccountId(),"账户ID");
		IOrderRuslt resilt=orderService.reassignment(orderIdDto.getOrderIdLong(), orderIdDto.getAccountId());
		if(resilt==null||resilt.success()){
			return ResultFactory.success();
		}
		return ResultFactory.error(resilt);
	}
}
