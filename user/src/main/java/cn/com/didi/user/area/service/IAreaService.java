package cn.com.didi.user.area.service;

import java.util.List;

import cn.com.didi.user.area.domain.AreaCodeDto;

/**
 * @author xlm
 *
 */
public interface IAreaService {
	public List<AreaCodeDto> selectAreas(String pcode,String type);
	public AreaCodeDto selectAreas(String code);
	public String selectAreaName(String code);
}
