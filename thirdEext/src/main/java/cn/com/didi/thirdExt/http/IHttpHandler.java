package cn.com.didi.thirdExt.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public interface IHttpHandler {
	/**
	 * @param post
	 */
	public boolean preForRequest(HttpEntityEnclosingRequestBase post);
	/**
	 * @param post
	 */
	public void forResponse(HttpResponse post);
}
