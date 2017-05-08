package cn.com.didi.user.ad.dao.mapper;

import cn.com.didi.user.ad.domain.AdPicDto;
import cn.com.didi.user.ad.domain.AdPicDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdPicDtoMapper {
    int countByExample(AdPicDtoExample example);

    int deleteByExample(AdPicDtoExample example);

    int deleteByPrimaryKey(Long picId);

    int insert(AdPicDto record);

    int insertSelective(AdPicDto record);

    List<AdPicDto> selectByExample(AdPicDtoExample example);

    AdPicDto selectByPrimaryKey(Long picId);

    int updateByExampleSelective(@Param("record") AdPicDto record, @Param("example") AdPicDtoExample example);

    int updateByExample(@Param("record") AdPicDto record, @Param("example") AdPicDtoExample example);

    int updateByPrimaryKeySelective(AdPicDto record);

    int updateByPrimaryKey(AdPicDto record);
}