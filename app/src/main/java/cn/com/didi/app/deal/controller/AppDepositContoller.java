package cn.com.didi.app.deal.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.DomainConstatns;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.webBase.util.IAccountResolver;
@RestController
public class AppDepositContoller {
	@Resource
	protected IDepositService depositService;
	@Resource
	protected IAccountResolver resolver;
	@RequestMapping(value = "/app/c/deposit/init", method = RequestMethod.POST)
	public IResult depositInit(HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		Long remain=depositService.countDeposit(accountId);
		Map<String,Object> p=new HashMap<>(1);
		p.put(DomainConstatns.AMOUNT, remain==null?0:remain);
		return ResultFactory.success(p);
	}
}
