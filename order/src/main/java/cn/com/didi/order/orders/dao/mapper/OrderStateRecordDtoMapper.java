package cn.com.didi.order.orders.dao.mapper;

import cn.com.didi.order.orders.domain.OrderStateRecordDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDtoExample;
import cn.com.didi.order.orders.domain.OrderStateRecordDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderStateRecordDtoMapper {
    int countByExample(OrderStateRecordDtoExample example);

    int deleteByExample(OrderStateRecordDtoExample example);

    int deleteByPrimaryKey(OrderStateRecordDtoKey key);

    int insert(OrderStateRecordDto record);

    int insertSelective(OrderStateRecordDto record);

    List<OrderStateRecordDto> selectByExample(OrderStateRecordDtoExample example);

    OrderStateRecordDto selectByPrimaryKey(OrderStateRecordDtoKey key);

    int updateByExampleSelective(@Param("record") OrderStateRecordDto record, @Param("example") OrderStateRecordDtoExample example);

    int updateByExample(@Param("record") OrderStateRecordDto record, @Param("example") OrderStateRecordDtoExample example);

    int updateByPrimaryKeySelective(OrderStateRecordDto record);

    int updateByPrimaryKey(OrderStateRecordDto record);
}