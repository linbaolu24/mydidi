package cn.com.didi.thirdExt.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public abstract class StringHttpHandler implements IHttpHandler {
	private String request;
	private String response;
	private String charset;
	private URI uri;

	@Override
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
		post.setURI(uri);
		post.setEntity(new StringEntity(request, charset));
		return false;
	}

	public void forResponseInternal(HttpResponse post) throws ParseException, IOException {

		response = EntityUtils.toString(post.getEntity(), charset);

	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

}
