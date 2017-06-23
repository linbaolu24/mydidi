package cn.com.didi.thirdExt.http;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.property.IConverter;

/**
 * @author xlm
 *
 * @param <T>
 */
public class JsonHttpHandler<T> implements IHttpHandler {
	private static Logger LOGGER=LoggerFactory.getLogger(JsonHttpHandler.class);
	protected Object obj;
	protected Map<String, String> headerMap;
	protected Class<T> resultClass;
	protected T result;
	protected String url;
	protected String charset = Charsets.UTF_8.name();
	public static final String HEADER_CONTENT_TYPE="Content-Type";
	public static final String HEADER_CONTENT_TYPE_JSON="application/json;charset=utf-8";
	protected Exception e;
	protected IConverter<String, String> convert;
	protected String source;
	
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
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
		
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
			String str =JSON.toJSONString(obj);//TODO = JackSonUtil.toJSONString(obj);
			LOGGER.info("请求内容为:  {}",str);
			post.setURI(new URI(url));
			StringEntity entity = new StringEntity(str, charset);
			post.setEntity(entity);
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(),e);
			this.e=e;
			return true;
		} 
		return false;

	}

	@Override
	public void forResponse(HttpResponse post) {
		try {
			HttpEntity entity = post.getEntity();
			byte[] tempResult = EntityUtils.toByteArray(entity);
			String returnStr=new String(tempResult,charset);
			LOGGER.info("返回结果为:  {}",returnStr);
			source=returnStr;
			if(convert!=null){
				returnStr=convert.convert(returnStr);
				LOGGER.info("转换结果为:  {}",returnStr);
			}
			if (resultClass != null) {
				result=JSON.parseObject(returnStr, resultClass);//TODO JackSonUtil.toObject(returnStr, resultClass);
			}
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(),e);
			this.e=e;
		}
	}

	public T getResult() {
		return result;
	}
	
	public T getResultAndThrow()  throws Exception{
		if(e!=null){
			throw e;
		}
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

	public IConverter<String, String> getConvert() {
		return convert;
	}

	public void setConvert(IConverter<String, String> convert) {
		this.convert = convert;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
