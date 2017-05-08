package cn.com.didi.order.orders.domain;

public class OrderListDto extends OrderListBaseDto{ 
	
	private String masterName;
	private String mci;
	public String getMci() {
		return mci;
	}
	public void setMci(String mci) {
		this.mci = mci;
	}
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	
}
