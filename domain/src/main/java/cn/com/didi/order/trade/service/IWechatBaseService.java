package cn.com.didi.order.trade.service;

import java.util.Map;

import cn.com.didi.domain.domains.wechat.WechatUserInfo;

public interface IWechatBaseService {
	public void suiteTicketPostData(Map<String, String> map, String postData, String suiteId);

	public String valiadate(String signature, String timestamp, String nonce, String echostr);
	/**
	 * @param code
	 * @return
	 */
	public WechatUserInfo getUserInfo(Long accountId,String code);
}
