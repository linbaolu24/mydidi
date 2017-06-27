package cn.com.didi.order.trade.service;

import java.io.UnsupportedEncodingException;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.WechatPayCustomerReqVo;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.domains.wechat.AccessTokenDto;
import cn.com.didi.domain.domains.wechat.AccessTokenOpenIdDto;
import cn.com.didi.domain.domains.wechat.WechatUserInfo;

public interface IWechatTransferService {
	/**
	 * @param reqVo
	 * @return
	 */
	public IResult<WechatPayCustomerReturnVo> transferAppPay(WechatPayCustomerReqVo reqVo);
	/**
	 * @param req
	 * @param signKey
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public String getAppPaySign(WechatPayCustomerReqVo req,String signKey,String charSet) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException;
	/**
	 * @param req
	 * @param signKey
	 * @return 
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public String getAppTransferSign(WechatPayCustomerReqVo req,String signKey,String charSet) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException;
	/**
	 * 转账
	 * @param reqVo
	 * @return
	 */
	public IResult<WechatPayCustomerReturnVo> transferForTransferAccounts(WechatPayCustomerReqVo reqVo);
	/**
	 * @param xml
	 * @return
	 */
	public IResult<WechatPayNotifyReturnVO> parseNotifyResponse(String xml);
	/**
	 * @param vo
	 * @return
	 */
	public boolean verifySign(WechatPayNotifyReturnVO vo);

	public AccessTokenOpenIdDto getUnionAccess(String appid,String secret,String code);
	/**
	 * @param appid
	 * @param openId
	 * @return
	 */
	public WechatUserInfo getUser(String url,String accessToken,String openId);
	/**
	 * 获取UNION
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public WechatUserInfo getUserFromCode(String appid,String secret,String code);

	/**
	 * @param appId
	 * @param String
	 */
	public AccessTokenDto getAccessToken(String appId, String secret);
}
