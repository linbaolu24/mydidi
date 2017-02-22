package cn.com.didi.core.property;

public interface IConverter<S,T> {
	T convert(S source);
}
