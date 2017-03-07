package cn.com.didi.user.item.dao.mapper;

import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.domain.SlsCityDto;
import cn.com.didi.user.item.domain.SlsCityDtoExample;
import cn.com.didi.user.item.domain.SlsCityDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SlsCityDtoMapper {
    int countByExample(SlsCityDtoExample example);

    int deleteByExample(SlsCityDtoExample example);

    int deleteByPrimaryKey(SlsCityDtoKey key);

    int insert(SlsCityDto record);

    int insertSelective(SlsCityDto record);

    List<SlsCityDto> selectByExample(SlsCityDtoExample example);

    SlsCityDto selectByPrimaryKey(SlsCityDtoKey key);

    int updateByExampleSelective(@Param("record") SlsCityDto record, @Param("example") SlsCityDtoExample example);

    int updateByExample(@Param("record") SlsCityDto record, @Param("example") SlsCityDtoExample example);

    int updateByPrimaryKeySelective(SlsCityDto record);

    int updateByPrimaryKey(SlsCityDto record);
    /**
     * @param dto
     * @return
     */
    List<SlsCityDtoKey> selectSlsCity(List<SlServiceDto> dto);
}