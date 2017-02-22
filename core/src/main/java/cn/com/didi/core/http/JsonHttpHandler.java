package cn.com.didi.core.http;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.Charsets;
import org.apache.commons.collections.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xlm
 *
 * @param <T>
 */
public class JsonHttpHandler<T> implements IHttpHandler {
	private static Logger LOGGER=LoggerFactory.getLogger(JsonHttpHandler.class);
	private Object obj;
	private Map<String, String> headerMap;
	private Class<T> resultClass;
	private T result;
	private String url;
	private String charset = Charsets.UTF_8.name();
	private static final String HEADER_CONTENT_TYPE="Content-Type";
	private static final String HEADER_CONTENT_TYPE_JSON="application/json;charset=utf-8";
	
	public JsonHttpHandler(Object obj, Map<String, String> headerMap, Class<T> resultClass, String url,
			String charset) {
		super();
		this.obj = obj;
		this.headerMap = headerMap;
		this.resultClass = resultClass;
		this.url = url;
		this.charset = charset;
	}

	public JsonHttpHandler(Object obj, Map<String, String> headerMap, Class<T> resultClass, String url) {
		super();
		this.obj = obj;
		this.headerMap = headerMap;
		this.resultClass = resultClass;
		this.url = url;
	}

	@Override
	public void preForRequest(HttpEntityEnclosingRequestBase post) {
		
		if (MapUtils.isNotEmpty(headerMap)) {
			Set<Map.Entry<String, String>> set = headerMap.entrySet();
			for (Map.Entry<String, String> one : set) {
				post.addHeader(one.getKey(), one.getValue());
			}
		}
		Header[] header=post.getHeaders(HEADER_CONTENT_TYPE);
		if(header==null||header.length<=0){
			post.setHeader(HEADER_CONTENT_TYPE,HEADER_CONTENT_TYPE_JSON);
		}
		try {
			String str =null;//TODO = JackSonUtil.toJSONString(obj);
			LOGGER.info("请求内容为:  {}",str);
			post.setURI(new URI(url));
			StringEntity entity = new StringEntity(str, charset);
			post.setEntity(entity);
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(),e);
		} 

	}

	@Override
	public void forResponse(HttpResponse post) {
		try {
			HttpEntity entity = post.getEntity();
			byte[] tempResult = EntityUtils.toByteArray(entity);
			String returnStr=new String(tempResult,charset);
			LOGGER.info("返回结果为:  {}",returnStr);
			if (resultClass != null) {
				result=null;//TODO JackSonUtil.toObject(returnStr, resultClass);
			}
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(),e);
		}
	}

	public T getResult() {
		return result;
	}
	/**
	 * @param header
	 * @param value
	 */
	public void addHeader(String header,String value){
		if(headerMap==null){
			headerMap=new HashMap<String, String>();
		}
		headerMap.put(header, value);
	}

}
