package cn.com.didi.platform.item.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.domain.util.ServiceState;
import cn.com.didi.domain.util.State;
import cn.com.didi.platform.item.domain.FlsTimeInterval;
import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.domain.SlsSericeExtDto;
import cn.com.didi.user.item.service.IItemService;

@RestController
public class ItemController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ItemController.class);
	@Resource
	protected IItemService itemServiece;

	@RequestMapping(value = "/platform/s/listFls", method = RequestMethod.POST)
	public IResult listFls(@RequestBody TimeInterval interval) {
		IPage<FlServiceDto> page = itemServiece.selectFls(interval);
		return ResultExt.build(page);
	}

	@RequestMapping(value = "/platform/s/addFls", method = RequestMethod.POST)
	public IResult addFls(@RequestBody FlServiceDto dto) {
		if (dto == null) {
			return null;
		}
		//dto.setServiceId(null);
		dto.setSlsNum(0);
		dto.setState(ServiceState.NORMAL.getCode());
		itemServiece.addFlService(dto);
		return ResultFactory.success();

	}

	@RequestMapping(value = "/platform/s/getFls", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult getFls() {
		return ResultFactory.success(itemServiece.selectAllFlService());
	}

	@RequestMapping(value = "/platform/s/updateFls", method = { RequestMethod.POST })
	public IResult updateFls(@RequestBody FlServiceDto dto) {
		if (dto == null) {
			LOGGER.warn("对象为空,不做处理");
			return ResultFactory.success();
		}
		if (dto.getServiceId() == null) {
			LOGGER.warn("一级服务ID为空,不做处理");
			return ResultFactory.success();
		}
		if (org.apache.commons.lang.StringUtils.isEmpty(dto.getState())) {
			LOGGER.warn("更新状态为空,不做处理");
			return ResultFactory.success();
		}
		itemServiece.updateFlsState(dto.getServiceId(), dto.getState());
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/s/flsDisplayOrder", method = { RequestMethod.POST, RequestMethod.GET })
	public IResult flsDisplayOrder() {
		Integer dispaly = itemServiece.selectMaxFlsDisplayOrder();
		if (dispaly == null) {
			dispaly = 0;
		}
		Map p = new HashMap(1);
		p.put(DomainConstatns.DISPLAY_ORDER, dispaly);
		return ResultFactory.success(p);
	}

	/* 以下为二级服务 */
	@RequestMapping(value = "/platform/s/listSls", method = RequestMethod.POST)
	public IResult listSls(@RequestBody FlsTimeInterval interval) {
		if (interval == null) {
			return ResultFactory.success();
		}
		IPage<SlServiceDto> page = itemServiece.selectSls(interval.time());
		return ResultExt.build(page);
	}

	@RequestMapping(value = "/platform/s/addSls", method = RequestMethod.POST)
	public IResult addSls(@RequestBody SlsSericeExtDto extDto) {
		if (extDto == null) {
			return null;
		}
		//extDto.setServiceId(null);
		extDto.setState(ServiceState.DRAFT.getCode());
		itemServiece.addSlsService(extDto.dto(),extDto.getCityList());
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/s/updateSls", method = RequestMethod.POST)
	public IResult updateSls(@RequestBody SlServiceDto dto) {
		if (dto == null) {
			LOGGER.warn("对象为空,不做处理");
			return ResultFactory.success();
		}
		if (dto.getServiceId() == null) {
			LOGGER.warn("二级服务ID为空,不做处理");
			return ResultFactory.success();
		}
		if (org.apache.commons.lang.StringUtils.isEmpty(dto.getState())) {
			LOGGER.warn("更新状态为空,不做处理");
			return ResultFactory.success();
		}
		itemServiece.updateSlsState(dto.getServiceId(), dto.getState());
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/s/slsDisplayOrder", method = RequestMethod.POST)
	public IResult slsDisplayOrder(@RequestBody SlServiceDto dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		Integer displayOrder = itemServiece.selectMaxSlsDisplayOrder(dto.getFlsId());
		if (displayOrder == null) {
			displayOrder = 0;
		}
		Map map = new HashMap();
		map.put(DomainConstatns.DISPLAY_ORDER, displayOrder);
		return ResultFactory.success(map);
	}
	
	@RequestMapping(value = "/platform/s/getSls", method = RequestMethod.POST)
	public IResult getSls(@RequestBody SlServiceDto dto) {
		if (dto == null) {
			return ResultFactory.success();
		}
		List<SlServiceDto> slsList = itemServiece.selectSls(dto.getFlsId());
		return ResultFactory.success(slsList);
	}
}
