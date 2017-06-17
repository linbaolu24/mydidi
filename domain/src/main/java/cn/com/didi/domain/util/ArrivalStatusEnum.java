package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum ArrivalStatusEnum implements ICodeAble {
	/**
	 *正常 
	 */
	NORMAL("0"),
	/**
	 * 未入驻
	 */
	NOT_ARRIVAL("1"),
	/**
	 * 审核尚未通过
	 */
	NOT_AUDIT("2");
	
	private String code;
	
	private ArrivalStatusEnum(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

}
