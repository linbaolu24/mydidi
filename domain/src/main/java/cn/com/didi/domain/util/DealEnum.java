package cn.com.didi.domain.util;
/*** 
 * 0 表示待确认 1表示已确认 2失败 3表示审核不通过 4 代表预审核状态 尚未提现成功
 * @author xlm
 *
 */
public enum DealEnum {
	WAITTING("0"),
	FINISH("1"),
	FAIL("2"),
	NOT_PASSING("3"),
	PRE_AUDIT("4");
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
