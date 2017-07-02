package cn.com.didi.app.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.user.domain.VipDealJAO;
import cn.com.didi.app.user.domain.VipDescriptionJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.users.domain.VipDescrptionDto;
import cn.com.didi.user.users.service.IVipService;
import cn.com.didi.webBase.util.IAccountResolver;

@RestController
public class AppVipController {
	@Resource
	protected IVipService vipService;
	@Resource
	protected IAppEnv appEnv;
	@Resource
	protected IAccountResolver resolver;
	@RequestMapping(value="/app/c/vip/regInit",method=RequestMethod.POST)
	public IResult vipRegInit(){
		Map<String,Object> map=new HashMap<>();
		String desc=vipService.regInit(getDefaultSlsId());
		map.put(DomainConstatns.DESCRIPTION, desc);
		map.put(DomainConstatns.AMOUNT, appEnv.getDeposite());
		return ResultFactory.success(map);
	}
	Integer getDefaultSlsId(){
		return appEnv.getMfxfSlsId();
	}
	@RequestMapping(value="/app/c/vip/init",method=RequestMethod.POST)
		public IResult vipInit(HttpServletRequest request){
		Long accountId=resolver.resolve(request);
		Integer slsId=getDefaultSlsId();
		VipDescrptionDto descDto=vipService.desc(accountId, slsId);
		if(descDto==null){
			return ResultFactory.success();
		}
		return ResultFactory.success(new VipDescriptionJAO(descDto));
	}
	@RequestMapping(value="/app/c/vip/reg",method=RequestMethod.POST)
	public IResult reg(@RequestBody VipDealJAO vip,HttpServletRequest request){
		vip.setCreateTime(null);
		Long accountId=resolver.resolve(request);
		vip.setAccountId(accountId);
		vip.setSlsId(getDefaultSlsId());
		vipService.reg(vip, vip.getDealId());
		VipDescrptionDto descDto=vipService.desc(accountId, vip.getSlsId());
		if(descDto==null){
			return ResultFactory.success();
		}
		return ResultFactory.success(new VipDescriptionJAO(descDto));
		
	}
	
	@RequestMapping(value="/app/c/vip/preReg",method=RequestMethod.POST)
	public IResult preReg(@RequestBody VipDealJAO vip,HttpServletRequest request){
		vip.setCreateTime(null);
		Long accountId=resolver.resolve(request);
		vip.setAccountId(accountId);
		vip.setSlsId(getDefaultSlsId());
		vipService.preReg(vip, vip.getDealId());
		VipDescrptionDto descDto=vipService.desc(accountId, vip.getSlsId());
		if(descDto==null){
			return ResultFactory.success();
		}
		return ResultFactory.success(new VipDescriptionJAO(descDto));
		
	}
}
