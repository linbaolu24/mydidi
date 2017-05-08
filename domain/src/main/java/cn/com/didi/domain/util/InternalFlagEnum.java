package cn.com.didi.domain.util;

public enum InternalFlagEnum {
	EXPERIENCE;
	public boolean isFlagSet(int flag){
		int mask=1<<ordinal();
		return (flag&mask)>0;
	}
	
	public int flagSet(int flag){
		int mask=1<<ordinal();
		return flag|mask;
	}
}
