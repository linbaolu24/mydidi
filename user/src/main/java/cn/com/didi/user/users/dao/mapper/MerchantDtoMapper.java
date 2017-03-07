package cn.com.didi.user.users.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantDtoExample;

public interface MerchantDtoMapper {
    int countByExample(MerchantDtoExample example);

    int deleteByExample(MerchantDtoExample example);

    int deleteByPrimaryKey(Long accountId);

    int insert(MerchantDto record);

    int insertSelective(MerchantDto record);

    List<MerchantDto> selectByExample(MerchantDtoExample example);

    MerchantDto selectByPrimaryKey(Long accountId);

    int updateByExampleSelective(@Param("record") MerchantDto record, @Param("example") MerchantDtoExample example);

    int updateByExample(@Param("record") MerchantDto record, @Param("example") MerchantDtoExample example);

    int updateByPrimaryKeySelective(MerchantDto record);

    int updateByPrimaryKey(MerchantDto record);
    /**
     * @param interval
     * @return
     */
    public List<MerchantDto> selectMerchants(@Param("time") TimeInterval interval,RowBounds rowBounds);
    /**
explain select * from  (merchant_area ma STRAIGHT_JOIN merchant_service ms on ma.account_id=ms.account_id)  STRAIGHT_JOIN merchant me on ma.account_id=me.account_Id where me.state='0'    and ma.area_code=''
and ms.sls_Id=1*/
    public List<MerchantDto> selectMatched(@Param("areaCode")String areaCode,@Param("slsId") Integer slsId);
}