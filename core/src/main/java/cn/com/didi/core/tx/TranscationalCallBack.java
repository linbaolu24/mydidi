package cn.com.didi.core.tx;

/**
 * 事务内回掉
 * 
 * @author xlm
 *
 * @param <T>
 */
public interface TranscationalCallBack<T> {
	default void invoeBefore(T t){
		
	}
	public void invoke(T t);
	public default boolean callAfterTranscational(T t){
		return true;
	}
}
