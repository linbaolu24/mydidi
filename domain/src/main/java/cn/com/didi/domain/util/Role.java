package cn.com.didi.domain.util;

public enum Role {
	COUSMER("C"), BUSINESS("B"), PLATFORM("P");
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private Role(String code) {
		this.code=code;
	}

	private String code;

}
