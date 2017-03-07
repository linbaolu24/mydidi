package cn.com.didi.user.system.dao.mapper;

import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.domain.CodeDictionaryDtoExample;
import cn.com.didi.user.system.domain.CodeDictionaryDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CodeDictionaryDtoMapper {
    int countByExample(CodeDictionaryDtoExample example);

    int deleteByExample(CodeDictionaryDtoExample example);

    int deleteByPrimaryKey(CodeDictionaryDtoKey key);

    int insert(CodeDictionaryDto record);

    int insertSelective(CodeDictionaryDto record);

    List<CodeDictionaryDto> selectByExample(CodeDictionaryDtoExample example);

    CodeDictionaryDto selectByPrimaryKey(CodeDictionaryDtoKey key);

    int updateByExampleSelective(@Param("record") CodeDictionaryDto record, @Param("example") CodeDictionaryDtoExample example);

    int updateByExample(@Param("record") CodeDictionaryDto record, @Param("example") CodeDictionaryDtoExample example);

    int updateByPrimaryKeySelective(CodeDictionaryDto record);

    int updateByPrimaryKey(CodeDictionaryDto record);
}