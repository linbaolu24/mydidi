package cn.com.didi.core.callBack;

public interface ICallBack<T,R> {
	/**
	 * @param param
	 * @return
	 */
	R call(T param,Object... args);
}
