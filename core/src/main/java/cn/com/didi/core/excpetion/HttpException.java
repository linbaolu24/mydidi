package cn.com.didi.core.excpetion;

import cn.com.didi.core.message.Message;

public class HttpException extends MessageObjectException {
	public int getStatu() {
		return statu;
	}

	public void setStatu(int statu) {
		this.statu = statu;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	private int statu;
	private String reason;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3692293954275457606L;

	public HttpException(Message msg, int code, String reason) {
		super(msg);
		this.statu = code;
		this.reason = reason;
	}

	public HttpException(Message msg, Throwable e, int code, String reason) {
		super(msg, e);
		this.statu = code;
		this.reason = reason;
	}

	public HttpException(Message msg, Throwable e, Object[] arg, int code, String reason) {
		super(msg, e, arg);
		this.statu = code;
		this.reason = reason;
	}
    
}
