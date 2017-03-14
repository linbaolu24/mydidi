package cn.com.didi.domain.util;

public enum DealEnum {
	WAITTING("0"),
	FINISH("1"),
	FAIL("2");
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private DealEnum(String code) {
		this.code = code;
	}

	private String code;
	
}
