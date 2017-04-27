package cn.com.didi.domain.util;

public enum OrderState {
	

	/**
	 * 失败
	 */
    ORDER_STATE_FAIL("0"),
	/**
	 * 完成
	 */
	ORDER_STATE_FINISH("1"),
	/**
	 * 取消
	 */
	ORDER_STATE_CANNEL("2"),
	/**
	 * 发布
	 */
	ORDER_STATE_PUBLISH("3"),
	/**
	 * 通知中
	 */
	ORDER_STATE_NOTIFY("4"),
	/**接单状态*/
	ORDER_STATE_TAKING("5"),
	/**
	 * 开始服务
	 */
	ORDER_STATE_START_SERVICE("6"),
	/**
	 * 待收费
	 */
	ORDER_STATE_PENDING_CHARGE("7"),
	/**
	 * 待评价
	 */
	ORDER_STATE_Pending_EVALUATION("8");
	
	private String code;

	private OrderState(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public boolean isLess(String state){
		return code.compareTo(state)<0;
	}
	/**比state大*/
	public boolean isGreatEqual(String state){
		return code.compareTo(state)>=0;
	}
	
}
