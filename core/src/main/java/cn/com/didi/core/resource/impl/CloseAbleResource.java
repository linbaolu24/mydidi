package cn.com.didi.core.resource.impl;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author xlm
 *
 * @param <T>
 */
public class CloseAbleResource<T extends Closeable> extends ResourceHolder<T>{

	public CloseAbleResource(T resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}
	protected void closeInternal() throws IOException {
		resource.close();
	}

}
