package cn.com.didi.core.property;

/**
 * 可类型转换
 * 
 * @author xlm
 * 
 */
public interface ICastAble {
	/**
	 * 如果不能转到T类型 返回null
	 * @param clazz
	 * @return 
	 */
	public <T> T cast(Class<T> clazz);
	/**
	 * 如果支持该类型转换返回false
	 * @param clazz
	 * @return
	 */
	public <T> boolean support(Class<T> clazz);
}
