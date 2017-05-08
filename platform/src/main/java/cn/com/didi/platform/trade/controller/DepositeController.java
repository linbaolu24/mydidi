package cn.com.didi.platform.trade.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.CountObject;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DepositDto;
import cn.com.didi.order.trade.service.IDepositService;

@RestController
public class DepositeController {

	@Resource
	protected IDepositService depositeService;

	@RequestMapping(value = "/platform/deposit/list", method = RequestMethod.POST)
	public IResult selectMerchants(@RequestBody TimeInterval interval) {
		IPage<DepositDto> page = depositeService.selectDepositList(interval);
		if (page == null) {
			return new ResultExt<>(null, CountObject.ZERO);
		}
		CountObject co = new CountObject(page.getCount());
		ResultExt<List<DepositDto>, CountObject> result = ResultExt.build(page);
		return result;
	}

	@RequestMapping(value = "/platform/deposit/refund", method = RequestMethod.POST)
	public IResult refund(@RequestBody DepositDto dto) {
		depositeService.refound(dto.getDepositId());
		return ResultFactory.success();
	}
}
