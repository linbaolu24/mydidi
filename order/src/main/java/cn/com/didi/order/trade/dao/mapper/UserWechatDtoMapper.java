package cn.com.didi.order.trade.dao.mapper;

import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.domain.UserWechatDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserWechatDtoMapper {
    int countByExample(UserWechatDtoExample example);

    int deleteByExample(UserWechatDtoExample example);

    int deleteByPrimaryKey(Long accountId);

    int insert(UserWechatDto record);

    int insertSelective(UserWechatDto record);

    List<UserWechatDto> selectByExample(UserWechatDtoExample example);

    UserWechatDto selectByPrimaryKey(Long accountId);

    int updateByExampleSelective(@Param("record") UserWechatDto record, @Param("example") UserWechatDtoExample example);

    int updateByExample(@Param("record") UserWechatDto record, @Param("example") UserWechatDtoExample example);

    int updateByPrimaryKeySelective(UserWechatDto record);

    int updateByPrimaryKey(UserWechatDto record);
}