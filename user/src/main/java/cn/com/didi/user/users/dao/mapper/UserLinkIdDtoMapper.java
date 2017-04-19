package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.domain.UserLinkIdDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserLinkIdDtoMapper {
    int countByExample(UserLinkIdDtoExample example);

    int deleteByExample(UserLinkIdDtoExample example);

    int deleteByPrimaryKey(Long accountId);

    int insert(UserLinkIdDto record);

    int insertSelective(UserLinkIdDto record);

    List<UserLinkIdDto> selectByExample(UserLinkIdDtoExample example);

    UserLinkIdDto selectByPrimaryKey(Long accountId);

    int updateByExampleSelective(@Param("record") UserLinkIdDto record, @Param("example") UserLinkIdDtoExample example);

    int updateByExample(@Param("record") UserLinkIdDto record, @Param("example") UserLinkIdDtoExample example);

    int updateByPrimaryKeySelective(UserLinkIdDto record);

    int updateByPrimaryKey(UserLinkIdDto record);
    
    int updateId(UserLinkIdDto record);
    int updateWechatAndAliPayLinkedId(UserLinkIdDto record);   
}