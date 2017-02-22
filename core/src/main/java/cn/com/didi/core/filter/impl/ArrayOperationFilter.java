package cn.com.didi.core.filter.impl;

import cn.com.didi.core.filter.IFilter;

public class ArrayOperationFilter<T> implements IFilter<T> {
    private IFilter<T>[] internalArray;
    private boolean anyTrue;
    
	public ArrayOperationFilter(IFilter<T>[] internalArray, boolean anyTrue) {
		super();
		this.internalArray = internalArray;
		this.anyTrue = anyTrue;
	}

	public boolean filter(T obj) {
		boolean temp=false;
		for(int i=0;i<internalArray.length;i++){
			temp=internalArray[i].filter(obj)|temp;
			if(anyTrue&&temp){//如果任意为true
				return true;
			}else if(!anyTrue&&!temp){//如果所有都为true
				return false;
			}
		}
		return true;
	}
	
}
