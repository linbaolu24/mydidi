package cn.com.didi.thirdExt.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.core.excpetion.HttpException;
import cn.com.didi.core.message.MessageFactory;
import cn.com.didi.domain.util.DomainMessageConstans;

public class Http200Hanle implements IHttpHandler {
	private static final Logger LOGGER=LoggerFactory.getLogger(Http200Hanle.class);
	public IHttpHandler getWarpperHandle() {
		return warpperHandle;
	}

	public void setWarpperHandle(IHttpHandler warpperHandle) {
		this.warpperHandle = warpperHandle;
	}

	public Http200Hanle(IHttpHandler warpperHandle) {
		super();
		this.warpperHandle = warpperHandle;
	}

	public Http200Hanle() {
		super();
	}

	private IHttpHandler warpperHandle;

	@Override
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
		return warpperHandle.preForRequest(post);
	}

	@Override
	public void forResponse(HttpResponse response) {
		if (response.getStatusLine().getStatusCode() == 200) {
			warpperHandle.forResponse(response);
		} else {
			LOGGER.error(response.getStatusLine().toString());
			throw new HttpException(MessageFactory.creatMessage(DomainMessageConstans.CODE_SYSTEM_HTTP_STATU_NOT_200),
					response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
		}
	}

}
