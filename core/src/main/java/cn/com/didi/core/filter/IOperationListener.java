package cn.com.didi.core.filter;

/**
 * @author xlm
 *
 * @param <E>
 * @param <T>
 */
public interface IOperationListener<E extends Enum<E>, T> {
	
	
	void operate(E operation, T data);
	
}
