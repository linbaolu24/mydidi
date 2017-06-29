package cn.com.didi.app.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;

import cn.com.didi.app.user.domain.AccountDomain;
import cn.com.didi.app.user.domain.MerchantServiceWrapperJAO;
import cn.com.didi.app.user.domain.MerchantWrapperJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.CrEnum;
import cn.com.didi.domain.util.Role;
import cn.com.didi.domain.util.State;
import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.service.IWechatBaseService;
import cn.com.didi.user.login2.service.ILoginService;
import cn.com.didi.user.register.service.IRegisterService;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantExtDto;
import cn.com.didi.user.users.domain.MerchantHolderDto;
import cn.com.didi.user.users.domain.MerchantServiceDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.users.service.IVipService;
import cn.com.didi.webBase.util.IAccountResolver;

@RestController
public class AppMerchantController {
	@Resource
	protected IAccountResolver resolver;
	@Resource
	protected IRegisterService registerService;
	@Resource
	protected ILoginService tloginService;
	@Resource
	protected IUserService tUserService;

	@Resource
	protected IMerchantService merchantService;
	@Resource
	protected IVipService vipService;
	@Resource
	protected IWechatBaseService wechatBaseService;


	@RequestMapping(value = "/app/user/enterMerchant", method = { RequestMethod.POST })
	public IResult enterMerchant(@RequestBody MerchantExtDto merchantExtDto, HttpServletRequest request) {
		AccountDomain obj = (AccountDomain) resolver.resolveObject(request);
		String role = (String) obj.getRole();
		if (!Role.BUSINESS.getCode().equals(role)) {
			throw new IllegalArgumentException("非商户端不能入驻企业。");
		}
		Long accountId =obj.getAccountId();
		merchantExtDto.setCr(CrEnum.WATTING.getCode());
		merchantExtDto.setState(State.VALID.getState());
		merchantExtDto.setBusinessCategory(BusinessCategory.THIRD.getCode());
		merchantExtDto.setAccountId(accountId);
		merchantExtDto.setBpn(obj.getBpn());
		popWechat(merchantExtDto, accountId);
		setNormal(merchantExtDto);
		merchantService.enterMerchant(merchantExtDto.dto(), merchantExtDto.getServiceList(), null);
		return ResultFactory.success();
	}
	protected void setNormal(MerchantExtDto extDto){
		if(CollectionUtils.isEmpty(extDto.getServiceList())&&!StringUtils.isEmpty(extDto.getServiceListStr())){
			extDto.setServiceList(JSONArray.parseArray(extDto.getServiceListStr(), MerchantServiceDto.class));
		}
	}
	protected void  popWechat(MerchantExtDto merchantExtDto,Long accountId){
		if(!StringUtils.isEmpty(merchantExtDto.getCode())){
			UserWechatDto userInfo=wechatBaseService.getUserInfo(accountId, merchantExtDto.getCode());
			MerchantDto mdto=merchantExtDto.dto();
			mdto.setWechatName(userInfo.getNickname());
			mdto.setWechatAccount(userInfo.getUnionid());
		}
	}

	@RequestMapping(value = "/app/user/merchantDetail", method = { RequestMethod.POST })
	public IResult merchantDetail( HttpServletRequest request) {
		Long accountId = resolver.resolve(request);
		MerchantHolderDto holderDto = merchantService.getMerchant(accountId);
		if (holderDto == null) {
			return ResultFactory.success();
		}
		MerchantWrapperJAO wrapped = new MerchantWrapperJAO(holderDto.getDto());
		List<MerchantServiceDto> lists = holderDto.getServiceList();
		if (!CollectionUtils.isEmpty(lists)) {
			List<MerchantServiceWrapperJAO> list2=lists.stream().map(MerchantServiceWrapperJAO::new).collect(Collectors.toList());
			wrapped.setServiceList(list2);
		}
		return ResultFactory.success(wrapped);
	}
	@RequestMapping(value = "/app/user/editMerchant", method = { RequestMethod.POST })
	public IResult editMerchant(@RequestBody MerchantExtDto merchantExtDto, HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		merchantExtDto.setAccountId(accountId);
		popWechat(merchantExtDto, accountId);
		setNormal(merchantExtDto);
		merchantService.editMerchantWithCheck(merchantExtDto.dto(), merchantExtDto.getServiceList(), null);
		return ResultFactory.success();
	}
}
