package cn.com.didi.domain.domains.ali;

public class AliPayTradeResponse extends AlipayTransToAccountResponse{
	private String amount;
	private String trade_status;
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	
}
