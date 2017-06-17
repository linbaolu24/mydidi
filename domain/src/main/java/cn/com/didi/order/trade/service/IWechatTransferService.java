package cn.com.didi.order.trade.service;

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
	public WechatUserInfo getUser(String accessToken,String openId);
	/**
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public WechatUserInfo getUserFromCode(String appid,String secret,String code);
}
