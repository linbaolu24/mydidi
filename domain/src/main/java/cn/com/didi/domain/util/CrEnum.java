package cn.com.didi.domain.util;

/**
 * 审批结果
 * @author xlm
 *
 */
public enum CrEnum {
	PASSING ("0"),//表示认证通过
	WATTING("1"),//表示认证待认证
	NOT_PASSING("2");//表示认证不通过
	private String code;

	private CrEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
