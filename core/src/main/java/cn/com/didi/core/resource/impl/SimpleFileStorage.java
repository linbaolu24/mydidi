package cn.com.didi.core.resource.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import cn.com.didi.core.resource.IFileStorage;
import cn.com.didi.core.resource.IResource;

public class SimpleFileStorage implements IFileStorage{
	private  File dic=new File(".");
	@Override
	public String storeFile(IResource<InputStream> in, long size, String path) throws IOException {
		String real=dic.getAbsolutePath()+"/"+System.currentTimeMillis()+"_"+path;

		FileUtils.writeByteArrayToFile(new File(real), IOUtils.toByteArray(in.getResource()));
		return real;
	}

	@Override
	public IResource<InputStream> getFile(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	public File getDic() {
		return dic;
	}

	public void setDic(File dic) {
		this.dic = dic;
	}

}
