package cn.com.didi.thirdExt.http;


public interface IHttpService {
	/**
	 * @param handler
	 */
	public void post(IHttpHandler handler);
	/**
	 * @param handler
	 */
	public void get(IHttpHandler handler);
}
