package cn.com.didi.thirdExt.produce;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.List;

import cn.com.didi.user.ad.domain.AdDescDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;

public interface IAppEnv {
	
	
	
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
	/**美容美发最大提交距离
	 * @return
	 */
	public int getMrmfCommmitMaxDistance();
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
	
	/**融云AppSecret
	 * @return
	 */
	public String getRyAppSecret ();
	/**
	 * 容云APpKEy
	 * @return
	 */
	public String getRyAppKey ();
	/**
	 * @return
	 */
	public boolean canReflashUserLinked();
	public String getRyBAppSecret();

	public String getRyBAppKey();
	/**
	 * @return
	 */
	public boolean passWechatNotifySign();
	/**
	 * @return
	 */
	public boolean passAliNotifySign();
	
	
	
	
	
	
	
	
	
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
	
	/**
	 * @return 
	 */
	public String getIpAdress();
	
	
	
	
	public String getAppName();
	/**
	 * 获取用户端APPID
	 * @return
	 */
	public String getWechatCAppId();
	/**
	 * 获取用户端AppSecret
	 * @return
	 */
	public String getWechatCAppSecret();
	/**
	 * @return 
	 */
	public String getWechatCAppSignKey();
	/**
	 * 获取用户端商户号ID
	 * @return
	 */
	public String getWechatCMchId();
	/**
	 * 获取微信C端APP name
	 * @return
	 */
	public String getWechatCAppName();
	
	
	
	/**
	 * @return
	 */
	public String getWechatAppId();
	

	/**微信商户号
	 * @return
	 */
	public String getWechatMchId();

	/**
	 * @return
	 */
	public String getWechatAppSignedkey();

	
	/**
	 * @return
	 */
	public String getWechatAppSecret();
	
	
	
	
	/**
	 * 获取微信公众号OpenSecret
	 * @return
	 */
	public String getWechatOpenSecret();
	/**
	 * 获取微信公众号Appid
	 * @return
	 */
	public String getWechatOpenAppId();

	/** 获取微信校验token
	 * @return
	 */
	public String getWechatValidatorToken();
	
	
}
