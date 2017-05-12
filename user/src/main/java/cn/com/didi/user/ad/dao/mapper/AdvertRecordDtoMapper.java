package cn.com.didi.user.ad.dao.mapper;

import cn.com.didi.user.ad.domain.AdvertRecordDto;
import cn.com.didi.user.ad.domain.AdvertRecordDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvertRecordDtoMapper {
    int countByExample(AdvertRecordDtoExample example);

    int deleteByExample(AdvertRecordDtoExample example);

    int deleteByPrimaryKey(Long recordId);

    int insert(AdvertRecordDto record);

    int insertSelective(AdvertRecordDto record);

    List<AdvertRecordDto> selectByExample(AdvertRecordDtoExample example);

    AdvertRecordDto selectByPrimaryKey(Long recordId);

    int updateByExampleSelective(@Param("record") AdvertRecordDto record, @Param("example") AdvertRecordDtoExample example);

    int updateByExample(@Param("record") AdvertRecordDto record, @Param("example") AdvertRecordDtoExample example);

    int updateByPrimaryKeySelective(AdvertRecordDto record);

    int updateByPrimaryKey(AdvertRecordDto record);
}