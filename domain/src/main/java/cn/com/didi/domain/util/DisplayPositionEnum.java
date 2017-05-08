package cn.com.didi.domain.util;

public enum DisplayPositionEnum {
	BOOT("0"),//表示启动页
	ORDER_TAKING("1"),
	LEFT("2");//表示侧边栏
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private DisplayPositionEnum(String code) {
		this.code = code;
	}
	
}
