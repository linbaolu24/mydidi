package cn.com.didi.webBase.util.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountResolver extends AccountResolverAdapter{
	
	public AccountResolver(){
		setAccountKey("didi.accountKey");
	}

}
