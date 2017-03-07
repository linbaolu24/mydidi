package cn.com.didi.core.property.impl.result;

import java.io.Serializable;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.IResultVisitor;

public class Result<T> implements IResult<T>,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6399731157171531964L;
	private String message;
	private String code;
	private Exception exception;
	private T data;

	public Result(String message, String code, Exception exception, T data) {
		super();
		this.message = message;
		this.code = code;
		this.exception = exception;
		this.data = data;
	}
	public Result(T data){
		this("",SUCCESS_CODE,null,data);
	}
	@Override
	public boolean success() {
		return SUCCESS_CODE.equals(code);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Exception exception() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public void visit(IResultVisitor visitor) {
		visitor.accept(this);

	}

}
