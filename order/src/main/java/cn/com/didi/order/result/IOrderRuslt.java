package cn.com.didi.order.result;

import cn.com.didi.core.property.IResult;

public interface IOrderRuslt<T> extends IResult<T>{
	public Long getOrderId();
	public boolean isHandleFail();
	public void setHandleFail(boolean set);
	public void setOrderId(Long orderId);
	public boolean isFinish();
	public void setFinish(boolean finish);
}
