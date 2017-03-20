package cn.com.didi.app.item.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.item.domain.FlsItemWrapper;
import cn.com.didi.app.item.domain.SlsItemWrapper;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.user.item.domain.FlServiceItemDto;
import cn.com.didi.user.item.domain.FlsItemDto;
import cn.com.didi.user.item.service.IItemService;

@RestController
public class AppItemController {
	@Resource
	protected IItemService item;

	@RequestMapping(value = "/app/c/order/listFls", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult listSlf() {
		List<FlServiceItemDto> items = item.selectItems();
		return ResultFactory.success(items);
	}

	@RequestMapping(value = "/app/c/order/listSls", method = RequestMethod.POST)
	public IResult listSls(@RequestBody Map items) {
		Integer flsId = (Integer) items.get(DomainConstatns.FLS_ID);
		AssertUtil.assertNotNullAppend(flsId, "一级服务ID不能为空");

		return ResultFactory.success(SlsItemWrapper.wrap(item.selectSlItems(flsId)));
	}
	
	@RequestMapping(value = "/app/c/order/allService", method = RequestMethod.POST)
	public IResult allService() {
		List<FlsItemDto> fls= item.selectAllFlsItem();

		return ResultFactory.success(FlsItemWrapper.wrap(fls));
	}

}
