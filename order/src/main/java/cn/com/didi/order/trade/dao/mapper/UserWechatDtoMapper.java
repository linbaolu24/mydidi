package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.domain.UserWechatDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWechatDtoMapper {
    int countByExample(UserWechatDtoExample example);

    int deleteByExample(UserWechatDtoExample example);

    int deleteByPrimaryKey(String unionid);

    int insert(UserWechatDto record);

    int insertSelective(UserWechatDto record);

    List<UserWechatDto> selectByExample(UserWechatDtoExample example);

    UserWechatDto selectByPrimaryKey(String unionid);

    int updateByExampleSelective(@Param("record") UserWechatDto record, @Param("example") UserWechatDtoExample example);

    int updateByExample(@Param("record") UserWechatDto record, @Param("example") UserWechatDtoExample example);

    int updateByPrimaryKeySelective(UserWechatDto record);

    int updateByPrimaryKey(UserWechatDto record);
    
    
    /*add by my*/
    /**
     * @param unionId
     * @param accountId
     * @return
     */
    int updateAccountId(@Param("unionid") String unionId,@Param("accountId") Long accountId);
}