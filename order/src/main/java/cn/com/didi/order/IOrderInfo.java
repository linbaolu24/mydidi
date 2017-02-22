package cn.com.didi.order;

import java.math.BigDecimal;

public interface IOrderInfo {
	/**
	 * orderId
	 * @return
	 */
	public Long getOrderId();
	/**
	 * @return
	 */
	public Long getConsumerId();
	/**
	 * @return
	 */
	public Long getMerchantId();
	/**
	 * @return
	 */
	public int getState();
	/**
	 * @return
	 */
	public int getFlsId();
	/**
	 * @return
	 */
	public int getSlsId();
	/**
	 * 获取业务类型 自营/非自营
	 * @return
	 */
	public String getBusinessCategory();
	/**佣金
	 * @return
	 */
	public BigDecimal getCommission();
	/**
	 * 判断是否是自营业务
	 * @return
	 */
	public boolean selfBusiness();
	
}
