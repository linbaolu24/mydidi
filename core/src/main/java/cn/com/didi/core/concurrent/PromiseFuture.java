package cn.com.didi.core.concurrent;

import java.util.concurrent.Future;

public interface PromiseFuture<V> extends Future<V> {

	PromiseFuture<V> setSuccess(V result);

	boolean trySuccess(V result);

	PromiseFuture<V> setFailure(Throwable cause);

	boolean tryFailure(Throwable cause);

	public void addListener(FutureListener<? extends Future<? super V>>... listeners);
}