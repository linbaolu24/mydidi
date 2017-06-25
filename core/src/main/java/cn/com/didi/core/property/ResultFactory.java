package cn.com.didi.core.property;

import java.util.HashMap;
import java.util.Map;

import cn.com.didi.core.excpetion.BaseRuntimeException;
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
		return new Result<T>( message.getMessage(),message.getCode(), null, obj);
	}
	
	public static <T> IResult<Map<String,T>> success(String key,T obj) {
		Map<String,T> map=new HashMap<>(1);
		map.put(key, obj);
		return success(map);
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
	
	public static <T> IResult<T> error(Message message,T obj) {
		return error(message.getCode(), message.getMessage(),obj);
	}
	public static <T> IResult<T> error(BaseRuntimeException message) {
		return error(message.getCode(), message.getMessage(),null);
	}
	
	public static <T> IResult<T> error(IResult<T> result) {
		return error(result.getCode(), result.getMessage());
	}
}
