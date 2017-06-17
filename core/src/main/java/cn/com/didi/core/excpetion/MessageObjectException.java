package cn.com.didi.core.excpetion;

import cn.com.didi.core.message.Message;

public class MessageObjectException extends BaseRuntimeException {
    //private Message msg;

    /**
	 * 
	 */
	private static final long serialVersionUID = -452695270430384012L;
	public MessageObjectException(Message msg) {
        super(msg.getCode(), msg.getMessage());
       // this.msg = msg;
    }

    public MessageObjectException(Message msg, Throwable e) {
        super(msg.getCode(), msg.getMessage(), e);
        //this.msg = msg;
    }
    public MessageObjectException(Message msg, Throwable e,Object[] arg) {
        super(msg.getCode(), msg.getMessage(arg), e);
        //this.msg = msg;
    }
    
    public MessageObjectException(Message msg, Object... arg) {
        this(msg, null,arg);
    }

	public MessageObjectException(String code, String message) {
		 super(code, message);
	}
}
