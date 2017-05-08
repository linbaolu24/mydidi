package cn.com.didi.user.message.dao.mapper;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.message.domain.TMessageDto;
import cn.com.didi.user.message.domain.TMessageDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TMessageDtoMapper {
    int countByExample(TMessageDtoExample example);

    int deleteByExample(TMessageDtoExample example);

    int deleteByPrimaryKey(Long messageId);

    int insert(TMessageDto record);

    int insertSelective(TMessageDto record);

    List<TMessageDto> selectByExampleWithBLOBs(TMessageDtoExample example);

    List<TMessageDto> selectByExample(TMessageDtoExample example);

    TMessageDto selectByPrimaryKey(Long messageId);

    int updateByExampleSelective(@Param("record") TMessageDto record, @Param("example") TMessageDtoExample example);

    int updateByExampleWithBLOBs(@Param("record") TMessageDto record, @Param("example") TMessageDtoExample example);

    int updateByExample(@Param("record") TMessageDto record, @Param("example") TMessageDtoExample example);

    int updateByPrimaryKeySelective(TMessageDto record);

    int updateByPrimaryKeyWithBLOBs(TMessageDto record);

    int updateByPrimaryKey(TMessageDto record);
    
    List<TMessageDto> selectPage(@Param("time") TimeInterval interval, RowBounds rowBounds) ;
}