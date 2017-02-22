package cn.com.didi.core.select;

import java.util.Collection;
import java.util.Map;

public interface ISelector<T,V> {
	/**
	 * @param eles
	 * @param context
	 * @return
	 */
	public T select(T[] eles,V context);
	/**
	 * @param eles
	 * @param context
	 * @return
	 */
	public T select(Collection<? extends T> eles,V context);
	/**
	 * @param eles
	 * @param context
	 * @return
	 */
	public T select(Map<?,? extends T> eles,V context);
	/**
	 * @param eles
	 * @param context
	 * @return
	 */
	public T select(Iterable<? extends T> eles,V context);
}
