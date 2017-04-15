package cn.com.didi.thirdExt.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpExecutionAware;
import org.apache.http.client.methods.HttpRequestWrapper;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpClientConnectionOperator;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.DefaultHttpClientConnectionOperator;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.execchain.ClientExecChain;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.message.MessageFactory;
import cn.com.didi.domain.util.DomainMessageConstans;

/**
 * @author xielm
 *
 */
public class SimpleHttpExeService implements IHttpExeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleHttpExeService.class);
	protected HttpClient client;
	protected int maxTotal = -1;
	protected int maxPerRoute = -1;
	protected boolean logRequestTime;
	protected IHttpBuilderInitalize initalize;
	public void init() {
		LOGGER.info("最大连接数为{},每个路由连接数为{}",maxTotal,maxPerRoute);
		if (client == null) {
			HttpClientBuilder builder=createBuilder();
			if (maxTotal >0) {
				builder.setMaxConnTotal(maxTotal);
			}
			if (maxPerRoute >0) {
				builder.setMaxConnPerRoute(maxPerRoute);
			}
			if(initalize!=null){
				LOGGER.debug("初始化器不为空,进行初始化.");
				initalize.init(builder);
			}
			client = builder.build();
		}
	}
	protected HttpClientBuilder createBuilder(){
		HttpClientBuilder builder;
		if(!logRequestTime){
			builder = HttpClientBuilder.create();
		}else{
			builder=new HttpClientBuilder(){
				 protected ClientExecChain decorateMainExec(final ClientExecChain mainExec) {
				        return new ClientExecChain() {							
							public CloseableHttpResponse execute(HttpRoute route, HttpRequestWrapper request, HttpClientContext clientContext,
									HttpExecutionAware execAware) throws IOException, HttpException {
								long start=System.currentTimeMillis();
								CloseableHttpResponse response=mainExec.execute(route, request, clientContext, execAware);
								long end=System.currentTimeMillis();
								LOGGER.debug("发送请求用时{}ms",(end-start));
								return response;
							}
						};
				    }
			};
			RegistryBuilder<ConnectionSocketFactory> socketFactory=RegistryBuilder.create();
			socketFactory.register("http", new LoggerConnectionSocketFactory(PlainConnectionSocketFactory.getSocketFactory()));
			LoggerHttpClientConnectionOperator operator=new LoggerHttpClientConnectionOperator(new DefaultHttpClientConnectionOperator(socketFactory.build(),null,new LoggerDNSResolver(SystemDefaultDnsResolver.INSTANCE)));
			PoolingHttpClientConnectionManager manager=new PoolingHttpClientConnectionManager(operator,null,-1,TimeUnit.MILLISECONDS);
			builder.setConnectionManager(manager);
			if(maxTotal >0){
				manager.setMaxTotal(maxTotal);
			}
			if(maxPerRoute>0){
				manager.setDefaultMaxPerRoute(maxPerRoute);
			}
		}
		return builder;
	}
	static class LoggerHttpClientConnectionOperator implements HttpClientConnectionOperator{
		protected  HttpClientConnectionOperator wrapped;
		
		public LoggerHttpClientConnectionOperator(HttpClientConnectionOperator wrapped) {
			super();
			this.wrapped = wrapped;
		}

		public void upgrade(ManagedHttpClientConnection conn, HttpHost host, HttpContext context) throws IOException {
			long start =System.currentTimeMillis();
			wrapped.upgrade(conn, host, context);
			long end=System.currentTimeMillis();
			LOGGER.debug("LoggerHttpClientConnectionOperator:升级协议用时{}ms",end-start);
		}
		
		public void connect(ManagedHttpClientConnection conn, HttpHost host, InetSocketAddress localAddress,
				int connectTimeout, SocketConfig socketConfig, HttpContext context) throws IOException {
			long start =System.currentTimeMillis();
			wrapped.connect(conn, host, localAddress, connectTimeout, socketConfig, context);
			long end=System.currentTimeMillis();
			LOGGER.debug("LoggerHttpClientConnectionOperator:connect连接用时{}ms",end-start);
			
		}
	}
	static class  LoggerConnectionSocketFactory implements ConnectionSocketFactory{
		protected  ConnectionSocketFactory wrapped;
		
		public LoggerConnectionSocketFactory(ConnectionSocketFactory wrapped) {
			super();
			this.wrapped = wrapped;
		}

		public Socket createSocket(HttpContext context) throws IOException {
			long start =System.currentTimeMillis();
			Socket so=wrapped.createSocket(context);
			long end=System.currentTimeMillis();
			LOGGER.debug("LoggerConnectionSocketFactory:创建socket{}ms",end-start);
			return so;
		}

		public Socket connectSocket(int connectTimeout, Socket sock, HttpHost host, InetSocketAddress remoteAddress,
				InetSocketAddress localAddress, HttpContext context) throws IOException {
			long start =System.currentTimeMillis();
			Socket so=wrapped.connectSocket(connectTimeout, sock, host, remoteAddress, localAddress, context);
			long end=System.currentTimeMillis();
			
			LOGGER.debug("LoggerConnectionSocketFactory:connectSocket{}ms",end-start);
			return so;
		}
		
	}
	static class LoggerDNSResolver implements DnsResolver{
		protected DnsResolver warpper;
		
		public LoggerDNSResolver(DnsResolver warpper) {
			super();
			this.warpper = warpper;
		}

		public InetAddress[] resolve(String host) throws UnknownHostException {
			long start =System.currentTimeMillis();
			InetAddress[] address=warpper.resolve(host);
			long end=System.currentTimeMillis();
			LOGGER.debug("LoggerDNSResolver:resolve:域名{}解析用时{}ms",host,end-start);
			return address;
		}
		
	}
	public HttpResponse execute(HttpUriRequest request) {
		HttpResponse response = null;
		try {
			response = client.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				return response;
			}
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(),e);
			throw new MessageObjectException(MessageFactory.creatMessage(DomainMessageConstans.CODE_SYSTEM_HTTP_ERROR,"Http请求异常"), e);
		}
		LOGGER.error("http返回码{}非200，{}",response.getStatusLine().getStatusCode(),response.getStatusLine().getReasonPhrase());
		return response;
		/*throw new BaseException(String.valueOf(response.getStatusLine().getStatusCode()),
				Fp56ErrorMessage.ERROR_HTTP.getMessage());*/
	}

	public void destroy() {
		if (client != null && client instanceof CloseableHttpClient) {
			try {
				((CloseableHttpClient) client).close();
			} catch (IOException e) {
				LOGGER.error("关闭client异常", e);
			}
		}
	}

	/**
	 * @return the maxTotal
	 */
	public int getMaxTotal() {
		return maxTotal;
	}

	/**
	 * @param maxTotal
	 *            the maxTotal to set
	 */
	public void setMaxTotal(int maxTotal) {
		this.maxTotal = maxTotal;
	}

	/**
	 * @return the maxPerRoute
	 */
	public int getMaxPerRoute() {
		return maxPerRoute;
	}

	/**
	 * @param maxPerRoute
	 *            the maxPerRoute to set
	 */
	public void setMaxPerRoute(int maxPerRoute) {
		this.maxPerRoute = maxPerRoute;
	}

	/**
	 * @return the client
	 */
	public HttpClient getClient() {
		return client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(HttpClient client) {
		this.client = client;
	}
	/**
	 * @return the logRequestTime
	 */
	public boolean isLogRequestTime() {
		return logRequestTime;
	}
	/**
	 * @param logRequestTime the logRequestTime to set
	 */
	public void setLogRequestTime(boolean logRequestTime) {
		this.logRequestTime = logRequestTime;
	}
	public IHttpBuilderInitalize getInitalize() {
		return initalize;
	}
	public void setInitalize(IHttpBuilderInitalize initalize) {
		this.initalize = initalize;
	}

}
