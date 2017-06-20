package cn.com.didi.order.trade.service;

import java.util.Map;

import cn.com.didi.order.trade.domain.UserWechatDto;

public interface IWechatBaseService {
	public void suiteTicketPostData(Map<String, String> map, String postData, String suiteId);

	public String valiadate(String signature, String timestamp, String nonce, String echostr);
	/**
	 * @param code
	 * @return
	 */
	public UserWechatDto getUserInfo(Long accountId,String code);
	/**
	 * @param postData
	 */
	public void subscribe (String postData);
	/**
	 * @return
	 */
	public String getAccessToken(String type);
}
