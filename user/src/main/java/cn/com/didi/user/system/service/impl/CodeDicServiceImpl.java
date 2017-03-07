package cn.com.didi.user.system.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.user.system.dao.mapper.CodeDictionaryDtoMapper;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.domain.CodeDictionaryDtoExample;
import cn.com.didi.user.system.service.ICodeDicService;
@Service
public class CodeDicServiceImpl implements ICodeDicService{
	protected CodeDictionaryDtoMapper codeDicMapper;
	@Override
	public List<CodeDictionaryDto> selectCodes(String cname) {
		if(StringUtils.isEmpty(cname)){
			return null;
		}
		CodeDictionaryDtoExample example=new CodeDictionaryDtoExample();
		CodeDictionaryDtoExample.Criteria cri=example.createCriteria();
		cri.andCnameEqualTo(cname);
		return codeDicMapper.selectByExample(example);
	}

}
