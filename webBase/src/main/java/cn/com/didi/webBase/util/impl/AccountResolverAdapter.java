package cn.com.didi.webBase.util.impl;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.utils.AESUtils;
import cn.com.didi.webBase.util.IAccountResolver;

public class AccountResolverAdapter implements IAccountResolver {
	private static final String AES_KEY="0123abcd4567efgh";
	public String getAccountKey() {
		return accountKey;
	}

	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	private String accountKey;

	@Override
	public Long resolve(HttpServletRequest request) {
		/*
		 * Object obj = getAccount(request); if (obj == null) { return null; }
		 * return resolveInternal(obj);
		 */
		return (Long) request.getSession().getAttribute(accountKey + "_ACCOUNT_ID");
	}

	protected Long resolveInternal(Object obj) {
		return null;
	}

	@Override
	public void saveAccount(HttpServletRequest request, Long acLong, Object obj) {
		request.getSession().setAttribute(accountKey, obj);
		request.getSession().setAttribute(accountKey + "_ACCOUNT_ID", acLong);
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
	public void saveAttr(HttpServletRequest request, String key, String value) {
		request.getSession().setAttribute(key, value);

	}

	@Override
	public String getAttr(HttpServletRequest request, String key) {
		return (String) request.getSession().getAttribute(key);
	}

	@Override
	public void reflashAccount(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Enumeration<String> arrayNames = session.getAttributeNames();
		Map<String, Object> map = new HashMap<>();
		String name = null;
		while (arrayNames.hasMoreElements()) {
			name = arrayNames.nextElement();
			map.put(name, session.getAttribute(name));
		}
		request.getSession().invalidate();
		session = request.getSession(true);
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			session.setAttribute(entry.getKey(), entry.getValue());
		}

	}

	@Override
	public long getSessionTimepout(HttpServletRequest request) {
		return request.getSession().getMaxInactiveInterval();
		//request.getServletContext().getm
	}

	@Override
	public String saveAccountAndGeneratorReflashToken(HttpServletRequest request, Long accountId, Object obj) {
		saveAccount(request, accountId, obj);
		String json=JSON.toJSONString(obj);
		return AESUtils.encrypt(json, AES_KEY);
	}

	@Override
	public <T> T pasreReflashToken(String token, Class<T> target) {
		String jsonStr=AESUtils.decrypt(token, AES_KEY);
		return (T)JSON.parseObject(jsonStr, target);
		
	}

}
