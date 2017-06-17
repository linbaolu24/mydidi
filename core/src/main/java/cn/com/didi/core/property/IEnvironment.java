package cn.com.didi.core.property;

/**
 * @author xlm
 *
 */
public interface IEnvironment {
	public String getProperty(String key);
	public void changePropertyValue(String key,String value);
}
