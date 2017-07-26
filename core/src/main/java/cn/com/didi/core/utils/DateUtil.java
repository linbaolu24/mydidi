package cn.com.didi.core.utils;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

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

	public static Date getDate(int year,int month,int day,boolean truncate) {

		Calendar cal=Calendar.getInstance();
		cal.set(year, month, day);
		if(truncate){
			DateUtils.truncate(cal, Calendar.DAY_OF_MONTH);
		}
		return cal.getTime();
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
	
	public static Date getInterval(int interval){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, interval);
		return cal.getTime();
	}
	public static void main(String[] args) {
		System.out.println(getIntervalYYYYMMDD(-3));
		System.out.println(getDate(1990, 01, 1,false));
	}
	public static long getIntervalDay(Date date1,Date date2){
		return  ((date2.getTime()-date1.getTime())/(24*60*60*1000));
	}
	public static boolean lessInterval(Date from,Date end,int interval){
		return  ((end.getTime()-from.getTime())<=interval*(24*60*60*1000));
	}
	public static Date getDateIntervalYear(Date time,int intervalYear){
		return DateUtils.addYears(time, intervalYear);
	}
	public static Date getFirstDayOfMonth(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY,0);
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	public static Date min(Date one,Date two){
		if(one==null){
			return two;
		}
		if(two==null){
			return one;
		}
		return one.compareTo(two)<=0?one:two;
	}
}
