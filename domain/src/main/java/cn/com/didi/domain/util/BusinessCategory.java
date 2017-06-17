package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum BusinessCategory implements ICodeAble{
	/**
	 * 自营
	 */
	SELF("0"),
	/**
	 * 三方
	 */
	THIRD("1");
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;

	private BusinessCategory(String code) {
		this.code = code;
	}
	
}
