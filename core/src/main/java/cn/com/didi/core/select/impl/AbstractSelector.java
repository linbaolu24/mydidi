package cn.com.didi.core.select.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import cn.com.didi.core.select.ISelector;

/**
 * @author xlm
 *
 * @param <T>
 * @param <V>
 */
public abstract class AbstractSelector<T,V> implements ISelector<T, V>{

	public T select(T[] eles, V context) {
		return select(Arrays.asList(eles),context);
	}

	public  T select(Collection<? extends T> eles, V context){
		if(eles==null&&eles.isEmpty()){
			return null;
		}
		return selectInternal(eles, context);
		
	}
	protected abstract T selectInternal(Collection<? extends T> eles, V context);
	public T select(Map<?, ? extends T> eles, V context) {
		return select(eles.values(), context);
	}

	public T select(Iterable<? extends T> eles, V context) {
		if(eles==null){
			return null;
		}
		ArrayList<T> list=new ArrayList<T>(4);
		for(T one:eles){
			list.add(one);
		}
		return select(eles, context);
	}

}
