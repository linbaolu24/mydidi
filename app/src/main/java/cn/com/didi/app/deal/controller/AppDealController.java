package cn.com.didi.app.deal.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.deal.domain.DrawInfoWrapperJAO;
import cn.com.didi.app.deal.domain.DrawJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.DealDrawListDto;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DrawInfoDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
@RestController
public class AppDealController extends AbstractDealController{
	@Resource
	protected IUserService userService;
	@RequestMapping(value = "/app/c/deposit/drawList", method = RequestMethod.POST)
	public IResult drawList(@RequestBody TimeInterval interval ,HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		interval.setAccountId(accountId);
		List<DealDrawListDto> remain=tradeInfoService.selectDrawList(interval);
		return ResultFactory.success(remain);
	}
	@RequestMapping(value = "/app/c/deposit/drawInit", method = RequestMethod.POST)
	public IResult drawInit(HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		UserLinkIdDto linkedIdDto=userService.selectUserLinkedId(accountId);
		DrawInfoDto drawInfo=accountAssetsService.drawInfo(accountId);
		if(drawInfo==null){
			drawInfo=new DrawInfoDto();
		}
		return ResultFactory.success(new DrawInfoWrapperJAO(drawInfo, linkedIdDto));
	}
	@RequestMapping(value = "/app/c/deposit/draw", method = RequestMethod.POST)
	public IResult draw(@RequestBody DrawJAO drawJao,HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		UserLinkIdDto linked=userService.selectUserLinkedId(accountId);
		PayAccountEnum enums=cn.com.didi.core.property.ICodeAble.getCode(PayAccountEnum.values(),drawJao.getPat());
		AssertUtil.assertNotNullAppend(enums, "到账账户");
		AssertUtil.assertNotNullAppend(drawJao.getAmount(), "提现金额");
		if(drawJao.getAmount()<10L){
			throw new IllegalArgumentException("提现金额不能小于0.1元。");
		}
		DealDto dealDto=new DealDto();
		dealDto.setDat(enums.getCode());
		dealDto.setAmount(drawJao.getAmount().intValue());
		dealDto.setCreateTime(new Date());
		
		String  da=enums.getAccoutId(linked);
		dealDto.setDa(da);
		dealDto.setExt1(linked.userName());
		dealDto.setCommission(0);
		tradeService.draw(dealDto);
		return ResultFactory.success();
	}
}
