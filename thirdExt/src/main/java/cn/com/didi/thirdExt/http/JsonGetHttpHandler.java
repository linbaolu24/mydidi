package cn.com.didi.thirdExt.http;

import java.net.URI;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
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
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {

		if (MapUtils.isNotEmpty(headerMap)) {
			Set<Map.Entry<String, String>> set = headerMap.entrySet();
			for (Map.Entry<String, String> one : set) {
				post.addHeader(one.getKey(), one.getValue());
			}
		}
		try {
			String newUrl=urlReplace(url,(Map)obj);						// JackSonUtil.toJSONString(obj);
			LOGGER.info("请求URL为:  {}", newUrl);
			post.setURI(new URI(newUrl));
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
     */
    public  String urlReplace (String url,  Map parms){
        if (StringUtils.isNotBlank(url) && null != parms ){
            StrSubstitutor strsub = new StrSubstitutor(parms);
            url = strsub.replace(url);
        }
        return url;
    }
	
}
