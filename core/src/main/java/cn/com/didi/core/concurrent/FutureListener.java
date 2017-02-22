package cn.com.didi.core.concurrent;

import java.util.EventListener;
import java.util.concurrent.Future;

public interface FutureListener<F extends PromiseFuture<?>> extends EventListener {

	/**
	 * Invoked when the operation associated with the {@link Future} has been
	 * completed.
	 * 
	 * @param future
	 *            the source {@link Future} which called this callback
	 */
	void operationComplete(F future) throws Exception;
}
