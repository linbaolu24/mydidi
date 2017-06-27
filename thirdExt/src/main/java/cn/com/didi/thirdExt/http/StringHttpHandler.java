package cn.com.didi.thirdExt.http;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import cn.com.didi.core.utils.Constans;

public abstract class StringHttpHandler implements IHttpHandler {
	private String request;
	private String response;
	private String charset=Constans.CHARSET_UTF_8;
	private URI uri;

	@Override
	public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
		post.setURI(uri);
		post.setEntity(new StringEntity(request, charset));
		return false;
	}

	public void forResponseInternal(HttpResponse post) throws ParseException, IOException {
		/*byte[] bytes=EntityUtils.toByteArray(post.getEntity());
		FileUtils.writeByteArrayToFile(new File("D:\result.txt"), bytes);
		System.err.println(new String(bytes));
		System.err.println(new String(bytes,"UNICODE"));
		response=new String(bytes,charset);
		System.err.println(response);*/
	
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
