package cn.com.didi.user.area.dao.mapper;

import cn.com.didi.user.area.domain.AreaCodeDto;
import cn.com.didi.user.area.domain.AreaCodeDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AreaCodeDtoMapper {
    int countByExample(AreaCodeDtoExample example);

    int deleteByExample(AreaCodeDtoExample example);

    int deleteByPrimaryKey(String areaCode);

    int insert(AreaCodeDto record);

    int insertSelective(AreaCodeDto record);

    List<AreaCodeDto> selectByExample(AreaCodeDtoExample example);

    AreaCodeDto selectByPrimaryKey(String areaCode);

    int updateByExampleSelective(@Param("record") AreaCodeDto record, @Param("example") AreaCodeDtoExample example);

    int updateByExample(@Param("record") AreaCodeDto record, @Param("example") AreaCodeDtoExample example);

    int updateByPrimaryKeySelective(AreaCodeDto record);

    int updateByPrimaryKey(AreaCodeDto record);
}