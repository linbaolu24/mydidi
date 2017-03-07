package cn.com.didi.message.push;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.IReciverDto;

/**
 * @author xlm
 *
 */
public interface IPushMessage {
	public IResult<Void> push(IReciverDto reciver,String message);
}
