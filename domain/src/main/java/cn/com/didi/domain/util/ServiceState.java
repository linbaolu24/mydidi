package cn.com.didi.domain.util;

public enum ServiceState {
	NORMAL("0"),//表示正常
	DRAFT("1"),//表示草稿
	HIDDEN("2"); //2表示隐藏
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private String code;

	private ServiceState(String code) {
		this.code = code;
	}
}
