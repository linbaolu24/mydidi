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
	public String getState();
	/**
	 * @return
	 */
	public Integer getFlsId();
	/**
	 * @return
	 */
	public Integer getSlsId();
	/**
	 * 获取业务类型 自营/非自营
	 * @return
	 */
	public String getBusinessCategory();
	/**佣金
	 * @return
	 */
	public Integer getCommission();
	/**
	 * 判断是否是自营业务
	 * @return
	 */
	public boolean selfBusiness();
	/**
	 * @return
	 */
	public String getConsumerAddress();
	
	/**经度*/
	public BigDecimal getLng() ;
    /**纬度*/
	public BigDecimal getLat();

	
}
