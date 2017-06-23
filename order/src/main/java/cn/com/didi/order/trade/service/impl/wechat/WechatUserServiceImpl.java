package cn.com.didi.order.trade.service.impl.wechat;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.wechat.WechatUserInfo;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.dao.mapper.UserWechatDtoMapper;
import cn.com.didi.order.trade.dao.mapper.UserWechatOpenIdDtoMapper;
import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.domain.UserWechatDtoExample;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDto;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDtoExample;
import cn.com.didi.order.trade.service.IWechatProvider;
import cn.com.didi.order.trade.service.IWechatUserService;

@Service
public class WechatUserServiceImpl implements IWechatUserService {
	@Resource
	protected UserWechatDtoMapper userWechatDtoMapper;
	@Resource
	protected UserWechatOpenIdDtoMapper myUserWechatOpenIdDtoMapper;
	@Resource
	protected IWechatProvider myWechatProvider;

	@Override
	@Transactional
	public UserWechatDto saveWithAccountId(Long accountId, WechatUserInfo info, String appid, WechatEnum type) {
		if (accountId != null) {
			userWechatDtoMapper.updateAccountId(info.getUnionid(), accountId);
			myUserWechatOpenIdDtoMapper.updateAccountId(info.getUnionid(), accountId);
		}
		return saveInternal(accountId, info, appid, type);
	}

	@Override
	@Transactional
	public UserWechatDto saveWithOutAccountId(WechatUserInfo info, String appid, WechatEnum type,
			TranscationalCallBack<WechatUserInfo> callBack) {
		UserWechatDto dto=saveInternal(null, info, appid, type);
		if (callBack != null) {
			callBack.invoke(info);
		}
		return dto;
	}

	protected UserWechatDto saveInternal(Long accountId, WechatUserInfo info, String appid, WechatEnum type) {
		UserWechatDto dto = infoToUserWechatDto(null, info);
		userWechatDtoMapper.insertSelective(dto);
		UserWechatOpenIdDto openId = infoToUserWechatOpenId(accountId, info, appid, type);
		if (openId != null) {
			myUserWechatOpenIdDtoMapper.insertSelective(openId);
		}
		return dto;
	}

	@Override
	public UserWechatDto getWechatDto(Long accountId) {
		UserWechatDtoExample exapmle = new UserWechatDtoExample();
		UserWechatDtoExample.Criteria criteria = exapmle.createCriteria();
		criteria.andAccountIdEqualTo(accountId);
		List<UserWechatDto> lists = userWechatDtoMapper.selectByExample(exapmle);
		if (CollectionUtils.isEmpty(lists)) {
			return null;
		}
		return lists.get(0);
	}

	@Override
	public UserWechatDto getWechatDto(Long accountId, String appid) {
		UserWechatDto dto = getWechatDto(accountId);
		if (dto != null) {
			UserWechatOpenIdDtoExample exapmle = new UserWechatOpenIdDtoExample();
			UserWechatOpenIdDtoExample.Criteria criteria = exapmle.createCriteria();
			criteria.andAccountIdEqualTo(accountId);
			criteria.andAppidEqualTo(appid);
			List<UserWechatOpenIdDto> wechatOpenId = myUserWechatOpenIdDtoMapper.selectByExample(exapmle);
			if (!CollectionUtils.isEmpty(wechatOpenId)) {
				dto.setOpenid(wechatOpenId.get(0).getOpenid());
			}
		}
		return dto;
	}

	protected UserWechatDto infoToUserWechatDto(Long accountId, WechatUserInfo info) {
		UserWechatDto dto = new UserWechatDto();
		dto.setAccountId(accountId);
		dto.setCountry(info.getCountry());
		dto.setCity(info.getCity());
		dto.setOpenid(info.getOpenid());
		dto.setSex(String.valueOf(info.getSex()));
		dto.setUnionid(info.getUnionid());
		dto.setProvince(info.getProvince());
		dto.setNickname(info.getNickname());
		dto.setHeadimgurl(info.getHeadimgurl());
		return dto;
	}

	protected UserWechatOpenIdDto infoToUserWechatOpenId(Long accountId, WechatUserInfo info, String appid,
			WechatEnum type) {
		if (!StringUtils.isEmpty(info.getOpenid())) {
			UserWechatOpenIdDto dto = new UserWechatOpenIdDto();
			dto.setAppid(appid);
			dto.setCode(type.name());
			dto.setAccountId(accountId);
			dto.setUnionid(info.getUnionid());
			dto.setOpenid(info.getOpenid());
			// dto.setFocusestate(focusestate);
			return dto;
		}
		return null;
	}

	@Override
	public UserWechatOpenIdDto getWechatDto(String unionId, WechatEnum type) {
		UserWechatOpenIdDtoExample example=new UserWechatOpenIdDtoExample();
		UserWechatOpenIdDtoExample.Criteria cri=example.createCriteria();
		cri.andUnionidEqualTo(unionId);
		String appid=myWechatProvider.getAppId(type);
		cri.andAppidEqualTo(appid);
		List<UserWechatOpenIdDto> lists=myUserWechatOpenIdDtoMapper.selectByExample(example);
		if(CollectionUtils.isEmpty(lists)){
			return null;
		}
		return lists.get(0);
	}
}
