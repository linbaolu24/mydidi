package cn.com.didi.thirdExt.produce.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.property.IEnvironment;
import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.NumberUtil;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.util.EnvConstants;
import cn.com.didi.user.ad.domain.AdDescDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;

public class AppEnvImpl implements IAppEnv {
	private IEnvironment appEnviroment;
	private String orderTransJson;
	private String depositeAliNotifyUrl;
	private URI appPayUri;
	private String depositeWechatNotifyUrl;
	private String ipAdress;
	private String  wechatPayNotifyUrl;
	private String wechatMchId;
	private String wechatPassword;
	private Resource keyStoreResource;
	private String wechatAppSignedkey;
	private Integer mfxfSlsId;
	private String ryAppSecret;
	public String ryAppKey;
	private String ryBAppSecret;
	
	private String ryBAppKey;
	private String passWchatNotifySign;
	private String passAliNotifySign;
	protected URI wechatTransFer;
	{
		try {
			appPayUri=new URI("https://api.mch.weixin.qq.com/pay/unifiedorder");
			wechatTransFer=new URI("https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers");
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	public void init(){
		
	}
	
	@Override
	public String getOrderTransJson() {
		return orderTransJson;
	}

	public void setOrderTransJson(String orderTransJson) {
		this.orderTransJson = orderTransJson;
	}
	public IEnvironment getAppEnviroment() {
		return appEnviroment;
	}
	public void setAppEnviroment(IEnvironment appEnviroment) {
		this.appEnviroment = appEnviroment;
	}
	@Override
	public int getDeposite() {
		String value = appEnviroment.getProperty(EnvConstants.DEPOSITE_MONEY);
		if (StringUtils.isEmpty(value)) {
			return 120;
		}
		return Integer.parseInt(value);
	}
	@Override
	public void changeDeposite(int newDeposite) {
		appEnviroment.changePropertyValue(EnvConstants.DEPOSITE_MONEY, String.valueOf(newDeposite));
	}
	
	@Override
	public String getDepositeAliNotifyUrl() {
		return depositeAliNotifyUrl;
	}
	public void setDepositeAliNotifyUrl(String depositeAliNotifyUrl) {
		this.depositeAliNotifyUrl = depositeAliNotifyUrl;
	}
	@Override
	public Long getSystemAccountId() {
		return 0L;
	}
	@Override
	public Integer getMrmfDayInterval() {
		String property=appEnviroment.getProperty(EnvConstants.MRMF_DAY_INTERVAL);
		if(StringUtils.isEmpty(property)){
			return -1;
		}
		return Integer.parseInt(property);
	}
	@Override
	public boolean isAdRtStatistic() {
		return false;
	}
	@Override
	public VipDescrptionDto getVipDesc(Integer slsId) {
		VipDescrptionDto desc=new VipDescrptionDto();
		desc.setVipName("嘀嘀洗发会员");
		desc.setTitle("免费洗吹");
		desc.setCondition("每月可免费洗吹10次");
		desc.setUsable("会员权利截至#{endTime}");
		return desc;
	}
	@Override
	public String getRegVipDesc(Integer slsId) {
		String desc= appEnviroment.getProperty(EnvConstants.VIP_DESC);
		if(desc!=null&&desc.indexOf("#{vipFee}")!=-1){
			int getDeposite=getDeposite();
			desc=desc.replace("#{vipFee}", NumberUtil.intToDecimal2(getDeposite));
		}
		return desc;
		
	}
	@Override
	public Integer getMfxfSlsId() {
		if(mfxfSlsId!=null){
			return mfxfSlsId;
		}
		mfxfSlsId= Integer.parseInt(appEnviroment.getProperty(EnvConstants.MRMF_SLS_ID));
		return mfxfSlsId;
	}
	@Override
	public List<AdDescDto> listMrmfAds() {
		String str=appEnviroment.getProperty(EnvConstants.MRMF_ADV);
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return JSON.parseArray(str, AdDescDto.class);
	}
	@Override
	public boolean canSendSmsToAllUser() {
		String value=appEnviroment.getProperty(EnvConstants.CAN_SEND_SMS);
		return StringUtils.isEmpty(value)||"Y".equalsIgnoreCase(value);
	}
	@Override
	public Integer getMrmfCountByMonth() {
		return 10;
	}
	@Override
	public String getWechatValidatorToken() {
		return "scurry30";
	}
	@Override
	public int getLockedWait() {
		return 5000;
	}
	
	
	
	
	
	
	@Override
	public String getDepositeWechatNotifyUrl() {
		return depositeWechatNotifyUrl;
	}
	@Override
	public String getAppName() {
		return "嘀嘀服务";
	}

	@Override
	public String getWechatAppId() {
		return "wxf0f6836240fdaf3e";
	}

	@Override
	public String getWechatMchId() {
		return wechatMchId;
	}

	@Override
	public String getIpAdress() {
		return ipAdress;
	}

	@Override
	public String getWechatPayNotifyUrl() {
		return wechatPayNotifyUrl;
	}

	@Override
	public String getWechatTradeType() {
		return "APP";
	}

	@Override
	public String getWechatCharSet() {
		return Constans.CHARSET_UTF_8;
	}

	@Override
	public String getWechatAppSignedkey() {
		return wechatAppSignedkey;
	}

	@Override
	public URI getWechatAppPayURI() {
		return appPayUri;
	}

	@Override
	public InputStream getWechatKeyStroe() throws IOException {
		if(keyStoreResource!=null){
				return keyStoreResource.getInputStream();
			
		}
		return null;
	}

	@Override
	public String getWechatPassword() {
		return wechatPassword;
	}

	@Override
	public URI getWechatTransferURI() {
		return wechatTransFer;
	}

	@Override
	public String getWechatAppSecret() {
		return "40956ed28413514d4a4dfee2ebb8471a";
	}

	@Override
	public String getWechatOpenAppId() {
		return "wxe29a2f519cf39295";
	}

	@Override
	public String getWechatOpenSecret() {
	
		return "9b7b4ee13fd9e9ee82f3ad55f585db47";
	}

	public void setDepositeWechatNotifyUrl(String depositeWechatNotifyUrl) {
		this.depositeWechatNotifyUrl = depositeWechatNotifyUrl;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public void setWechatPayNotifyUrl(String wechatPayNotifyUrl) {
		this.wechatPayNotifyUrl = wechatPayNotifyUrl;
	}

	public void setWechatMchId(String wechatMchId) {
		this.wechatMchId = wechatMchId;
	}

	public void setWechatPassword(String wechatPassword) {
		this.wechatPassword = wechatPassword;
	}

	public Resource getKeyStoreResource() {
		return keyStoreResource;
	}

	public void setKeyStoreResource(Resource keyStoreResource) {
		this.keyStoreResource = keyStoreResource;
	}

	public void setWechatAppSignedkey(String wechatAppSignedkey) {
		this.wechatAppSignedkey = wechatAppSignedkey;
	}

	public void setMfxfSlsId(Integer mrmfSlsId) {
		this.mfxfSlsId = mrmfSlsId;
	}


	@Override
	public boolean canReflashUserLinked() {
		String value=appEnviroment.getProperty(EnvConstants.CAN_REFLASH_USER_LINKED);
		return StringUtils.isEmpty(value)||"Y".equalsIgnoreCase(value);
	}

	public String getRyAppSecret() {
		return ryAppSecret;
	}

	public void setRyAppSecret(String ryAppSecret) {
		this.ryAppSecret = ryAppSecret;
	}

	public String getRyAppKey() {
		return ryAppKey;
	}

	public void setRyAppKey(String ryAppKey) {
		this.ryAppKey = ryAppKey;
	}

	public String getRyBAppSecret() {
		return ryBAppSecret;
	}

	public void setRyBAppSecret(String ryBAppSecret) {
		this.ryBAppSecret = ryBAppSecret;
	}

	public String getRyBAppKey() {
		return ryBAppKey;
	}

	public void setRyBAppKey(String ryBAppKey) {
		this.ryBAppKey = ryBAppKey;
	}

	@Override
	public boolean passWechatNotifySign() {
		return booleanStr(getPassWchatNotifySign());
	}

	@Override
	public boolean passAliNotifySign() {
		return booleanStr(getPassAliNotifySign());
	}

	public String getPassWchatNotifySign() {
		return passWchatNotifySign;
	}

	public void setPassWchatNotifySign(String passWchatNotifySign) {
		this.passWchatNotifySign = passWchatNotifySign;
	}

	public String getPassAliNotifySign() {
		return passAliNotifySign;
	}

	public void setPassAliNotifySign(String passAliNotifySign) {
		this.passAliNotifySign = passAliNotifySign;
	}
	
	public boolean booleanStr(String value){
		return StringUtils.isEmpty(value)||"Y".equalsIgnoreCase(value);
	}

	

}
