package cn.com.didi.order.trade.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.order.trade.domain.DealDrawListDto;
import cn.com.didi.order.trade.domain.DealDto;
import cn.com.didi.order.trade.domain.DealDtoExample;
import cn.com.didi.order.trade.domain.DealListDto;
import cn.com.didi.order.trade.domain.DealStatDto;

public interface DealDtoMapper {
    int countByExample(DealDtoExample example);

    int deleteByExample(DealDtoExample example);

    int deleteByPrimaryKey(Long dealId);

    int insert(DealDto record);

    int insertSelective(DealDto record);

    List<DealDto> selectByExample(DealDtoExample example);

    DealDto selectByPrimaryKey(Long dealId);

    int updateByExampleSelective(@Param("record") DealDto record, @Param("example") DealDtoExample example);

    int updateByExample(@Param("record") DealDto record, @Param("example") DealDtoExample example);

    int updateByPrimaryKeySelective(DealDto record);

    int updateByPrimaryKey(DealDto record);
    
    //add by my
    /**将交易状态改为成功*/
    public int updateDealState(@Param("dealId") Long dealId, @Param("destState") String destState, @Param("sourceState") String sourceState,@Param("tradeId") String tradeId,@Param("remain") Long remain);
    
    /**将交易状态改为成功*/
   // public int updateDealRemain(@Param("dealId") Long dealId, @Param("destState") Integer remain);
    /**
     * @param dealId
     * @param destState
     * @return
     */
    public int updatePureDealState(@Param("dealId") Long dealId, @Param("destState") String destState,@Param("tradeId") String tradeId);
    /**
     * @param dealId
     * @return
     */
    Long selectOrderIdFromDeal(Long dealId);
    
    List<DealListDto> selectTrades(@Param("time") TimeInterval time,RowBounds rows);
    List<DealListDto> selectDraws(@Param("time") TimeInterval time,RowBounds rows);
    /**
     * @param dealId
     * @param cause
     * @return
     */
    int updateFail(@Param("dealId") Long dealId,@Param("cause") String cause);
    
    List<DealDrawListDto> selectDrawList(@Param("time") TimeInterval time,RowBounds rows);
    /**
     * @param dai
     * @param fromDate
     * @param category
     * @return
     */
    Long countSum(@Param("dai") Long dai,@Param("fromDate") Date fromDate,@Param("cat") String category);
    /**
     * @param dealId
     * @param dest
     * @param cat
     * @param source
     * @return
     */
    int updateDealStateAndSourceArray(@Param("dealId") Long dealId, @Param("destState") String dest,@Param("cat") String cat,@Param("sourceState") String... source);
    /**
     * @param record
     * @return
     */
    int updateByPrimaryKeySelectiveAndSourceState(DealDto record);
    /**
     * @param accountId
     * @param rowBounds
     * @return
     */
    public List<DealStatDto> countDai(@Param("dai") Long accountId,@Param("endTime") Date endTime,RowBounds rowBounds);
}