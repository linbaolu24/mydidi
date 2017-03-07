package cn.com.didi.core.resource.util;

import cn.com.didi.core.resource.IResource;

public abstract class ResourceUtil {
	public static <T> void close(IResource<T> resource) {
		try {
			resource.close();
		} catch (Exception e) {

		}
	}
}
