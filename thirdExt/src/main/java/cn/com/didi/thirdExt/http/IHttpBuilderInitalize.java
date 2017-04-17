package cn.com.didi.thirdExt.http;

import org.apache.http.impl.client.HttpClientBuilder;

/**
 * 对http进行初始化,例如https
 * @author xlm
 *
 */
public interface IHttpBuilderInitalize {
	public void init(HttpClientBuilder builder);
}

