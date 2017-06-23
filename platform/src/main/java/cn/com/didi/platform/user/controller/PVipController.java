package cn.com.didi.platform.user.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.user.users.domain.VipDto;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.users.service.IVipService;

@RestController
public class PVipController {
	@Resource
	protected IVipService vipService;
	@Resource
	protected IUserService userService;
	@RequestMapping(value = "/platform/vip/list", method = RequestMethod.POST)
	public IResult selectMerchants(@RequestBody TimeInterval interval) {
		IPage<VipDto> page = vipService.listPage(interval);
		return ResultExt.build(page);
	}
	@RequestMapping(value = "/platform/vip/update", method = RequestMethod.POST)
	public IResult updateVip(@RequestBody VipDto interval) {
		vipService.updateVip(interval.getAccountId(), null, interval.getCname(), interval.getBpn(), interval.getProfilePhoto());
		return ResultFactory.success();
	}
	@RequestMapping(value = "/platform/vip/set", method = RequestMethod.POST)
	public IResult setVip(@RequestBody Map<String,String> map) {
		String value=map.get(DomainConstatns.AMOUNT);
		AssertUtil.assertNotNullAppend(value, "金额");
		int newValue=new BigDecimal(value).multiply(Constans.YB).intValue();
		if(newValue<=0){
			throw new IllegalArgumentException("金额不能为负。");
		}
		vipService.setVipFee(null, newValue);
		return ResultFactory.success();
	}
	
	@RequestMapping(value = "/platform/vip/get", method = RequestMethod.POST)
	public IResult getVip() {
		
		int fee=vipService.getVipFee(null);
		Map<String,String> map =new HashMap<>(1);
		map.put(DomainConstatns.AMOUNT,NumberUtil.intToDecimal2(fee));
		return ResultFactory.success(map);
	}
}
