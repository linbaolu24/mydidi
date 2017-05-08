package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum SpecialTypeEnum implements ICodeAble{
	NORMAL("0"),
	/**
	 * 美容美发
	 */
	MRMF("1");
	private String code;
	private SpecialTypeEnum(String code) {
		this.code = code;
	}
	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return code;
	}

}
