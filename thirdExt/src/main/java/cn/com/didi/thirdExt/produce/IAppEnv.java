package cn.com.didi.thirdExt.produce;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import cn.com.didi.user.ad.domain.AdDescDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;

public interface IAppEnv {
	public String getAppName();
	
	public String getWechatAppId();
	/**
	 * 获取微信公众号Appid
	 * @return
	 */
	public String getWechatOpenAppId();
	/**
	 * 获取微信公众号OpenSecret
	 * @return
	 */
	public String getWechatOpenSecret();
	/**微信商户号
	 * @return
	 */
	public String getWechatMchId();
	/**
	 * @return 
	 */
	public String getIpAdress();
	/**
	 * 获取微信通知Url
	 * @return
	 */
	public String getWechatPayNotifyUrl();
	/**
	 * 获取微信通知URL
	 * @return
	 */
	public String getDepositeWechatNotifyUrl();
	/**
	 * @return
	 */
	public String getWechatTradeType();
	
	/**用于app支付的字符集
	 * @return
	 */
	public String getWechatCharSet();
	
	/**
	 * @return
	 */
	public String getWechatAppSignedkey();
	
	/**
	 * 微信统一下单URL
	 * @return
	 */
	public URI getWechatAppPayURI();
	/**
	 * 微信转账URI
	 * @return
	 */
	public URI getWechatTransferURI();
	
	/**
	 * @return
	 * @throws IOException 
	 */
	public InputStream getWechatKeyStroe() throws IOException;
	/**证书的password
	 * @return
	 */
	public String getWechatPassword();
	/** 获取微信校验token
	 * @return
	 */
	public String getWechatValidatorToken();
	
	
	
	public String getWechatAppSecret();
	
	
	/**
	 * @return
	 */
	public String getOrderTransJson();
	/**
	 * @return
	 */
	public int getDeposite();
	/**
	 * 修改押金值
	 * @param newDeposite
	 */
	public void changeDeposite(int newDeposite);
	/**
	 * 获取阿里异步通知URL
	 * @return
	 */
	public String getDepositeAliNotifyUrl();
	/**
	 * 获取系统账户ID
	 * @return
	 */
	public Long getSystemAccountId();
	/**
	 * @return
	 */
	public Integer getMrmfDayInterval();
	/**
	 * @return
	 */
	public Integer getMrmfCountByMonth();
	/**
	 * 广告实时统计
	 * @return
	 */
	public boolean isAdRtStatistic();
	/**
	 * @return
	 */
	public VipDescrptionDto getVipDesc(Integer slsId);
	/**
	 * 注册VIP描述
	 * @param slsId
	 * @return
	 */
	public String getRegVipDesc(Integer slsId);
	/**
	 * 获取免费洗发slsID
	 * @return
	 */
	public Integer getMfxfSlsId();
	/**
	 * @return
	 */
	public List<AdDescDto> listMrmfAds();
	/**
	 * @return
	 */
	public boolean canSendSmsToAllUser();
	/**
	 * @return
	 */
	public int getLockedWait();
}
