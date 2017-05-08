package cn.com.didi.user.ad.dao.mapper;

import cn.com.didi.user.ad.domain.DpDto;
import cn.com.didi.user.ad.domain.DpDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DpDtoMapper {
    int countByExample(DpDtoExample example);

    int deleteByExample(DpDtoExample example);

    int deleteByPrimaryKey(Long dpId);

    int insert(DpDto record);

    int insertSelective(DpDto record);

    List<DpDto> selectByExample(DpDtoExample example);

    DpDto selectByPrimaryKey(Long dpId);

    int updateByExampleSelective(@Param("record") DpDto record, @Param("example") DpDtoExample example);

    int updateByExample(@Param("record") DpDto record, @Param("example") DpDtoExample example);

    int updateByPrimaryKeySelective(DpDto record);

    int updateByPrimaryKey(DpDto record);
}