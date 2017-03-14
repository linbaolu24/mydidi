package cn.com.didi.message.push.service;

import java.util.List;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.MessageDto;

/**
 * @author xlm
 *
 */
public interface IPushMessageService {
	public IResult<Void> push(IReciverDto reciver,MessageDto message);
	public IResult<Void> push(List<IReciverDto> reciver,MessageDto message);
}
