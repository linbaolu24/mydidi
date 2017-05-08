package cn.com.didi.user.message.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.message.dao.mapper.TMessageDtoMapper;
import cn.com.didi.user.message.domain.TMessageDto;
@Service
public class MessageServiceImpl implements IMessageService{
	@Resource
	protected TMessageDtoMapper mapper; 
	@Override
	public IPage<TMessageDto> selectPage(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<TMessageDto> list = (PageList<TMessageDto>) mapper.selectPage(interval,pageBounds);
		return new MybatisPaginatorPage<>(list);
	}

	@Override
	public void addMessage(TMessageDto messageDto) {
		mapper.insertSelective(messageDto);
	}

	@Override
	public void onOff(List<IdStateDto> messageDto) {
		for(IdStateDto one :messageDto){
			onOff(one.getId(),one.getState());
		}
		
	}

	@Override
	public void onOff(Long id, String state) {
		TMessageDto dto=new TMessageDto();
		dto.setState(state);
		dto.setMessageId(id);
		mapper.updateByPrimaryKeySelective(dto);
		
	}

}
