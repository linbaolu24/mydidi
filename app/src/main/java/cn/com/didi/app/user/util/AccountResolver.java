package cn.com.didi.app.user.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.com.didi.webBase.util.impl.AccountResolverAdapter;

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
