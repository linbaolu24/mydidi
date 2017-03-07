package cn.com.didi.message.shortmessage.service;

import cn.com.didi.core.property.IResult;
import cn.com.didi.message.shortmessage.domain.ShortMessgaeDto;

public interface IShortMessageService {
	/**
	 * @param dto
	 * @return
	 */
	public IResult<Void> sendMessage(ShortMessgaeDto dto);
}
