package cn.com.didi.order.orders.service;

/**
 * @author xlm
 *
 */
public interface IOrderStateTransform {
	/**
	 * 判断能否进行状态转移
	 * @param source
	 * @param dest
	 * @param businessCharge
	 * @param businessCategory
	 * @return
	 */
	public boolean canTransform(String source,String dest,String businessCharge,String businessCategory);
	/**
	 * 进行状态转移
	 * @param source
	 * @param businessCharge
	 * @param businessCategory
	 * @return
	 */
	public String transform(String source,String businessCharge,String businessCategory);
}
