package cn.com.didi.core.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件存储
 * @author xlm
 *
 */
public interface IFileStorage {
	/**
	 * @param in 输入流
	 * @param size 输入大小
	 * @param path 
	 * @return 返回真正的位置
	 */
	String storeFile(IResource<InputStream> in,long size,String path) throws IOException;
	
	/**
	 * @param path
	 * @return
	 */
	IResource<InputStream> getFile(String path);
}
