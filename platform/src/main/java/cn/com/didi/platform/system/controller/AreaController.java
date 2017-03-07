package cn.com.didi.platform.system.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.util.NameConstans;
import cn.com.didi.user.area.domain.AreaCodeDto;
import cn.com.didi.user.area.service.IAreaService;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.service.ICodeDicService;

@RestController
public class AreaController {
	@Resource
	protected IAreaService areaService;
	@Resource
	protected ICodeDicService codeDicService;
	@RequestMapping(value="/platform/listArea",method=RequestMethod.POST)
	public IResult listArea(@RequestBody AreaCodeDto dto){
		AssertUtil.assertNotNullAppend(dto.getpAreaCode(), NameConstans.P_AREA_CODE);
		return ResultFactory.success(areaService.selectAreas(dto.getpAreaCode(),dto.getType()));
	}
	@RequestMapping(value="/platform/codeDic",method=RequestMethod.POST)
	public IResult codeDic(@RequestBody CodeDictionaryDto dto){
		AssertUtil.assertNotNullAppend(dto.getCname(), NameConstans.CODE_CNAME);
		return ResultFactory.success(codeDicService.selectCodes(dto.getCname()));
		
	}
}
