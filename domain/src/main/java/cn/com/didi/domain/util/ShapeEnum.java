package cn.com.didi.domain.util;

public enum ShapeEnum {
	/**多边形*/
	P("P");

	private ShapeEnum(String code) {
		this.code = code;
	}

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
