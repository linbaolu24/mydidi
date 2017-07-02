package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum Role implements ICodeAble{

	COUSMER("C",100L), BUSINESS("B",101L), PLATFORM("P",110L);
	public Long   accountIdFromUserName(String phone){
		return prefix*exp+Long.parseLong(phone);
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private Role(String code,long prefix) {
		this.code=code;
		this.prefix=prefix;
	}

	private String code;
	private long prefix;
	public long getPrefix() {
		return prefix;
	}
	public void setPrefix(long prefix) {
		this.prefix = prefix;
	}
	static long exp=(long)Math.pow(10, 12);
	
	
}
