package cn.com.didi.user.area.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.user.area.dao.mapper.AreaCodeDtoMapper;
import cn.com.didi.user.area.domain.AreaCodeDto;
import cn.com.didi.user.area.domain.AreaCodeDtoExample;
import cn.com.didi.user.area.service.IAreaService;
@Service
public class AreaServiceImpl implements IAreaService{
	@Resource
	protected AreaCodeDtoMapper mapper;
	@Override
	public List<AreaCodeDto> selectAreas(String pcode, String type) {
		AreaCodeDtoExample example=new AreaCodeDtoExample();
		AreaCodeDtoExample.Criteria cri=example.createCriteria();
		cri.andPAreaCodeEqualTo(pcode);
		return mapper.selectByExample(example);
	}
	@Override
	public AreaCodeDto selectAreas(String code) {
		return mapper.selectByPrimaryKey(code);
	}
	@Override
	public String selectAreaName(String code) {
		AreaCodeDto dto=selectAreas(code);
		if(dto!=null){
			return dto.getCname();
		}
		return null;
	}

}
