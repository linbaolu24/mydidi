package cn.com.didi.user.system.service;

import java.util.List;

import cn.com.didi.user.system.domain.SystemParameterDto;

public interface ISysParamService {
	/**
	 * @return
	 */
	List<SystemParameterDto> selectAllParams();
	/**
	 * @return
	 */
	SystemParameterDto selectSysparams(String paramCode);
	
	/**
	 * @return
	 */
	String selectSysparamsValue(String paramCode);
}
