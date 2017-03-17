package cn.com.didi.thirdExt.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author xielm
 *
 */
public interface IHttpExeService {
	/**
	 * 执行Http请求
	 * @param request
	 * @return
	 */
	HttpResponse execute(HttpUriRequest request);
}
