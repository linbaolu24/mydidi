package cn.com.didi.core.utils;

public class NumberUtil {
	public static String intToDecimal2(int number){
		int f=number%100;
		int y=number/100;
		return y+"."+f/10+f%10;
	}
}
