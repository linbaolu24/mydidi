package cn.com.didi.user.users.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.user.users.dao.mapper.UserExperienceDtoMapper;
import cn.com.didi.user.users.domain.UserExperienceDto;
import cn.com.didi.user.users.domain.UserExperienceDtoKey;
import cn.com.didi.user.users.service.IUserExperienceService;

@Service
public class UserExperienceServiceImpl implements IUserExperienceService{
	@Resource
	protected UserExperienceDtoMapper mapper;
	@Override
	public boolean addExperience(Long accountId, Integer slsId) {
		UserExperienceDto dto=new UserExperienceDto();
		dto.setAccoutid(accountId);
		dto.setLastTime(new Date());
		dto.setNtlTime(dto.getLastTime());
		dto.setNum(1);
		dto.setSlsId(slsId);
		return mapper.insertIgnoreSelective(dto)>0;
	}
	@Override
	public boolean releaseExperience(Long accountId, Integer slsId) {
		UserExperienceDtoKey key=new UserExperienceDtoKey();
		key.setAccoutid(accountId);
		key.setSlsId(slsId);
		mapper.deleteByPrimaryKey(key);
		return true;
	}

}
