package cn.com.didi.user.ad.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdDtoExample;

public interface AdDtoMapper {
    int countByExample(AdDtoExample example);

    int deleteByExample(AdDtoExample example);

    int deleteByPrimaryKey(Long adId);

    int insert(AdDto record);

    int insertSelective(AdDto record);

    List<AdDto> selectByExample(AdDtoExample example);

    AdDto selectByPrimaryKey(Long adId);

    int updateByExampleSelective(@Param("record") AdDto record, @Param("example") AdDtoExample example);

    int updateByExample(@Param("record") AdDto record, @Param("example") AdDtoExample example);

    int updateByPrimaryKeySelective(AdDto record);

    int updateByPrimaryKey(AdDto record);
    
    int updateState(IdStateDto stateDto);
    
    /**
     * @param interval
     * @return
     */
    List<AdDto>  selectAdPage(@Param("time") TimeInterval interval,RowBounds rowBounds);
    AdDto selectAd(@Param("dispaly") String dispaly,@Param("time") Date time,@Param("hour") int hour);
    /***/
    List<AdDto>  selectAdList(@Param("dispaly") String dispaly,@Param("time") Date time,@Param("hour") int hour);
    int updateAddExposure(@Param("adId") Long adId,@Param("added") int added);
    int updateAddClickRate(@Param("adId") Long adId,@Param("added") int added);
}