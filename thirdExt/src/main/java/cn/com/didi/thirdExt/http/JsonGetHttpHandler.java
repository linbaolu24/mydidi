package cn.com.didi.thirdExt.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonGetHttpHandler<T> extends JsonHttpHandler<T> {
	private static Logger LOGGER = LoggerFactory.getLogger(JsonGetHttpHandler.class);

	public JsonGetHttpHandler(Object obj, Map<String, String> headerMap, Class<T> resultClass, String url) {
		super(obj, headerMap, resultClass, url);
	}
	public JsonGetHttpHandler(Map<String,String> obj,Class<T> resultClass, String url) {
		this(obj, null, resultClass, url);
	}

	@Override
	public boolean preForRequestGet(HttpUriRequest post) {

		if (MapUtils.isNotEmpty(headerMap)) {
			Set<Map.Entry<String, String>> set = headerMap.entrySet();
			for (Map.Entry<String, String> one : set) {
				post.addHeader(one.getKey(), one.getValue());
			}
		}
		try {
			String newUrl=urlReplace(url,(Map)obj);						// JackSonUtil.toJSONString(obj);
			LOGGER.info("请求URL为:  {}", newUrl);
			((HttpRequestBase)post).setURI(new URI(newUrl));
		} catch (Exception e) {
			LOGGER.error("" + e.getMessage(), e);
			this.e = e;
		}
		return false;

	}
	  /**
     * 将URL中的模板参数用实际值替换
     * @param urlEnums
     * @param parms
     * @return
	 * @throws UnsupportedEncodingException 
     */
    public  String urlReplace (String url,  Map<String,String> parms) throws UnsupportedEncodingException{
        if (StringUtils.isNotBlank(url) && null != parms ){
        	Map map2=new HashMap<>(parms.size());
        	for(Map.Entry<String, String> one:parms.entrySet()){
        		map2.put(one.getKey(), URLEncoder.encode(one.getValue(),charset));
        	}
            StrSubstitutor strsub = new StrSubstitutor(map2);
            url = strsub.replace(url);
        }
        return url;
    }
    
	
}
