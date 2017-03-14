package cn.com.didi.core.property;

import cn.com.didi.core.message.Message;
import cn.com.didi.core.property.impl.result.Result;

/**
 * @author xlm
 *
 */
public class ResultFactory {
	private static final IResult SUCCESS = new Result(null);

	public static <T> IResult<T> success(T obj) {
		return new Result<T>(obj);
	}

	public static <T> IResult<T> success(Message message, T obj) {
		return new Result<T>(message.getCode(), message.getMessage(), null, obj);
	}

	@SuppressWarnings("unchecked")
	public static <T> IResult<T> success() {
		return SUCCESS;
	}

	public static <T> IResult<T> error(String code, String message, T obj) {
		return new Result<T>(message, code, null, obj);
	}
	
	public static <T> IResult<T> error(String code, String message) {
		return error(code, message, null);
	}
	
	public static <T> IResult<T> error(Message message) {
		return error(message.getCode(), message.getMessage(), null);
	}
}
