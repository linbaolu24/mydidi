package cn.com.didi.thirdExt.produce.impl;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.property.IEnvironment;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.util.EnvConstants;
import cn.com.didi.user.ad.domain.AdDescDto;
import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.users.domain.VipDescrptionDto;

public class AppEnvImpl implements IAppEnv {
	private IEnvironment appEnviroment;
	private String orderTransJson;
	private String depositeAliNotifyUrl;
	public void init(){
		
	}
	@Override
	public String getAppName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatAppId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatMchId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIpAdress() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatPayNotifyUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatTradeType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatCharSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatAppkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URI getWechatAppPayURI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getWechatKeyStroe() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getWechatPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrderTransJson() {
		/*if(StringUtils.isEmpty(orderTransJson)){
			orderTransJson=enviroment
		}*/
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
	public String getAesKey() {
		// TODO Auto-generated method stub
		return null;
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
		return true;
	}
	@Override
	public VipDescrptionDto getVipDesc(Integer slsId) {
		VipDescrptionDto desc=new VipDescrptionDto();
		desc.setVipName("美容美发会员");
		desc.setTitle("免费洗发");
		desc.setCondition("两次免费洗发，需间隔3天以上");
		desc.setUsable("一年以内");
		return desc;
	}
	@Override
	public String getRegVipDesc(Integer slsId) {
		return appEnviroment.getProperty(EnvConstants.VIP_DESC);
	}
	@Override
	public Integer getMfxfSlsId() {
		return Integer.parseInt(appEnviroment.getProperty(EnvConstants.MRMF_SLS_ID));
	}
	@Override
	public List<AdDescDto> listMrmfAds() {
		String str=appEnviroment.getProperty(EnvConstants.MRMF_ADV);
		if(StringUtils.isEmpty(str)){
			return null;
		}
		return JSON.parseArray(str, AdDescDto.class);
	}
	

	

}
