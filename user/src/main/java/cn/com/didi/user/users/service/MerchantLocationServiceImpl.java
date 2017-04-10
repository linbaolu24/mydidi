package cn.com.didi.user.users.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.domains.ReciverDto;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.Role;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

@Service
public class MerchantLocationServiceImpl implements IReciverSearchService {
	@Resource
	protected IMerchantService merchantService;
	@Resource
	protected IUserService userService;

	@Override
	public List<IReciverDto> list(Point poi, Integer slsId) {
		List<MerchantAreaDto> areaDto = merchantService.select(poi, 5, slsId);
		if (CollectionUtils.isEmpty(areaDto)) {
			return null;
		}
		List<Long> accountList = new ArrayList<Long>(areaDto.size());
		for (MerchantAreaDto one : areaDto) {
			if (one.getAccountId() != null) {
				accountList.add(one.getAccountId());
			}
		}
		List<UserLinkIdDto> users = userService.selectUserLinkedId(accountList);
		if (CollectionUtils.isEmpty(users)) {
			return null;
		}
		List<IReciverDto> reciverList = new ArrayList<>(users.size());
		IReciverDto temp = null;
		for (UserLinkIdDto one : users) {
			temp = userLinkIdDtoToReciverDto(one);
			if (temp != null) {
				reciverList.add(temp);
			}
		}
		return reciverList;
	}

	protected IReciverDto userLinkIdDtoToReciverDto(UserLinkIdDto user) {
		return userLinkIdDtoToReciverDto(user, Role.BUSINESS);
	}
	
	protected IReciverDto userLinkIdDtoToReciverDto(UserLinkIdDto user,Role role) {
		ReciverDto temp = null;
		if (user != null) {
			temp = new ReciverDto();
			temp.setAccountId(user.getAccountId());
			temp.setReciveType("gt");
			temp.setReciveId(user.getGtCid());
			temp.setAccountType(role);
		}
		return temp;
	}

	@Override
	public IReciverDto match(String areaCode, Point poi, Integer slsId) {
		/*if (StringUtils.isEmpty(areaCode)) {
			return null;
		}*/
		if (poi == null) {
			return null;
		}
		if (StringUtils.isEmpty(poi.getLat()) || StringUtils.isEmpty(poi.getLng())) {
			return null;
		}
		MerchantAreaDto mad = new MerchantAreaDto();
		mad.setAreaCode(areaCode);
		mad.setLat(new BigDecimal(poi.getLat()));
		mad.setLng(new BigDecimal(poi.getLng()));
		MerchantDto matched = merchantService.match(mad, slsId);
		if (matched == null) {
			return null;
		}
		UserLinkIdDto linked=userService.selectUserLinkedId(matched.getAccountId());
		return userLinkIdDtoToReciverDto(linked);
	}

	@Override
	public IReciverDto match(Long accoutId,Role role) {
		UserLinkIdDto uLinkDto=userService.selectUserLinkedId(accoutId);
		return userLinkIdDtoToReciverDto(uLinkDto,role);
	}

}
