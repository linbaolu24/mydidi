package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum TradeCategory implements ICodeAble{
	IN("0","0","in"),//表示平台收入 
	OUT("1","1","draw"),//表示体现 
	PENALTY("2","0","penalty"),//表示违约金   
	DEPOSIT("3","0","deposit");//表示押金
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;
	private String type;//0代表收入 1代表支出
	private String name;
	private TradeCategory(String code,String type,String name) {
		this.code = code;
		this.type=type;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
