package cn.com.didi.order.orders.service;

import cn.com.didi.order.result.IOrderRuslt;

/**
 * @author xlm
 *
 */
public interface IOrderIntercptor {
	<T> IOrderRuslt<T> intercptor();
}
