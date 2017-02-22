package cn.com.didi.core.property;

public interface IResult<T> {
    String SUCCESS_CODE="200";
	/**
	 * @return
	 */
	boolean success();
	/**
	 * @return
	 */
	T getData();
	/**
	 * @return
	 */
	Exception exception();
	/**
	 * 代码
	 * @return
	 */
	String getCode();
	/**
	 * 消息
	 * @return
	 */
	String getMessage();
	/**
	 * @param visitor
	 */
	void visit(IResultVisitor visitor);
}
