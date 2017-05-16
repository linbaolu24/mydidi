package cn.com.didi.thirdExt.produce;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import cn.com.didi.user.ad.domain.AdDescDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;

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
	public List<AdDescDto> listMrmfAds();
}
