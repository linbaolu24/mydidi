package cn.com.didi.webBase.file.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.resource.IFileStorage;
import cn.com.didi.core.resource.impl.CloseAbleResource;

@RestController
public class FileController {
	@Resource
	protected IFileStorage storage;
	public IResult<Map> fileUpload(MultipartHttpServletRequest servlet) throws IOException{
		Iterator<String> ite=  servlet.getFileNames();
		if(!ite.hasNext()){
			ResultFactory.success(null);
		}
		Map<String,String> map=new HashMap<String,String>();
		String name=null;
		while(ite.hasNext()){
			MultipartFile file=servlet.getFile(name);
			String path=storage.storeFile(new CloseAbleResource(file.getInputStream()), file.getSize(), name);
			map.put(name, path);
		}
		return ResultFactory.success(map);
		
	}
}
