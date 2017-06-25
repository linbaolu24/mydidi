package cn.com.didi.core.tx;

/**
 * 事务内回掉
 * 
 * @author xlm
 *
 * @param <T>
 */
public interface TranscationalCallBack<T> {
	public static TranscationalCallBack NULL_TRANSCATIONALCALLBACK = new TranscationalCallBack() {

		@Override
		public void invoke(Object t) {

		}

	};

	default void invoeBefore(T t) {

	}

	public void invoke(T t);

	@Deprecated
	public default boolean callAfterTranscational(T t) {
		return true;
	}

	public static <S> TranscationalCallBack<S> nullTranscationalCallBack() {
		return NULL_TRANSCATIONALCALLBACK;
	}
}
