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

	@Override
	public SystemParameterDto selectSysparams(String paramCode) {
		return mapper.selectByPrimaryKey(paramCode);
	}

	@Override
	public String selectSysparamsValue(String paramCode) {
		SystemParameterDto  paramDto=selectSysparams(paramCode);
		if(paramDto!=null){
			return paramDto.getParamValue();
		}
		return null;
	}
	
}
