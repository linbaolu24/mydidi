package cn.com.didi.user.item.dao.mapper;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.domain.SlServiceDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface SlServiceDtoMapper {
    int countByExample(SlServiceDtoExample example);

    int deleteByExample(SlServiceDtoExample example);

    int deleteByPrimaryKey(Integer serviceId);

    int insert(SlServiceDto record);

    int insertSelective(SlServiceDto record);

    List<SlServiceDto> selectByExample(SlServiceDtoExample example);

    SlServiceDto selectByPrimaryKey(Integer serviceId);

    int updateByExampleSelective(@Param("record") SlServiceDto record, @Param("example") SlServiceDtoExample example);

    int updateByExample(@Param("record") SlServiceDto record, @Param("example") SlServiceDtoExample example);

    int updateByPrimaryKeySelective(SlServiceDto record);

    int updateByPrimaryKey(SlServiceDto record);
    
    /**
     * @param flsService
     * @return
     */
    Integer selectMaxSlsDisplayOrder(Integer flsService);
    /**
     * @param interval
     * @param bounds
     * @return
     */
    List<SlServiceDto> selectSls(@Param("time") TimeInterval interval,RowBounds bounds);
    /**
     * @param slsId
     * @param displayOrder
     * @param flsId
     * @return
     */
    public int updateDisplayOrder(@Param("serviceId") Integer slsId,@Param("displayOrder") Integer displayOrder,@Param("flsId") Integer flsId);
}