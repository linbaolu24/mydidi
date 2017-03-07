package cn.com.didi.core.excpetion;

import cn.com.didi.core.message.Message;

/**
 * 
 * <p>标题:</p>
 * <p>描述:</p>
 * <p>版权: 税友软件集团股份有限公司</p> 
 * <p>创建时间:2016年6月16日 </p> 
 * <p>作者：谢磊敏</p>
 * <p>修改历史记录：</p>
 */
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

	public MessageObjectException(String code, String message) {
		 super(code, message);
	}
}
