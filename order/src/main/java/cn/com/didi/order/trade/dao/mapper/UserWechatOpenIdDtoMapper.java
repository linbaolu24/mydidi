package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.UserWechatOpenIdDto;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDtoExample;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWechatOpenIdDtoMapper {
    int countByExample(UserWechatOpenIdDtoExample example);

    int deleteByExample(UserWechatOpenIdDtoExample example);

    int deleteByPrimaryKey(UserWechatOpenIdDtoKey key);

    int insert(UserWechatOpenIdDto record);

    int insertSelective(UserWechatOpenIdDto record);

    List<UserWechatOpenIdDto> selectByExample(UserWechatOpenIdDtoExample example);

    UserWechatOpenIdDto selectByPrimaryKey(UserWechatOpenIdDtoKey key);

    int updateByExampleSelective(@Param("record") UserWechatOpenIdDto record, @Param("example") UserWechatOpenIdDtoExample example);

    int updateByExample(@Param("record") UserWechatOpenIdDto record, @Param("example") UserWechatOpenIdDtoExample example);

    int updateByPrimaryKeySelective(UserWechatOpenIdDto record);

    int updateByPrimaryKey(UserWechatOpenIdDto record);
    
    
    /*add by my*/
    /**
     * @param unionId
     * @param accountId
     * @return
     */
    int updateAccountId(@Param("unionid") String unionId,@Param("accountId") Long accountId);
}