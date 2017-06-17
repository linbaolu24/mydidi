package cn.com.didi.thirdExt.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;

public interface IHttpHandler {
	/**
	 * @param post
	 */
	public boolean preForRequest(HttpEntityEnclosingRequestBase post);
	/**
	 * @param post
	 */
	public void forResponse(HttpResponse post);
	/**
	 * @param requst
	 */
	default public boolean preForRequestGet(HttpUriRequest requst){
		return true;
	}
}
