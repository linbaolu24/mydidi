package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum FlagEnum implements ICodeAble{
	FLAG_NOT_SET("0"),
	FLAG_SET("1");
	private String code;

	private FlagEnum(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
	
}
