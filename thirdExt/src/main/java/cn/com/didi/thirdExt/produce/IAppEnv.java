package cn.com.didi.thirdExt.produce;

import java.io.InputStream;
import java.net.URI;

public interface IAppEnv {
	public String getAppName();
	
	public String getWechatAppId();
	/**
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
	 * @return
	 */
	public String getWechatTradeType();
	
	/**
	 * @return
	 */
	public String getWechatCharSet();
	
	/**
	 * @return
	 */
	public String getWechatAppkey();
	
	/**
	 * @return
	 */
	public URI getWechatAppPayURI();
	
	/**
	 * @return
	 */
	public InputStream getWechatKeyStroe();
	/**
	 * @return
	 */
	public String getWechatPassword();
	
	/**
	 * @return
	 */
	public String getOrderTransJson();
	/**
	 * @return
	 */
	public String getAesKey();
	/**
	 * @return
	 */
	public int getDeposite();
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
}
