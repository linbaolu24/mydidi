package cn.com.didi.core.filter.impl;

import cn.com.didi.core.filter.IFilter;

public class NotFilter<T> implements IFilter<T> {
	IFilter<T> internalFilter;
	
	public NotFilter(IFilter<T> internalFilter) {
		super();
		this.internalFilter = internalFilter;
	}

	public boolean filter(T obj) {
		return !internalFilter.filter(obj);
	}

}
