package cn.com.didi.thirdExt.http;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

import cn.com.didi.thirdExt.produce.IAppEnv;

public class MappedHttpExeService implements IHttpExeService{
	protected IHttpExeService wechatHttpExeService;
	protected IHttpExeService normal;
	protected IAppEnv appEnv;
	protected URI wechatTransFer;
	public void init(){
			wechatTransFer=appEnv.getWechatTransferURI();
		
	}
	@Override
	public HttpResponse execute(HttpUriRequest request) {
		if(wechatTransFer.equals(request.getURI())){
			return wechatHttpExeService.execute(request);
		}else{
			return normal.execute(request);
		}
	}
	public IHttpExeService getWechatHttpExeService() {
		return wechatHttpExeService;
	}
	public void setWechatHttpExeService(IHttpExeService wechatHttpExeService) {
		this.wechatHttpExeService = wechatHttpExeService;
	}
	public IHttpExeService getNormal() {
		return normal;
	}
	public void setNormal(IHttpExeService normal) {
		this.normal = normal;
	}
	public IAppEnv getAppEnv() {
		return appEnv;
	}
	public void setAppEnv(IAppEnv appEnv) {
		this.appEnv = appEnv;
	}

}
