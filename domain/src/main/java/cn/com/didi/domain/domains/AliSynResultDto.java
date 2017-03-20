package cn.com.didi.domain.domains;

public class AliSynResultDto {
	@Override
	public String toString() {
		return "AliSynResultDto [memo=" + memo + ", result=" + result + ", resultStatus=" + resultStatus + "]";
	}
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
