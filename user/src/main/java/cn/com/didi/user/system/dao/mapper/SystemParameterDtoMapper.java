package cn.com.didi.user.system.dao.mapper;

import cn.com.didi.user.system.domain.SystemParameterDto;
import cn.com.didi.user.system.domain.SystemParameterDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SystemParameterDtoMapper {
    int countByExample(SystemParameterDtoExample example);

    int deleteByExample(SystemParameterDtoExample example);

    int deleteByPrimaryKey(String paramCode);

    int insert(SystemParameterDto record);

    int insertSelective(SystemParameterDto record);

    List<SystemParameterDto> selectByExample(SystemParameterDtoExample example);

    SystemParameterDto selectByPrimaryKey(String paramCode);

    int updateByExampleSelective(@Param("record") SystemParameterDto record, @Param("example") SystemParameterDtoExample example);

    int updateByExample(@Param("record") SystemParameterDto record, @Param("example") SystemParameterDtoExample example);

    int updateByPrimaryKeySelective(SystemParameterDto record);

    int updateByPrimaryKey(SystemParameterDto record);
    
    List<SystemParameterDto> selectAll();
}