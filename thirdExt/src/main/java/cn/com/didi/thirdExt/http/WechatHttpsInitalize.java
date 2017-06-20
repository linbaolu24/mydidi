package cn.com.didi.thirdExt.http;

import java.io.InputStream;
import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.thirdExt.produce.IAppEnv;

public class WechatHttpsInitalize implements IHttpBuilderInitalize {
	private static final Logger LOGGER = LoggerFactory.getLogger(WechatHttpsInitalize.class);
	protected IAppEnv env;

	@Override
	public void initalize(HttpClientBuilder builder) {
		try {
			InputStream inStream = env.getWechatKeyStroe();
			if (inStream == null) {
				LOGGER.error("微信证书未知的输入流");
				return;
			}
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(env.getWechatKeyStroe(), env.getWechatPassword().toCharArray());
			// Trust own CA and all self-signed certs
			SSLContext sslcontext = SSLContexts.custom()
					.loadKeyMaterial(keyStore, env.getWechatPassword().toCharArray()).build();
			// Allow TLSv1 protocol only
			SSLConnectionSocketFactory SSLSF = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" },
					null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			builder.setSSLSocketFactory(SSLSF);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	public IAppEnv getEnv() {
		return env;
	}

	public void setEnv(IAppEnv env) {
		this.env = env;
	}

}
