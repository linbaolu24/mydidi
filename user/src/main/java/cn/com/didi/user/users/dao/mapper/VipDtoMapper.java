package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.user.users.domain.VipDto;
import cn.com.didi.user.users.domain.VipDtoExample;
import cn.com.didi.user.users.domain.VipDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VipDtoMapper {
    int countByExample(VipDtoExample example);

    int deleteByExample(VipDtoExample example);

    int deleteByPrimaryKey(VipDtoKey key);

    int insert(VipDto record);

    int insertSelective(VipDto record);

    List<VipDto> selectByExample(VipDtoExample example);

    VipDto selectByPrimaryKey(VipDtoKey key);

    int updateByExampleSelective(@Param("record") VipDto record, @Param("example") VipDtoExample example);

    int updateByExample(@Param("record") VipDto record, @Param("example") VipDtoExample example);

    int updateByPrimaryKeySelective(VipDto record);

    int updateByPrimaryKey(VipDto record);
}