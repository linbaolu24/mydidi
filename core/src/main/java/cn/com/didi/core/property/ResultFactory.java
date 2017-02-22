package cn.com.didi.core.property;

import cn.com.didi.core.property.impl.result.Result;

/**
 * @author xlm
 *
 */
public class ResultFactory {
	public static <T> IResult<T> success(T obj){
		return new Result<T>(obj);
	}
}
