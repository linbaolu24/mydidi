package cn.com.didi.order.trade.service;

import cn.com.didi.core.tx.TranscationalCallBack;
import cn.com.didi.domain.domains.wechat.WechatUserInfo;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.domain.UserWechatDto;
import cn.com.didi.order.trade.domain.UserWechatOpenIdDto;

public interface IWechatUserService {
	/**
	 * 
	 */
	public UserWechatDto saveWithAccountId(Long accountId,WechatUserInfo info,String appid,WechatEnum type );
	/**
	 * 如果accountId不存在的情况
	 * @param accountId
	 * @param info
	 * @param appid
	 * @param type
	 */
	public UserWechatDto saveWithOutAccountId(WechatUserInfo info,String appid,WechatEnum type,TranscationalCallBack<WechatUserInfo> callBack);
	/**
	 * @return
	 */
	public UserWechatDto getWechatDto(Long accountId);
	public UserWechatOpenIdDto getWechatDto(String unionId,WechatEnum type);
	/**
	 * 包含该appid的openid
	 * @return 
	 */
	public UserWechatDto getWechatDto(Long accountId,String appid);
	
}
