package cn.com.didi.domain.domains.wechat;

import cn.com.didi.domain.domains.AppNormalDto;

/**
 * @author xlm
 *
 */
public class WechatAppDto extends AppNormalDto{
	/**
	 * 商户号id
	 */
	private String mchid;
	/**
	 * 商户签名key
	 */
	private String signKey;
	private String validatorToken;

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

	public String getSignKey() {
		return signKey;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getValidatorToken() {
		return validatorToken;
	}

	public void setValidatorToken(String validatorToken) {
		this.validatorToken = validatorToken;
	}
	
}
