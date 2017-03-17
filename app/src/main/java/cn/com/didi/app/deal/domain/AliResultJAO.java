package cn.com.didi.app.deal.domain;

/**
 * @author xlm
 *
 */
public class AliResultJAO {
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	private String memo;
	private String result;
	private String resultStatus;
	
}
