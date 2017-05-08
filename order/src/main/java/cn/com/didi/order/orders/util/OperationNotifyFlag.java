package cn.com.didi.order.orders.util;

public enum OperationNotifyFlag {
	ADD_ExperienceService;
	public boolean isFlagSet(int flag){
		int mask=1<<ordinal();
		return (flag&mask)>0;
	}
	
	public int flagSet(int flag){
		int mask=1<<ordinal();
		return flag|mask;
	}
}
