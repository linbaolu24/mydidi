package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum Role implements ICodeAble{
	COUSMER("C"), BUSINESS("B"), PLATFORM("P");
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private Role(String code) {
		this.code=code;
	}

	private String code;

}
