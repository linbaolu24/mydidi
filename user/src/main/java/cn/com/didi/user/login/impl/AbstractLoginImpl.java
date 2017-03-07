package cn.com.didi.user.login.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.user.context.ILoginContext;
import cn.com.didi.user.login.ILoginProcess;
import cn.com.didi.user.login.ILoginResult;
import cn.com.didi.user.login.ILoginService;
import cn.com.didi.user.login.ISession;

public abstract class AbstractLoginImpl implements ILoginService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractLoginImpl.class);
	protected List<ILoginProcess> listeners;

	public ILoginResult login(ILoginContext context) {
		ILoginResult result = null;
		ISession session = null;
		try {
			result = postBefore(context);
			if (result != null) {
				return result;
			}
			session = context.createSession();
			result = loginInternal(context);
			result.setSession(session);
			postSession(context, session);
			ILoginResult tempResult = postAfter(context, result);
			return tempResult == null ? result : tempResult;
		} catch (RuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			try {
				ILoginResult exResult = postException(context, result, e);
				if (session != null) {
					session.dispose();
				}
				if (exResult != null) {
					return exResult;
				}
				throw e;
			} catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
				throw e;
			}
		}

	}

	public void addLoginObverser(ILoginProcess obverser) {
		if (listeners == null) {
			listeners = new ArrayList<>(4);
		}
		listeners.add(obverser);
	}

	/** 进行登录操作,登录完成会进行会话创建 */
	protected abstract ILoginResult loginInternal(ILoginContext context);
	protected ILoginResult postBefore(ILoginContext context) {
		if (CollectionUtils.isEmpty(listeners)) {
			return null;
		}
		ILoginResult result = null;
		for (ILoginProcess one : listeners) {
			result = one.postLoginBefore(context);
			if (result != null) {
				break;
			}
		}
		return result;

	}

	protected ILoginResult postException(ILoginContext context, ILoginResult tempResult, Exception ex) {
		if (CollectionUtils.isEmpty(listeners)) {
			return null;
		}
		ILoginResult result = null;
		for (ILoginProcess one : listeners) {
			result = one.postLoginException(context, result, ex);
			if (result != null) {
				break;
			}
		}
		return result;

	}

	protected ILoginResult postAfter(ILoginContext context, ILoginResult result) {
		if (CollectionUtils.isEmpty(listeners)) {
			return null;
		}
		ILoginResult tempResult = null;
		for (ILoginProcess one : listeners) {
			tempResult = one.postLoginAfter(context, result);
			if (tempResult != null) {
				break;
			}
		}
		return tempResult;

	}

	protected void postSession(ILoginContext context, ISession session) {
		if (CollectionUtils.isEmpty(listeners)) {
			return;
		}
		ILoginResult result = null;
		for (ILoginProcess one : listeners) {
			if (one.postLoginSession(context, session)) {
				return;
			}

		}

	}

	public List<ILoginProcess> getListeners() {
		return listeners;
	}

	public void setListeners(List<ILoginProcess> listeners) {
		this.listeners = listeners;
	}

}
