package cn.com.didi.user.system.service;

import java.util.List;

import cn.com.didi.user.system.domain.CodeDictionaryDto;

public interface ICodeDicService {
	List<CodeDictionaryDto> selectCodes(String cname);
	CodeDictionaryDto selectCode(String cname,String code);
}
