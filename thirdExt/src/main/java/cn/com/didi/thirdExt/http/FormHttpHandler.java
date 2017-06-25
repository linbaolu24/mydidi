package cn.com.didi.thirdExt.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.Charsets;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormHttpHandler implements IHttpHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(FormHttpHandler.class);
	private Map<String, String> headerMap;
	private String url;
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String HEADER_CONTENT_TYPE_JSON = "application/x-www-form-urlencoded;charset=";
	private List<? extends NameValuePair> pair;
	private String charset = Charsets.UTF_8.name();
	private HttpResponse reponse;
	private String result;
	private Exception e;
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
		if (MapUtils.isNotEmpty(headerMap)) {
			Set<Map.Entry<String, String>> set = headerMap.entrySet();
			for (Map.Entry<String, String> one : set) {
				post.addHeader(one.getKey(), one.getValue());
			}
		}
		Header[] header = post.getHeaders(HEADER_CONTENT_TYPE);
		if (header == null || header.length <= 0) {
			post.setHeader(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_JSON+charset);
		}
		try {
			if (pair != null) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pair, charset);
				post.setEntity(entity);
			}
			post.setURI(new URI(url));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(), e);
			this.e=e;
			return true;
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage(), e);
			this.e=e;
			return true;
		}
		return false;

	}

	public void forResponse(HttpResponse post) {
		this.reponse=post;
		try {
			result = EntityUtils.toString(post.getEntity(), charset);
		} catch (ParseException | IOException e) {
			this.e=e;
		}
	}

	public Map<String, String> getHeaderMap() {
		return headerMap;
	}

	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<? extends NameValuePair> getPair() {
		return pair;
	}

	public void setPair(List<? extends NameValuePair> pair) {
		this.pair = pair;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public HttpResponse getReponse() {
		return reponse;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	public String getResultAndThrow() throws Exception {
		if(e!=null){
			throw e;
		}
		return result;
	}

}
