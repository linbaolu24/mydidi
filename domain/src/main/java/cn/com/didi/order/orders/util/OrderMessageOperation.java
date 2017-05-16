package cn.com.didi.order.orders.util;

public enum OrderMessageOperation {
	AUTH,//鉴权
	BEFORE_PUBLISH,//在发布之前
	BEFORE_ADD,// 在增加之前 
	ADD_NEW_ORDER,//增加了新的订单
	ORDER_PUBLISHED,//订单发布成功以后
	ORDER_PUBLISHFAIL,//订单发布失败
	BEFORE_AUTO_DISPATCH,
	CANCELED,//订单取消
	FINISH_EVALUATION;//完成评价
}
