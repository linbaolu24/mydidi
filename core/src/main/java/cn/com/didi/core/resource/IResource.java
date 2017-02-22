package cn.com.didi.core.resource;

import java.io.Closeable;

public interface IResource<T> extends Closeable{
	T getResource();
}
