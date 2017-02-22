package cn.com.didi.core.property;

/**
 * @author xlm
 *
 * @param <T>
 */
public interface IResultVisitor {
	<T> void accept(IResult<T> result);
}
