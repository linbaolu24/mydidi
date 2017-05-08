package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.user.users.domain.UserExperienceDto;
import cn.com.didi.user.users.domain.UserExperienceDtoExample;
import cn.com.didi.user.users.domain.UserExperienceDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExperienceDtoMapper {
    int countByExample(UserExperienceDtoExample example);

    int deleteByExample(UserExperienceDtoExample example);

    int deleteByPrimaryKey(UserExperienceDtoKey key);

    int insert(UserExperienceDto record);

    int insertSelective(UserExperienceDto record);

    List<UserExperienceDto> selectByExample(UserExperienceDtoExample example);

    UserExperienceDto selectByPrimaryKey(UserExperienceDtoKey key);

    int updateByExampleSelective(@Param("record") UserExperienceDto record, @Param("example") UserExperienceDtoExample example);

    int updateByExample(@Param("record") UserExperienceDto record, @Param("example") UserExperienceDtoExample example);

    int updateByPrimaryKeySelective(UserExperienceDto record);

    int updateByPrimaryKey(UserExperienceDto record);
    //add by my
    int insertIgnoreSelective(UserExperienceDto record);
}