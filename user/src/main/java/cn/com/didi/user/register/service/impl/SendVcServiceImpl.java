package cn.com.didi.user.register.service.impl;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.property.IEnvironment;
import cn.com.didi.core.property.IResult;
import cn.com.didi.message.shortmessage.domain.ShortMessgaeDto;
import cn.com.didi.message.shortmessage.service.IShortMessageService;
import cn.com.didi.thirdExt.util.EnvConstants;
import cn.com.didi.user.register.service.ISendVcService;

@Service
public class SendVcServiceImpl implements ISendVcService {
	@Resource
	protected IEnvironment didiEnvironment;
	@Resource
	protected IShortMessageService shortMessage;

	@Override
	public void send(String phone, int vc, int valid) {
		String template = didiEnvironment.getProperty(EnvConstants.VC_MESSAGE_TEMPLATE);
		if (StringUtils.isEmpty(template)) {
			return;
		}
		String message = MessageFormat.format(template, phone, String.valueOf(vc), valid);
		ShortMessgaeDto dto = new ShortMessgaeDto();
		dto.setContent(message);
		dto.setPhones(new String[] { phone });

		IResult<Void> result=shortMessage.sendMessage(dto);
		if(!result.success()){
			throw new MessageObjectException(result.getCode(),result.getMessage());
		}

	}

	@Override
	public void send(String[] phone, String message) {
		ShortMessgaeDto dto = new ShortMessgaeDto();
		dto.setContent(message);
		dto.setPhones( phone );
		IResult<Void> result=shortMessage.sendMessage(dto);
		if(!result.success()){
			throw new MessageObjectException(result.getCode(),result.getMessage());
		}

	}

}
