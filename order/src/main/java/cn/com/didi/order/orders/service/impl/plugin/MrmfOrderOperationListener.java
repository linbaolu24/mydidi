package cn.com.didi.order.orders.service.impl.plugin;

import cn.com.didi.core.filter.IOperationListener;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderContextDto;
import cn.com.didi.order.orders.util.OrderMessageOperation;

public class MrmfOrderOperationListener implements IOperationListener<OrderMessageOperation, OrderContextDto> {

	@Override
	public void operate(OrderMessageOperation operation, OrderContextDto data) {
		if (!SpecialTypeEnum.MRMF.codeEqual(data.getOrderDto().getSpecialType())) {
			if (OrderMessageOperation.BEFORE_PUBLISH.equals(operation)) {
				
			}
		}
	}

}
