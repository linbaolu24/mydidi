package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.MerchantRemainingDto;
import cn.com.didi.order.trade.domain.MerchantRemainingDtoExample;
import cn.com.didi.order.trade.domain.MerchantRemainingDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantRemainingDtoMapper {
    int countByExample(MerchantRemainingDtoExample example);

    int deleteByExample(MerchantRemainingDtoExample example);

    int deleteByPrimaryKey(MerchantRemainingDtoKey key);

    int insert(MerchantRemainingDto record);

    int insertSelective(MerchantRemainingDto record);

    List<MerchantRemainingDto> selectByExample(MerchantRemainingDtoExample example);

    MerchantRemainingDto selectByPrimaryKey(MerchantRemainingDtoKey key);

    int updateByExampleSelective(@Param("record") MerchantRemainingDto record, @Param("example") MerchantRemainingDtoExample example);

    int updateByExample(@Param("record") MerchantRemainingDto record, @Param("example") MerchantRemainingDtoExample example);

    int updateByPrimaryKeySelective(MerchantRemainingDto record);

    int updateByPrimaryKey(MerchantRemainingDto record);
    
    int updateAddRemaining(MerchantRemainingDto meDto);
}