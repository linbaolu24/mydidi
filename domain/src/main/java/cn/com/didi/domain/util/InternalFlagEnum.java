package cn.com.didi.domain.util;

public enum InternalFlagEnum {
	/**
	 * 表示体验
	 */
	EXPERIENCE,
	/**
	 * 表示订单改派
	 */
	REASSIGNMENT;
	public boolean isFlagSet(int flag){
		int mask=1<<ordinal();
		return (flag&mask)>0;
	}
	
	public int flagSet(int flag){
		int mask=1<<ordinal();
		return flag|mask;
	}
	
	public boolean isFlagSetInteger(Integer flag){
		if(flag==null){
			return false;
		}
		return isFlagSet(flag);
	}
}
