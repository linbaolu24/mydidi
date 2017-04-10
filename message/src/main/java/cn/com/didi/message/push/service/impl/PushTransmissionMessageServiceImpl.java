package cn.com.didi.message.push.service.impl;

import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import cn.com.didi.domain.domains.MessageDto;

public class PushTransmissionMessageServiceImpl extends PushMessageServiceImpl{
	@SuppressWarnings("deprecation")
	public AbstractTemplate notificationTemplateDemo(MessageDto context) {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent(context.getContent());
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
}
