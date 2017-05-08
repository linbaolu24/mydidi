package cn.com.didi.domain.util;

/**
 * 虚拟标记
 * @author xlm
 *
 */
public enum VisualMark {
	VISUAL("1"),
	NORMAL("0");
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	private VisualMark(String code) {
		this.code = code;
	}
	
}
