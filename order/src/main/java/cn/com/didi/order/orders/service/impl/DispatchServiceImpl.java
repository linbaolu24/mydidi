package cn.com.didi.order.orders.service.impl;

import java.util.concurrent.Executor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.thread.ExecutorFactory;
import cn.com.didi.order.IOrderInfo;
import cn.com.didi.order.orders.service.IOrderService;
import cn.com.didi.order.result.IOrderRuslt;

/**
 * @author xlm
 *
 */
@Service

public class DispatchServiceImpl extends OrderLifeListenerAdapter {
	public ExecutorFactory getFactory() {
		return factory;
	}

	public void setFactory(ExecutorFactory factory) {
		this.factory = factory;
	}

	private static final String AUTO_EXE_NAME="auto_exe";
	private static final Logger LOGGER=LoggerFactory.getLogger(DispatchServiceImpl.class);
	public Executor getExe() {
		return exe;
	}

	public void setExe(Executor exe) {
		this.exe = exe;
	}

	protected Executor exe;
	@Resource
	protected ExecutorFactory factory;
	@PostConstruct
	protected void init(){
		if(exe==null&&factory!=null){
			exe=factory.createExecutor(AUTO_EXE_NAME);
		}
	}

	@Override
	public boolean publish(IOrderService service, IOrderInfo info, IOrderRuslt<Void> result) {
		if ((result == null || result.success())
				&& (info != null && info.getOrderId() != null && info.getConsumerId() != null)) {
			LOGGER.debug("=====进行派单======");
			DispatchRunable run = new DispatchRunable(info.getOrderId(), info.getConsumerId(), service,
					info.selfBusiness());
			exe.execute(run);
		}
		return true;

	}

	protected static class DispatchRunable implements Runnable {
		private static Logger LOGGER = LoggerFactory.getLogger(DispatchRunable.class);
		private Long orderId;
		private Long bId;
		private IOrderService orderService;
		private boolean isSelf;

		public DispatchRunable(Long orderId, Long bId, IOrderService orderService, boolean isSelf) {
			super();
			this.orderId = orderId;
			this.bId = bId;
			this.orderService = orderService;
			this.isSelf = isSelf;
		}

		@Override
		public void run() {
			try {
				IOrderRuslt<Void> orderResult;
				if (isSelf) {
					orderResult = orderService.autoDispatch(orderId, bId);
				} else {
					orderResult = orderService.notifyOrder(orderId, bId);
				}
				if (orderResult != null && !orderResult.success()) {
					LOGGER.error("订单{}派单失败,错误代码{},错误消息{}", orderId, orderResult.getCode(), orderResult.getMessage());
				}
			} catch (Exception e) {
				LOGGER.error("订单{}派单失败", orderId, e);
			}
		}

	}
	

	// public void publish(IOrderService service, IOrderInfo info) {
	/*
	 * if(info.selfBusiness()){//如果自营业务发出自动派单 service.autoDispatch(info); }else{
	 * service.notifyOrder(info);//如果非自营业务发出抢单 }
	 */
	// }
	
}
