package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserDtoMapper {
    int countByExample(UserDtoExample example);

    int deleteByExample(UserDtoExample example);

    int deleteByPrimaryKey(Long accountId);

    int insert(UserDto record);

    int insertSelective(UserDto record);

    List<UserDto> selectByExample(UserDtoExample example);

    UserDto selectByPrimaryKey(Long accountId);

    int updateByExampleSelective(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByExample(@Param("record") UserDto record, @Param("example") UserDtoExample example);

    int updateByPrimaryKeySelective(UserDto record);

    int updateByPrimaryKey(UserDto record);
    /**TODO sql未写
     * @param interval
     * @return
     */
    List<UserDto>  selectUsers(@Param("time") TimeInterval interval,RowBounds rowBounds);
    /**
     * 选择平台用户 TODO sql未写
     * @param interval
     * @return
     */
    List<UserDto>  selectPlatformUsers(@Param("time") TimeInterval interval,RowBounds rowBounds);
}