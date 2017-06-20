package cn.com.didi.platform.ad.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.platform.ad.domain.AdDispalyJO;
import cn.com.didi.platform.ad.domain.AdHolderJO;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdReportDto;
import cn.com.didi.user.ad.domain.AdTimeInterval;
import cn.com.didi.user.ad.domain.DpDto;
import cn.com.didi.user.ad.service.IAdService;

@RestController
public class AdController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(AdController.class);
	@Resource
	protected IAdService adService;

	@RequestMapping(value = "/platform/ad/list", method = RequestMethod.POST)
	public IResult listFls(@RequestBody AdTimeInterval interval) {
		IPage<AdDto> page = adService.selectAdPage(interval);
		return ResultExt.build(page);
	}

	@RequestMapping(value = "/platform/ad/add", method = RequestMethod.POST)
	public IResult addFls(@RequestBody AdHolderJO dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		adService.add(dto.dto(), dto.getImgList());
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/ad/allDisplayPosition", method = { RequestMethod.POST })
	public IResult allDisplay() {
		List<DpDto> lists=adService.selectAllDp();
		List<AdDispalyJO> jo=AdDispalyJO.wrapper(lists);
		return ResultFactory.success(jo);
	}
	@RequestMapping(value = "/platform/ad/updateState", method = { RequestMethod.POST })
	public IResult updateState(@RequestBody List<IdStateDto> lists) {
		adService.updateState(lists);
		return ResultFactory.success();
	}
/*	@RequestMapping(value = "/platform/ad/report", method = { RequestMethod.POST })
	public IResult updateState(AdTimeInterval report) {
		List<AdReportDto> reports=adService.report(report);
		return ResultFactory.success(reports);
	}*/
	
}
