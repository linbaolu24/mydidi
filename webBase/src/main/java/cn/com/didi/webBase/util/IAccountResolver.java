package cn.com.didi.webBase.util;

import javax.servlet.http.HttpServletRequest;

public interface IAccountResolver {
	/**
	 * @param request
	 * @return
	 */
	public Long resolve(HttpServletRequest request);
	/**
	 * @param request
	 * @return
	 */
	public Object resolveObject(HttpServletRequest request);
	/**
	 * @param request
	 * @param obj
	 */
	public void saveAccount(HttpServletRequest request,Long accountId,Object obj);
	
	/**
	 * @param request
	 * @param obj
	 */
	public String saveAccountAndGeneratorReflashToken(HttpServletRequest request,Long accountId,Object obj);

	/**
	 * @param key
	 * @param key
	 */
	public void saveAttr(HttpServletRequest request,String key,String value);
	/**
	 * @param key
	 * @param key
	 */
	public String getAttr(HttpServletRequest request,String key);
	/**
	 * @param request
	 * @return
	 */
	public Object getAccount(HttpServletRequest request);
	/**
	 * @param request
	 */
	public void clearAccount(HttpServletRequest request);
	
	/**
	 * @param request
	 */
	public void reflashAccount(HttpServletRequest request);
	
	/**
	 * @param request
	 */
	public <T> T pasreReflashToken(String token,Class<T> target);
	
	
	/**
	 * @param request
	 * @return
	 */
	public long getSessionTimepout(HttpServletRequest request);
}
