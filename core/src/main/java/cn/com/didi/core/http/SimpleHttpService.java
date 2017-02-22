package cn.com.didi.core.http;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 标题: SimpleHttpService.java<br>
 * 描述: <br>
 * 版权: 税友软件集团股份有限公司 <br>
 * 创建时间: 2016年10月12日<br>
 * 作者: xielm <br>
 * 修改历史记录：<br>
*/	
public class SimpleHttpService implements IHttpService{
	private static Logger LOGGER= LoggerFactory.getLogger(SimpleHttpService.class);
	@Resource
	protected IHttpExeService httpHandler;
	public void post(IHttpHandler handler) {
		HttpPost post=new HttpPost();
		HttpEntity entity=null;
		try{
			handler.preForRequest(post);
			HttpResponse response=httpHandler.execute(post);
			entity=response.getEntity();
			handler.forResponse(response);
		}finally{
			try {
				EntityUtils.consume(entity);
			} catch (Exception e) {
				LOGGER.error(""+e.getMessage(),e);
			}
		}
	}
	
	/*public void get(IHttpHandler handler) {
		HttpGet post=new HttpGet();
		HttpEntity entity=null;
		try{
			handler.preForRequest(post);
			HttpResponse response=httpHandler.execute(post);
			entity=response.getEntity();
			handler.forResponse(response);
		}finally{
			try {
				EntityUtils.consume(entity);
			} catch (Exception e) {
				LOGGER.error(""+e.getMessage(),e);
			}
		}
	}*/

}
