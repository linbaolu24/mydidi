package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

public enum TradeCategory implements ICodeAble{
	IN("0","0"),//表示平台收入 
	OUT("1","1"),//表示体现 
	PENALTY("2","0"),//表示违约金   
	DEPOSIT("3","0");//表示押金
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
	private TradeCategory(String code,String type) {
		this.code = code;
		this.type=type;
	}
	
}
