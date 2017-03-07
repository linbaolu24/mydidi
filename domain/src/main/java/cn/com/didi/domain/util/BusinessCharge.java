package cn.com.didi.domain.util;

public enum BusinessCharge {
	Free("0"),
	CHARGE("1");
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;

	private BusinessCharge(String code) {
		this.code = code;
	}
	public static boolean isCharge(String code){
		return CHARGE.getCode().equals(code);
	}
}
