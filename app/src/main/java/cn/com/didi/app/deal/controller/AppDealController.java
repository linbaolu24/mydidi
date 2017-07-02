package cn.com.didi.app.deal.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.deal.domain.DrawInfoWrapperJAO;
import cn.com.didi.app.deal.domain.DrawJAO;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.impl.SimplePageBound;
import cn.com.didi.core.utils.AssertUtil;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.domain.DealDrawListDto;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DrawInfoDto;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDto;
import cn.com.didi.order.trade.service.IWechatUserService;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserService;
@RestController
public class AppDealController extends AbstractDealController{
	@Resource
	protected IUserService userService;
	@Resource
	protected IWechatUserService wechatUserService;
	@RequestMapping(value = "/app/b/trade/drawList", method = RequestMethod.POST)
	public IResult drawList(@RequestBody TimeInterval interval ,HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		interval.setAccountId(accountId);
		List<DealDrawListDto> remain=tradeInfoService.selectDrawList(interval);
		return ResultFactory.success(remain);
	}
	@RequestMapping(value = "/app/b/trade/drawInit", method = RequestMethod.POST)
	public IResult drawInit(HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		UserLinkIdDto linkedIdDto=userService.selectUserLinkedId(accountId);
		DrawInfoDto drawInfo=accountAssetsService.drawInfo(accountId);
		if(drawInfo==null){
			drawInfo=new DrawInfoDto();
		}
		return ResultFactory.success(new DrawInfoWrapperJAO(drawInfo, linkedIdDto));
	}
	@RequestMapping(value = "/app/b/trade/stat", method = RequestMethod.POST)
	public IResult dealStat(@RequestBody SimplePageBound pageBounds,HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		List list=tradeInfoService.statBusiness(accountId, pageBounds);
		Date date=new Date();
		Map map=new HashMap<>();
		map.put("date", date);
		map.put("list", list);
		return ResultFactory.success(map);
	}
	@RequestMapping(value = "/app/b/trade/draw", method = RequestMethod.POST)
	public IResult draw(@RequestBody DrawJAO drawJao,HttpServletRequest request){
		Long accountId = resolver.resolve(request);
		UserLinkIdDto linked=userService.selectUserLinkedId(accountId);
		PayAccountEnum enums=cn.com.didi.core.property.ICodeAble.getCode(PayAccountEnum.values(),drawJao.getPat());
		AssertUtil.assertNotNullAppend(enums, "到账账户");
		AssertUtil.assertNotNullAppend(drawJao.getAmount(), "提现金额");
		if(drawJao.getAmount()<enums.getMinTransAmount()){
			throw new IllegalArgumentException("提现金额不能小于"+NumberUtil.intToDecimal2(enums.getMinTransAmount())+"元。");
		}
		if(drawJao.getAmount().longValue()>Integer.MAX_VALUE){
			throw new IllegalArgumentException("提现金额不能超过1400万");
		}
			
		DealDto dealDto=new DealDto();
		dealDto.setDat(enums.getCode());
		dealDto.setAmount(drawJao.getAmount().intValue());
		dealDto.setCreateTime(new Date());
		String  da=enums.getAccoutId(linked);
		if(StringUtils.isEmpty(da)){
			throw new IllegalArgumentException("请先绑定提现账号。");
		}
		if(PayAccountEnum.WECHATPAY.equals(enums)){
			UserWechatOpenIdDto dtos=wechatUserService.getWechatDto(da, WechatEnum.APP);
			if(dtos==null||StringUtils.isEmpty(dtos.getOpenid())){
				throw new IllegalArgumentException("请先关注公众号。");
			}
			da=dtos.getOpenid();
		}
		dealDto.setDa(da);
		dealDto.setDai(accountId);
		dealDto.setSa(da);
		dealDto.setSat(dealDto.getDat());
		dealDto.setExt1(linked.userName());
		dealDto.setCommission(0);
		tradeService.draw(dealDto);
		return ResultFactory.success();
	}
}
