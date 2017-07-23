package cn.com.didi.user.users.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.core.property.Couple;
import cn.com.didi.core.select.IPage;
import cn.com.didi.core.select.IPageBound;
import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.domains.ReciverDto;
import cn.com.didi.domain.domains.SimpleMerchantDto;
import cn.com.didi.domain.util.BusinessCategory;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.Role;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;

@Service
public class MerchantLocationServiceImpl implements IReciverSearchService {
	@Resource
	protected IMerchantService merchantService;
	@Resource
	protected IUserService userService;

	@Override
	public List<IReciverDto> list(Point poi, Integer slsId) {
		List<MerchantAreaDto> areaDto = merchantService.select(poi, 20, slsId);
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

	protected IReciverDto userLinkIdDtoToReciverDto(UserLinkIdDto user, Role role) {
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
		/*
		 * if (StringUtils.isEmpty(areaCode)) { return null; }
		 */
		MerchantDto matched = matchMerchantDto(areaCode, poi, slsId);
		if (matched == null) {
			return null;
		}
		UserLinkIdDto linked = userService.selectUserLinkedId(matched.getAccountId());
		return userLinkIdDtoToReciverDto(linked);
	}

	protected MerchantDto matchMerchantDto(String areaCode, Point poi, Integer slsId) {
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
		return matched;
	}

	@Override
	public IReciverDto match(Long accoutId, Role role) {
		UserLinkIdDto uLinkDto = userService.selectUserLinkedId(accoutId);
		return userLinkIdDtoToReciverDto(uLinkDto, role);
	}

	@Override
	public IMerchantDto getMerchant(Long accountId) {
		MerchantDto mdto = merchantService.selectMerchant(accountId);
		return convert(mdto);
	}

	protected IMerchantDto convert(MerchantDto mdto) {
		SimpleMerchantDto smdto = new SimpleMerchantDto();
		smdto.setMasterName(mdto.getMastername());
		smdto.setMci(StringUtils.defaultIfEmpty(mdto.getContactInformation(), mdto.getBpn()));
		smdto.setLat(mdto.getLat());
		smdto.setLng(mdto.getLng());
		smdto.setMerchantId(mdto.getAccountId());
		return smdto;
	}

	@Override
	public Couple<IReciverDto, IMerchantDto> matchMerchant(String areaCode, Point poi, Integer slsId) {
		MerchantDto matched =matchMerchantDto(areaCode, poi, slsId);
		if(matched==null){
			return null;
		}
		UserLinkIdDto linked = userService.selectUserLinkedId(matched.getAccountId());
		IReciverDto first= userLinkIdDtoToReciverDto(linked);
		IMerchantDto second=convert(matched);
		Couple<IReciverDto, IMerchantDto> couple=new Couple<IReciverDto, IMerchantDto>(first, second);
		return couple;
	}

	@Override
	public Couple<IMerchantDto,IReciverDto > getMerchantAndReciver(Long accountId) {
		if(accountId==null){
			return null;
		}
		IMerchantDto merchant=getMerchant(accountId);
		if(merchant==null){
			return null;
		}
		IReciverDto reciver=match(accountId, Role.BUSINESS);
		return new Couple<IMerchantDto, IReciverDto>(merchant, reciver);
	}

	@Override
	public String getPhone(Long accountId) {
		return userService.selectPhone(accountId);
	}

	@Override
	public void updateEve(Long accountId, int eve) {
		//merchantService.updateEve(accountId, eve);
	}

	@Override
	public IPage<IReciverDto> listAllUser(Role role, IPageBound pageBounds) {
		return userService.listAllUser(role, pageBounds);
	}

	@Override
	public IPage<IReciverDto> listMerchats(BusinessCategory category, IPageBound pageBounds) {
		return userService.listAllBusiness(category, pageBounds);
	}

	@Override
	public List<IReciverDto> match(List<Long> accoutId) {
		if(CollectionUtils.isEmpty(accoutId)){
			return null;
		}
		List<UserLinkIdDto> linkedId=userService.selectUserLinkedId(accoutId);
		if(CollectionUtils.isEmpty(linkedId)){
			return null;
		}
		return linkedId.stream().map(this::userLinkIdDtoToReciverDto).collect(Collectors.toList());
	}

}
