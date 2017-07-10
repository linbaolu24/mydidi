package cn.com.didi.order.orders.service.impl.plugin;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cn.com.didi.core.filter.IOperationInterceptor;
import cn.com.didi.core.lock.ILock;
import cn.com.didi.core.lock.LockManager;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.order.result.IOrderRuslt;
import cn.com.didi.order.result.OrderRuslt;
import cn.com.didi.order.util.OrderMessageConstans;

@Service
@Primary
public class LockedOperationInterceptor
		implements IOperationInterceptor<OrderMessageOperation, OrderContextDto, IOrderService> {
	private static Logger LOGGER = LoggerFactory.getLogger(LockedOperationInterceptor.class);
	@Resource
	protected LockManager myLockManager;
	@Resource(name = "normalOrderOperationInterceptor")
	protected IOperationInterceptor<OrderMessageOperation, OrderContextDto, IOrderService> wrapped;

	@Override
	public <R> IOrderRuslt<R> interceptor(OrderMessageOperation operation, OrderContextDto data, IOrderService source) {
		OrderDto order = data.getOrderDto();
		if (!isNeedLock(order)) {
			return null;
		}
		if (OrderMessageOperation.BEFORE_PUBLISH.equals(operation)) {
			String lockName = getLockName(order);
			long timeOut = getLockMillSecond(order);
			LOGGER.debug("对订单{}根据消费者id和二级服务id进行上锁,锁名{},锁等待超时时间为{}毫秒", order, lockName, timeOut);
			ILock lock = myLockManager.accquireLock(lockName);
			try {
				boolean success = lock.lock(timeOut, TimeUnit.MILLISECONDS);
				if (!success) {
					return new OrderRuslt<>(OrderMessageConstans.ORDER_MRMF_LOCKED);
				}
				data.setLock(lock);
				return (IOrderRuslt<R>) wrapped.interceptor(operation, data, source);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				return new OrderRuslt<>(OrderMessageConstans.ORDER_MRMF_LOCK_FAIL);
			}
		} else if (data.getLock() != null && (OrderMessageOperation.ORDER_PUBLISHED.equals(operation)
				|| OrderMessageOperation.ORDER_PUBLISHFAIL.equals(operation))) {
			ILock lock = data.getLock();
			
			try {
				return (IOrderRuslt<R>) wrapped.interceptor(operation, data, source);
			} finally {
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("订单ID:{},解锁{}", order.getOrderId(), getLockName(order));
				}
				unlock(lock);
			}
			
		}
		return (IOrderRuslt<R>) wrapped.interceptor(operation, data, source);

	}

	protected void unlock(ILock lock) {
		try {
			lock.unlock();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}

	protected boolean isNeedLock(OrderDto order) {
		if (SpecialTypeEnum.MRMF.codeEqual(order.getSpecialType())) {
			return true;
		}
		return false;

	}

	protected String getLockName(OrderDto order) {
		return "order_" + order.getConsumerAccountId() + "_" + order.getSlsId();
	}

	protected long getLockMillSecond(OrderDto order) {
		return 1500;
	}
}
