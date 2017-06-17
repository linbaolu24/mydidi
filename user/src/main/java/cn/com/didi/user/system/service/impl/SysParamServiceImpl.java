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
	protected SystemParameterDtoMapper sysParammapper;

	@Override
	public List<SystemParameterDto> selectAllParams() {
		return sysParammapper.selectAll();
	}

	@Override
	public SystemParameterDto selectSysparams(String paramCode) {
		return sysParammapper.selectByPrimaryKey(paramCode);
	}

	@Override
	public String selectSysparamsValue(String paramCode) {
		SystemParameterDto  paramDto=selectSysparams(paramCode);
		if(paramDto!=null){
			return paramDto.getParamValue();
		}
		return null;
	}

	@Override
	public void updateSystemValue(String paramCode, String value) {
		SystemParameterDto dto=new SystemParameterDto();
		dto.setParamCode(paramCode);
		dto.setParamValue(value);
		sysParammapper.updateByPrimaryKey(dto);
		
	}
	
}
