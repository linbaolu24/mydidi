package cn.com.didi.core.property;

/**
 * 身份信息
 * @author xlm
 *
 */
public interface Identity<T>{
	public String getDescription();
	public String getType();
	public T getData();
	public boolean match(Identity<T> other);
}
