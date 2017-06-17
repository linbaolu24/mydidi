package cn.com.didi.domain.domains.ali;

public class AlipayTransToAccountResponse extends AlipayResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 
	 * 支付宝转账单据号，成功一定返回，失败可能不返回也可能返回。
	 */
	private String order_id;

	/** 
	 * 商户转账唯一订单号：发起转账来源方定义的转账单据号。请求时对应的参数，原样返回。
	 */
	private String out_biz_no;

	/** 
	 * 支付时间：格式为yyyy-MM-dd HH:mm:ss，仅转账成功返回。
	 */
	private String pay_date;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getOut_biz_no() {
		return out_biz_no;
	}

	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}

	public String getPay_date() {
		return pay_date;
	}

	public void setPay_date(String pay_date) {
		this.pay_date = pay_date;
	}
	
}
