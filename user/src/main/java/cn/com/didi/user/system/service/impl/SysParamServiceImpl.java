package cn.com.didi.user.system.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.user.system.dao.mapper.SystemParameterDtoMapper;
import cn.com.didi.user.system.domain.SystemParameterDto;
import cn.com.didi.user.system.service.ISysParamService;

/**
 * @author xlm
 *
 */
@Service
public class SysParamServiceImpl implements ISysParamService{
	@Resource
	protected SystemParameterDtoMapper mapper;

	@Override
	public List<SystemParameterDto> selectAllParams() {
		return mapper.selectAll();
	}
	
}
