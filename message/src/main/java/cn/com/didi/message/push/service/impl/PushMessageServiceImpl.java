package cn.com.didi.message.push.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.AbstractTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.MessageDto;
import cn.com.didi.message.push.service.IPushMessageService;

public class PushMessageServiceImpl implements IPushMessageService {
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getOfflineExpireTime() {
		return offlineExpireTime;
	}

	public void setOfflineExpireTime(int offlineExpireTime) {
		this.offlineExpireTime = offlineExpireTime;
	}

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PushMessageServiceImpl.class);
	protected String appId;// = "IRUelUtd5A93EXHPhijUp1";
	protected String appKey;// = "HTSpjOhFlr73IpnzJaZfY6";
	protected String masterSecret;// = "XqXFmluserA8i31KVdTmL2";
	protected String host = "http://sdk.open.api.igexin.com/apiex.htm";
	protected IGtPush push;
	protected int offlineExpireTime = 24 * 1000 * 3600;

	public void init() {
		push = new IGtPush(host, appKey, masterSecret);
	}

	@Override
	public IResult<Void> push(IReciverDto reciver, MessageDto messageContext) {
		LOGGER.debug("消息推送  {}   ",messageContext);
		if (reciver == null) {
			return ResultFactory.success();
		}
		SingleMessage message = new SingleMessage();
		AbstractTemplate template = notificationTemplateDemo(messageContext);
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(offlineExpireTime);
		Target target = new Target();
		target.setAppId(appId);
		target.setClientId(reciver.getReciveId());
		IPushResult ret = null;
		try {
			ret = push.pushMessageToSingle(message, target);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (ret != null) {
			LOGGER.debug("个推返回为:{}", ret.getResponse());
		}
		return ResultFactory.success();
	}

	@Override
	public IResult<Void> push(List<IReciverDto> reciver, MessageDto messageContext) {
		if (CollectionUtils.isEmpty(reciver)) {
			return ResultFactory.success();
		}
		ListMessage message = new ListMessage();
		AbstractTemplate template = notificationTemplateDemo(messageContext);
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(offlineExpireTime);
		List<Target> target = new ArrayList(reciver.size());
		Target ta;
		for (IReciverDto one : reciver) {
			ta = new Target();
			ta.setAppId(appId);
			ta.setClientId(one.getReciveId());
			target.add(ta);
		}
		String taskId = null;
		IPushResult ret = null;
		try {
			taskId = push.getContentId(message);
			ret = push.pushMessageToList(taskId, target);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		if (ret != null) {
			LOGGER.debug("个推返回为:{}", ret.getResponse());
		}
		return ResultFactory.success();
	}

	@SuppressWarnings("deprecation")
	public AbstractTemplate notificationTemplateDemo(MessageDto context) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		// 设置通知栏标题与内容
		//template.setTitle("请输入通知栏标题");
		//template.setText("请输入通知栏内容");
		template.setTitle(context.getTitle());
		template.setText(context.getText());
		// 配置通知栏图标
		template.setLogo("icon.png");
		// 配置通知栏网络图标，填写图标URL地址
		template.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		template.setIsRing(true);
		template.setIsVibrate(true);
		template.setIsClearable(true);
		// 设置打开的网址地址
		template.setTransmissionType(2);
		template.setTransmissionContent(context.getContent());
		return template;
	}
	

}
