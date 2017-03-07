package cn.com.didi.user.register.dao.mapper;

import cn.com.didi.user.register.domain.PhoneVcDto;
import cn.com.didi.user.register.domain.PhoneVcDtoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PhoneVcDtoMapper {
    int countByExample(PhoneVcDtoExample example);

    int deleteByExample(PhoneVcDtoExample example);

    int deleteByPrimaryKey(Long vcId);

    int insert(PhoneVcDto record);

    int insertSelective(PhoneVcDto record);

    List<PhoneVcDto> selectByExample(PhoneVcDtoExample example);

    PhoneVcDto selectByPrimaryKey(Long vcId);

    int updateByExampleSelective(@Param("record") PhoneVcDto record, @Param("example") PhoneVcDtoExample example);

    int updateByExample(@Param("record") PhoneVcDto record, @Param("example") PhoneVcDtoExample example);

    int updateByPrimaryKeySelective(PhoneVcDto record);

    int updateByPrimaryKey(PhoneVcDto record);
}