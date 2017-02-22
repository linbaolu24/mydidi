package cn.com.didi.core.excpetion;

import java.util.List;

/**
 * @author xlm
 *
 */
public class BaseRuntimeException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3012256265475473878L;
	private String code;
	private Object[] arg;
	public BaseRuntimeException() {
        super();
    }

    public BaseRuntimeException(String code, String message, Throwable cause, Object... arg) {
        super(message, cause);
        setCode(code);
        setArg(arg);
    }

    public BaseRuntimeException(String code, String message, Object... arg) {
        super(message);
        setCode(code);
        setArg(arg);
    }

    public BaseRuntimeException(String code, Throwable cause) {
        this(code, cause, null);
    }

    public BaseRuntimeException(String code, Throwable cause, Object... arg) {
        super(cause);
        setCode(code);
        setArg(arg);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the arg
     */
    public Object[] getArg() {
        return arg;
    }

    /**
     * @param arg the arg to set
     */
    public void setArg(Object[] arg) {
        this.arg = arg;
    }

    /**设置变长参数
     * @param arg
     */
    public void setVarArg(Object... arg) {
        setArg(arg);
    }

    public <T> void setArg(List<T> argList) {
        setArg(argList.toArray(new Object[0]));
    }
}
