package cn.com.didi.webBase.util.impl;

import javax.servlet.http.HttpServletRequest;

import cn.com.didi.webBase.util.IAccountResolver;

public class AccountResolverAdapter implements IAccountResolver {
	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	private String accountKey;

	@Override
	public Long resolve(HttpServletRequest request) {
		/*Object obj = getAccount(request);
		if (obj == null) {
			return null;
		}
		return resolveInternal(obj);*/
		return (Long) request.getSession().getAttribute(accountKey+"_ACCOUNT_ID");
	}

	protected Long resolveInternal(Object obj) {
		return null;
	}

	@Override
	public void saveAccount(HttpServletRequest request,Long acLong, Object obj) {
		request.getSession().setAttribute(accountKey, obj);
		request.getSession().setAttribute(accountKey+"_ACCOUNT_ID", acLong);
	}

	@Override
	public Object getAccount(HttpServletRequest request) {
		return request.getSession().getAttribute(accountKey);
	}

	@Override
	public void clearAccount(HttpServletRequest request) {
		request.getSession().invalidate();
	}

	@Override
	public void saveAttr(HttpServletRequest request,String key, String value) {
		request.getSession().setAttribute(key, value);
		
	}

	@Override
	public String getAttr(HttpServletRequest request, String key) {
		return (String)request.getAttribute(key);
	}

}
