package cn.com.didi.thirdExt.produce.impl;

import java.io.InputStream;
import java.net.URI;

import org.apache.commons.lang.StringUtils;

import cn.com.didi.core.property.IEnvironment;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.thirdExt.util.EnvConstants;

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
	

	

}
