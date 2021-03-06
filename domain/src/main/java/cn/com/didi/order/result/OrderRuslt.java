package cn.com.didi.order.result;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.property.impl.result.Result;

public class OrderRuslt<T> extends Result<T> implements IOrderRuslt<T>{
	public static final OrderRuslt SUCCESS_ORDER_RESULT=new OrderRuslt((Long)null);
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public boolean isHandleFail() {
		return handleFail;
	}
	public void setHandleFail(boolean handleFail) {
		this.handleFail = handleFail;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	private boolean handleFail;
	private Long orderId;
	private boolean finish;
	public OrderRuslt(String message, String code, Exception exception, T data) {
		super(message, code, exception, data);
		// TODO Auto-generated constructor stub
	}
	public OrderRuslt(Long orderId) {
		this(null,SUCCESS_CODE,null,null);
		this.orderId=orderId;
	}
	public OrderRuslt(T data) {
		this(null,SUCCESS_CODE,null,data);
	}
	
	public OrderRuslt(String message, String code) {
		super(message, code, null, null);
		// TODO Auto-generated constructor stub
	}
	public OrderRuslt(Message message){
		this(message.getMessage(),message.getCode());
	}
	@SuppressWarnings("unchecked")
	public static <T> OrderRuslt<T> successResult(){
		return SUCCESS_ORDER_RESULT;
	}

}
