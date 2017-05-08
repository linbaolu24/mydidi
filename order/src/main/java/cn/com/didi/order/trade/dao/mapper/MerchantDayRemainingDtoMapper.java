package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDtoExample;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantDayRemainingDtoMapper {
    int countByExample(MerchantDayRemainingDtoExample example);

    int deleteByExample(MerchantDayRemainingDtoExample example);

    int deleteByPrimaryKey(MerchantDayRemainingDtoKey key);

    int insert(MerchantDayRemainingDto record);

    int insertSelective(MerchantDayRemainingDto record);

    List<MerchantDayRemainingDto> selectByExample(MerchantDayRemainingDtoExample example);

    MerchantDayRemainingDto selectByPrimaryKey(MerchantDayRemainingDtoKey key);

    int updateByExampleSelective(@Param("record") MerchantDayRemainingDto record, @Param("example") MerchantDayRemainingDtoExample example);

    int updateByExample(@Param("record") MerchantDayRemainingDto record, @Param("example") MerchantDayRemainingDtoExample example);

    int updateByPrimaryKeySelective(MerchantDayRemainingDto record);

    int updateByPrimaryKey(MerchantDayRemainingDto record);
    
    
    //add by my 
    
    /**
     * @param accountId
     * @param maxDay
     * @return
     */
    List<MerchantDayRemainingDto> selectByAccountId(@Param("accountId") Long accountId,@Param("maxDay") Integer maxDay);//TODO sql 未写
    /**
     * @param accountId
     * @param maxDay
     * @return
     */
    List<MerchantDayRemainingDto> countByAccountId(@Param("accountId") Long accountId,@Param("maxDay") Integer maxDay);
    /**
     * @param accountId
     * @param maxDay
     * @param at
     * @return
     */
    MerchantDayRemainingDto countByAccountIdAndAt(@Param("accountId") Long accountId,@Param("maxDay") Integer maxDay,@Param("at") String at);
    /**
     * @param accountId
     * @param maxDay
     * @param at
     * @return
     */
    List<MerchantDayRemainingDto> selectByAccountIdAndPayType(@Param("accountId") Long accountId,@Param("maxDay") Integer maxDay,@Param("at") String at);
    /**
     * @param record
     * @return
     */
    int saveMerchantDayRemainingDto(MerchantDayRemainingDto record);
    
    int updateByPrimaryKeyAdd(MerchantDayRemainingDto record);//TODO 暂未使用
}