package cn.com.didi.message;

/**
 * @author xlm
 *
 */
public interface IMessage {
	/**
	 * 消息类型
	 * @return
	 */
	public String getType();
	/**
	 * <p>获取数据</p>
	 * @return
	 */
	public Object getData();
}
