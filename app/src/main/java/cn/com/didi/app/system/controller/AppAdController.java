package cn.com.didi.app.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.app.system.domain.AdWrapperJAO;
import cn.com.didi.core.property.Couple;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.DpDto;
import cn.com.didi.user.ad.service.IAdService;
import cn.com.didi.webBase.util.IAccountResolver;

@RestController
public class AppAdController {
	@Resource
	protected IAdService adService;
	@Resource
	protected IAccountResolver accountResolver;

	@RequestMapping(value="/app/ad/get", method = RequestMethod.POST)
	public IResult getAds(@RequestBody DpDto adPic,HttpServletRequest request){
		//Long accountId=accountResolver.resolve(request);
		List<Couple<AdDto, AdPicDto>> lists=adService.queryAdList(null, adPic);
		if(CollectionUtils.isEmpty(lists)){
			return ResultFactory.success();
		}
		Map<String,List> maps=new HashMap<>();
		for(Couple<AdDto, AdPicDto> one:lists){
			if(one.getSecond()==null||one.getFirst()==null){
				return null;
			}
			List list=maps.get(one.getFirst().getDisplayPosition());
			if(list==null){
				list=new ArrayList<>(4);
				
				maps.put(one.getFirst().getDisplayPosition(), list);
			}
			list.add(new AdWrapperJAO(one.getFirst(), one.getSecond()));
		}
		return ResultFactory.success(maps);
		
	}
}
