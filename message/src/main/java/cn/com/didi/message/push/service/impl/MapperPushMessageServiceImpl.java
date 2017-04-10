package cn.com.didi.message.push.service.impl;

import java.util.List;
import java.util.Map;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.message.push.service.IPushMessageService;

public class MapperPushMessageServiceImpl implements IPushMessageService {
	protected Map<String,IPushMessageService> mapper;
	@Override
	public IResult<Void> push(IReciverDto reciver, MessageDto message) {
		IPushMessageService temp=getPushMessage(reciver);
		return temp.push(reciver, message);
	}

	@Override
	public IResult<Void> push(List<IReciverDto> reciver, MessageDto message) {
		IPushMessageService temp=getPushMessage(reciver.get(0));
		return temp.push(reciver, message);
	}
	protected IPushMessageService getPushMessage(IReciverDto reciver){
		return mapper.get(reciver.getAccountType().getCode());
	}

	public Map<String, IPushMessageService> getMapper() {
		return mapper;
	}

	public void setMapper(Map<String, IPushMessageService> mapper) {
		this.mapper = mapper;
	}
	
}
