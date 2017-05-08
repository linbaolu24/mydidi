package cn.com.didi.core.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author xlm
 *
 */
public class DateUtil {
	public static int getCurrentYYYYMMDD(){
		return getCurrentYYYYMMDD(Calendar.getInstance());
	}
	
	public static int getCurrentYYYYMMDD(Calendar cal){
		
		int year=cal.get(Calendar.YEAR);
		int month=cal.get(Calendar.MONTH)+1;
		int day=cal.get(cal.DAY_OF_MONTH);
		return year*10000+month*100+day;
	}
	
	public static int getCurrentYYYYMMDD(Date date){
		
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		return getCurrentYYYYMMDD(cal);
	}
	
	public static int getIntervalYYYYMMDD(int interval){
		Calendar cal=Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(cal.DAY_OF_MONTH), 0, 0, 0);
		cal.add(Calendar.DAY_OF_MONTH, interval);
		return getCurrentYYYYMMDD(cal);
	}
	public static void main(String[] args) {
		System.out.println(getIntervalYYYYMMDD(-3));
	}
	public static long getIntervalDay(Date date1,Date date2){
		return  ((date2.getTime()-date1.getTime())/(24*60*60*1000));
	}
}
