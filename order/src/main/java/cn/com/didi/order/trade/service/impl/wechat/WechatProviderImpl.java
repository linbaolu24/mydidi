package cn.com.didi.order.trade.service.impl.wechat;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.domain.domains.wechat.WechatAppDto;
import cn.com.didi.domain.util.WechatEnum;
import cn.com.didi.order.trade.service.IWechatProvider;
import cn.com.didi.thirdExt.produce.IAppEnv;
@Service
public class WechatProviderImpl implements IWechatProvider{
	@Resource
	protected IAppEnv myAppEnvImpl;
	@Override
	public String getAppId(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return myAppEnvImpl.getWechatAppId();
		}else if(WechatEnum.APP_C.equals(typeEnums)){
			return myAppEnvImpl.getWechatCAppId();
		}
		return myAppEnvImpl.getWechatOpenAppId();
	}

	@Override
	public String getAppSecret(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return myAppEnvImpl.getWechatAppSecret();
		}else if(WechatEnum.APP_C.equals(typeEnums)){
			return myAppEnvImpl.getWechatCAppSecret();
		}
		return myAppEnvImpl.getWechatOpenSecret();
	}

	@Override
	public String getMchId(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return myAppEnvImpl.getWechatMchId();
		}else if(WechatEnum.APP_C.equals(typeEnums)){
			return myAppEnvImpl.getWechatCMchId();
		}
		return myAppEnvImpl.getWechatOpenSecret();
	}

	@Override
	public WechatAppDto getAppConfig(WechatEnum typeEnums) {
		WechatAppDto dto=new WechatAppDto();
		dto.setMchid(getMchId(typeEnums));
		dto.setAppid(getAppId(typeEnums));
		dto.setAppSecret(getAppSecret(typeEnums));
		dto.setAppName(myAppEnvImpl.getAppName());
		dto.setSignKey(getAppSignKey(typeEnums));
		return dto;
	}

	@Override
	public String getAppSignKey(WechatEnum typeEnums) {
		if(WechatEnum.APP.equals(typeEnums)){
			return myAppEnvImpl.getWechatAppSignedkey();
		}else if(WechatEnum.APP_C.equals(typeEnums)){
			return myAppEnvImpl.getWechatAppSignedkey();
		}
		return myAppEnvImpl.getWechatAppSignedkey();
	}

}
