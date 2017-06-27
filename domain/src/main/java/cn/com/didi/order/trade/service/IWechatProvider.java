package cn.com.didi.order.trade.service;

import cn.com.didi.domain.domains.wechat.WechatAppDto;
import cn.com.didi.domain.util.WechatEnum;

public interface IWechatProvider {
	/**
	 * @param typeEnums
	 * @return
	 */
	public String getAppId(WechatEnum typeEnums);
	/**
	 * @param typeEnums
	 * @return
	 */
	public String getAppSecret(WechatEnum typeEnums);
	/**
	 * @param typeEnums
	 * @return
	 */
	public String getMchId(WechatEnum  typeEnums );
	/**
	 * @param typeEnums
	 * @return
	 */
	public String getAppSignKey(WechatEnum  typeEnums );
	/**
	 * @param typeEnums
	 * @return
	 */
	public WechatAppDto getAppConfig(WechatEnum  typeEnums );
}
