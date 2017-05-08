package cn.com.didi.user.ad.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.didi.user.ad.domain.AdReportDto;
import cn.com.didi.user.ad.domain.AdReportDtoExample;
import cn.com.didi.user.ad.domain.AdReportDtoKey;
import cn.com.didi.user.ad.domain.AdTimeInterval;

public interface AdReportDtoMapper {
    int countByExample(AdReportDtoExample example);

    int deleteByExample(AdReportDtoExample example);

    int deleteByPrimaryKey(AdReportDtoKey key);

    int insert(AdReportDto record);

    int insertSelective(AdReportDto record);

    List<AdReportDto> selectByExample(AdReportDtoExample example);

    AdReportDto selectByPrimaryKey(AdReportDtoKey key);

    int updateByExampleSelective(@Param("record") AdReportDto record, @Param("example") AdReportDtoExample example);

    int updateByExample(@Param("record") AdReportDto record, @Param("example") AdReportDtoExample example);

    int updateByPrimaryKeySelective(AdReportDto record);

    int updateByPrimaryKey(AdReportDto record);
    
    int insertOrupdateExposure(AdReportDto record);
    int insertOrupdateExposureList(List<AdReportDto> record);//TODO sql未写
    
    
    int updateClickRate(AdReportDto record);
    
    /**
     * @param time
     * @return
     */
    List<AdReportDto> selectReportHour(AdTimeInterval time);
    /**
     * @param time
     * @return
     */
    List<AdReportDto> selectReportDay(AdTimeInterval time);
}