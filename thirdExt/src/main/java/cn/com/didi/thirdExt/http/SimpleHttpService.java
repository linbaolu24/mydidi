package cn.com.didi.thirdExt.http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleHttpService implements IHttpService {
	public IHttpExeService getHttpExe() {
		return httpExe;
	}

	public void setHttpExe(IHttpExeService httpExe) {
		this.httpExe = httpExe;
	}

	private static Logger LOGGER = LoggerFactory.getLogger(SimpleHttpService.class);
	protected IHttpExeService httpExe;

	public void post(IHttpHandler handler) {
		HttpPost post = new HttpPost();
		HttpEntity entity = null;
		try {
			boolean isStop = handler.preForRequest(post);
			if (!isStop) {
				HttpResponse response = httpExe.execute(post);
				entity = response.getEntity();
				handler.forResponse(response);
			}
		} finally {
			try {
				EntityUtils.consume(entity);
			} catch (Exception e) {
				LOGGER.error("" + e.getMessage(), e);
			}
		}
	}

	/*
	 * public void get(IHttpHandler handler) { HttpGet post=new HttpGet();
	 * HttpEntity entity=null; try{ handler.preForRequest(post); HttpResponse
	 * response=httpHandler.execute(post); entity=response.getEntity();
	 * handler.forResponse(response); }finally{ try {
	 * EntityUtils.consume(entity); } catch (Exception e) {
	 * LOGGER.error(""+e.getMessage(),e); } } }
	 */

}
