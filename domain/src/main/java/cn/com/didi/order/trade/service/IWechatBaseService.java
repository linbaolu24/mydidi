package cn.com.didi.order.trade.service;

import java.util.Map;

import cn.com.didi.domain.domains.wechat.AccessTokenDto;
import cn.com.didi.domain.util.WechatEnum;
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
	 * @param code
	 * @return
	 */
	public UserWechatDto getUserInfo(Long accountId,String code,WechatEnum type);

	/**订阅公众号
	 * @param type 表示微信公众号类型
	 * @param postData
	 */
	public void subscribe (WechatEnum type,Map<String,Object> sub);
	/**
	 * @return
	 */
	public AccessTokenDto getAccessToken(WechatEnum type);
	/**
	 * @param xml
	 * @return
	 */
	public String message(String xml,WechatEnum type);
}
