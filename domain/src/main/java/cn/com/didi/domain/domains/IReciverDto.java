package cn.com.didi.domain.domains;

import cn.com.didi.domain.util.Role;

public interface IReciverDto {
	Long getAccountId();
	String getReciveId();
	String getReciveType();
	Role getAccountType();
}
