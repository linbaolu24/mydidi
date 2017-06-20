package cn.com.didi.message;

/**
 * @author xlm
 *
 */
public interface IMessage<T> {
	/**
	 * 消息类型
	 * @return
	 */
	public String getType();
	/**
	 * 获取消息ID
	 * @return
	 */
	public String messageId();
	/**
	 * 获取分类
	 * @return
	 */
	public String getCategory();
	/**
	 * <p>获取数据</p>
	 * @return
	 */
	public T getData();
}
