package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DepositDto;
import cn.com.didi.order.trade.domain.DepositDtoExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface DepositDtoMapper {
    int countByExample(DepositDtoExample example);

    int deleteByExample(DepositDtoExample example);

    int deleteByPrimaryKey(Long depositId);

    int insert(DepositDto record);

    int insertSelective(DepositDto record);

    List<DepositDto> selectByExample(DepositDtoExample example);

    DepositDto selectByPrimaryKey(Long depositId);

    int updateByExampleSelective(@Param("record") DepositDto record, @Param("example") DepositDtoExample example);

    int updateByExample(@Param("record") DepositDto record, @Param("example") DepositDtoExample example);

    int updateByPrimaryKeySelective(DepositDto record);

    int updateByPrimaryKey(DepositDto record);
    
    //add by my
    List<DepositDto> selectDepositList(@Param("time") TimeInterval interval, RowBounds bounds);
    /**
     * 查找满足参数的第一个DepositId
     * @param accountId
     * @param time
     * @param state
     * @return
     */
    Long selectFirstDepositId(@Param("sai") Long accountId,@Param("time") Date time,@Param("state") String state);
    /**
     * @param accountId
     * @param time
     * @param state
     * @return
     */
    Long countDeposit(@Param("sai") Long accountId,@Param("time") Date time,@Param("state") String state);
}