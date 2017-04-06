package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.core.shape.IPoint;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantAreaDtoExample;
import cn.com.didi.user.users.domain.MerchantAreaDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantAreaDtoMapper {
    int countByExample(MerchantAreaDtoExample example);

    int deleteByExample(MerchantAreaDtoExample example);

    int deleteByPrimaryKey(MerchantAreaDtoKey key);

    int insert(MerchantAreaDto record);

    int insertSelective(MerchantAreaDto record);

    List<MerchantAreaDto> selectByExample(MerchantAreaDtoExample example);

    MerchantAreaDto selectByPrimaryKey(MerchantAreaDtoKey key);

    int updateByExampleSelective(@Param("record") MerchantAreaDto record, @Param("example") MerchantAreaDtoExample example);

    int updateByExample(@Param("record") MerchantAreaDto record, @Param("example") MerchantAreaDtoExample example);

    int updateByPrimaryKeySelective(MerchantAreaDto record);

    int updateByPrimaryKey(MerchantAreaDto record);
    /**
     * @param key
     * @return
     */
    List<MerchantAreaDto> selectMerchantArea(MerchantAreaDtoKey key);
    
    
    /**查找该范围的商铺
     * @param leftDown
     * @param rightTop
     * @param slsId
     * @return
     */
    List<MerchantAreaDto> selectPoints(@Param("leftDown") MerchantAreaDto leftDown, @Param("rightTop") MerchantAreaDto rightTop,@Param("slsId") Integer slsId);
    /**选择包含点的商铺 X 代表经度 Y代表纬度*/
    List<MerchantAreaDto> selectAreas(@Param("point") IPoint leftDown,@Param("slsId") Integer slsId);
}