package cn.com.didi.order.trade.service.impl.wechat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.service.IWechatProvider;
import cn.com.didi.thirdExt.produce.IAppEnv;
@Service
public class WechatProviderImpl implements IWechatProvider{
	@Resource
	protected IAppEnv appEnvImpl;
	@Override
	public String getAppId(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return appEnvImpl.getWechatAppId();
		}
		return appEnvImpl.getWechatOpenAppId();
	}

	@Override
	public String getAppSecret(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return appEnvImpl.getWechatAppSecret();
		}
		return appEnvImpl.getWechatOpenSecret();
	}

}
