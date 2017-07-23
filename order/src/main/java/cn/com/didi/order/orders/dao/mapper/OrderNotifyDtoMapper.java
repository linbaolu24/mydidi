package cn.com.didi.order.orders.dao.mapper;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.order.orders.domain.OrderNotifyDto;
import cn.com.didi.order.orders.domain.OrderNotifyDtoExample;
import cn.com.didi.order.orders.domain.OrderNotifyDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderNotifyDtoMapper {
    int countByExample(OrderNotifyDtoExample example);

    int deleteByExample(OrderNotifyDtoExample example);

    int deleteByPrimaryKey(OrderNotifyDtoKey key);

    int insert(OrderNotifyDto record);

    int insertSelective(OrderNotifyDto record);

    List<OrderNotifyDto> selectByExample(OrderNotifyDtoExample example);

    OrderNotifyDto selectByPrimaryKey(OrderNotifyDtoKey key);

    int updateByExampleSelective(@Param("record") OrderNotifyDto record, @Param("example") OrderNotifyDtoExample example);

    int updateByExample(@Param("record") OrderNotifyDto record, @Param("example") OrderNotifyDtoExample example);

    int updateByPrimaryKeySelective(OrderNotifyDto record);

    int updateByPrimaryKey(OrderNotifyDto record);
    //add by my
    int insertNotifyList(@Param("record") OrderNotifyDto record,@Param("rlist") List<IReciverDto> lists);
    /**
     * @param record
     * @param lists
     * @return
     */
    List<Long> selectMerchatId(Long orderId);
}