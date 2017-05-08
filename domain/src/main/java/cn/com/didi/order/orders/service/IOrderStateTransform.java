package cn.com.didi.order.orders.service;

import java.util.List;

import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderStateRecordDto;

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
	/**
	 * 解析状态流程
	 * @param order
	 * @param record
	 * @return
	 */
	public List<OrderStateRecordDto> resolve(OrderDto order,List<OrderStateRecordDto> record);
}
