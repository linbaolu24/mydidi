package cn.com.didi.webBase.util.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountResolver extends AccountResolverAdapter{
	protected Long resolveInternal(Object obj) {
		/*Map uext=(Map)obj;
		return uext.getAccountId();*/
		return null;
	}
	public AccountResolver(){
		setAccountKey("didi.accountKey");
	}

}
