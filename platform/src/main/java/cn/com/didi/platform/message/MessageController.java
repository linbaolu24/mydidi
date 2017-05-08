package cn.com.didi.platform.message;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.query.ResultExt;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.State;
import cn.com.didi.user.message.domain.TMessageDto;
import cn.com.didi.user.message.service.IMessageService;

@RestController
public class MessageController {
	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MessageController.class);
	@Resource
	protected IMessageService messageService;

	@RequestMapping(value = "/platform/message/list", method = RequestMethod.POST)
	public IResult listFls(@RequestBody TimeInterval interval) {
		IPage<TMessageDto> page = messageService.selectPage(interval);
		return ResultExt.build(page);
	}

	@RequestMapping(value = "/platform/message/add", method = RequestMethod.POST)
	public IResult listFls(@RequestBody TMessageDto messageDto) {
		messageDto.setState(State.VALID.getState());
		messageDto.setMesageType("0");
		messageDto.setCreateTime(new Date());
		messageService.addMessage(messageDto);
		return ResultFactory.success();
	}

	@RequestMapping(value = "/platform/message/onOff", method = RequestMethod.POST)
	public IResult onOffMerchant(@RequestBody List<IdStateDto> idStateList) {
		if (CollectionUtils.isEmpty(idStateList)) {
			return ResultFactory.success();
		}
		messageService.onOff(idStateList);
		return ResultFactory.success();
	}
}
