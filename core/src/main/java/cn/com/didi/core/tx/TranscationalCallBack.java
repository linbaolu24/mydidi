package cn.com.didi.core.tx;

/**
 * 事务内回掉
 * 
 * @author xlm
 *
 * @param <T>
 */
public interface TranscationalCallBack<T> {
	public void invoke(T t);
}
