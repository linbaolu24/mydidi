package cn.com.didi.order;

import org.apache.commons.lang.math.RandomUtils;

public class Tests {

	public static void main(String[] args) {
		System.out.println(Long.MAX_VALUE);
		
		System.out.println(System.currentTimeMillis()*(1000000));
		System.out.println(System.currentTimeMillis()*(1000000)+(123456789%900+100)*1000+RandomUtils.nextInt(10000));
		System.out.println(System.currentTimeMillis());
	}
}
