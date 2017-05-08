package cn.com.didi.user.message.service;

import java.util.List;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.message.domain.TMessageDto;

/**
 * @author xlm
 *
 */
public interface IMessageService {
	/**
	 * @param interval
	 * @return
	 */
	public IPage<TMessageDto> selectPage(TimeInterval interval);
	/**
	 * @param messageDto
	 */
	public void addMessage(TMessageDto messageDto);
	
	/**
	 * @param messageDto
	 */
	public void onOff(List<IdStateDto> messageDto);
	public void onOff(Long id,String state);
	
	//public void List<TMessageDto> selectMessage();
}
