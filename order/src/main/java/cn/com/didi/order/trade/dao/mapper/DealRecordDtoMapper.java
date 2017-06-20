package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.DealRecordDto;
import cn.com.didi.order.trade.domain.DealRecordDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DealRecordDtoMapper {
    int countByExample(DealRecordDtoExample example);

    int deleteByExample(DealRecordDtoExample example);

    int deleteByPrimaryKey(Long streamId);

    int insert(DealRecordDto record);

    int insertSelective(DealRecordDto record);

    List<DealRecordDto> selectByExampleWithBLOBs(DealRecordDtoExample example);

    List<DealRecordDto> selectByExample(DealRecordDtoExample example);

    DealRecordDto selectByPrimaryKey(Long streamId);

    int updateByExampleSelective(@Param("record") DealRecordDto record, @Param("example") DealRecordDtoExample example);

    int updateByExampleWithBLOBs(@Param("record") DealRecordDto record, @Param("example") DealRecordDtoExample example);

    int updateByExample(@Param("record") DealRecordDto record, @Param("example") DealRecordDtoExample example);

    int updateByPrimaryKeySelective(DealRecordDto record);

    int updateByPrimaryKeyWithBLOBs(DealRecordDto record);

    int updateByPrimaryKey(DealRecordDto record);
}