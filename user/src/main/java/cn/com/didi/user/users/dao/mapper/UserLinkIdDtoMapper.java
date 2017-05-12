package cn.com.didi.user.users.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.domain.UserLinkIdDtoExample;

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
    
    // add by my
    int updateId(UserLinkIdDto record);
    int updateWechatAndAliPayLinkedId(UserLinkIdDto record);   
    
    /**
     * @param role
     * @param pageBouns
     * @return
     */
    List<IReciverDto> selectRecivers(@Param("role") String role,@Param("businessCategory") String businessCategory,RowBounds pageBouns);
}