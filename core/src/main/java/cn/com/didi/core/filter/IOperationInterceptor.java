package cn.com.didi.core.filter;

import cn.com.didi.core.property.IResult;

/**
 * @author xlm
 *
 * @param <E> 表示方法操作
 * @param <T> 表示数据
 * @param <S> 表示来源
 */
public interface IOperationInterceptor<E extends Enum<E>,T,S> {
	<R> IResult<R> interceptor(E operation,T data,S source);
}
