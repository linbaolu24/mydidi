package cn.com.didi.core.resource.impl;

import java.io.IOException;

import cn.com.didi.core.resource.IResource;

/**
 * @author xlm
 *
 * @param <T>
 */
public class ResourceHolder<T> implements IResource<T> {
	protected T resource;
	private boolean closed=false;
	
	public ResourceHolder(T resource) {
		super();
		this.resource = resource;
	}

	public void close() throws IOException {
		if(closed){
			return;
		}
		closed=true;
		closeInternal();
	}
	protected void closeInternal() throws IOException{
		
	}
	public T getResource() {
		return resource;
	}

}
