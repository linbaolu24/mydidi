package cn.com.didi.user.system.service;

import java.util.List;

import cn.com.didi.user.system.domain.SystemParameterDto;

public interface ISysParamService {
	List<SystemParameterDto> selectAllParams();
}
