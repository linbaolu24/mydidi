package cn.com.didi.user.item.dao.mapper;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.FlServiceDtoExample;
import cn.com.didi.user.item.domain.FlServiceItemDto;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface FlServiceDtoMapper {
    int countByExample(FlServiceDtoExample example);

    int deleteByExample(FlServiceDtoExample example);

    int deleteByPrimaryKey(Integer serviceId);

    int insert(FlServiceDto record);

    int insertSelective(FlServiceDto record);

    List<FlServiceDto> selectByExample(FlServiceDtoExample example);

    FlServiceDto selectByPrimaryKey(Integer serviceId);

    int updateByExampleSelective(@Param("record") FlServiceDto record, @Param("example") FlServiceDtoExample example);

    int updateByExample(@Param("record") FlServiceDto record, @Param("example") FlServiceDtoExample example);

    int updateByPrimaryKeySelective(FlServiceDto record);

    int updateByPrimaryKey(FlServiceDto record);
    /**
     * 
     */
    public List<FlServiceDto> selectAllFlService();
    /**
     * @return
     */
    Integer selectMaxFlsDisplayOrder();
    
    List<FlServiceDto> selectFls(@Param("time") TimeInterval intervale,RowBounds bounds);
    
    void updateFlsCount(Integer flsId);
    
    void updateDisplayOrder(@Param("serviceId") Integer flsId,@Param("displayOrder") Integer displayOrder);
    /**
     * @return
     */
    List<FlServiceItemDto> selectItems();
}