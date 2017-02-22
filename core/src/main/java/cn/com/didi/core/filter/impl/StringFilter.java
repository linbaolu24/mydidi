package cn.com.didi.core.filter.impl;

import cn.com.didi.core.filter.IFilter;

/**
 * 字符串匹配
 * @author xlm
 *
 */
public class StringFilter implements IFilter<String>{
	private String match;
	public boolean filter(String obj) {
		if(match==obj){
			return true;
		}
		if(match==null){
			return false;
		}
		return match.equals(obj);
	}

}
