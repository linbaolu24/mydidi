package cn.com.didi.webBase.file.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.resource.IFileStorage;
import cn.com.didi.core.resource.impl.CloseAbleResource;
import cn.com.didi.core.resource.util.ResourceUtil;

@RestController
public class FileController {
	@Resource
	protected IFileStorage storage;

	@RequestMapping(value = "/file/fileUpload", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public IResult<Map> fileUpload(MultipartHttpServletRequest servlet) throws IOException {
		Iterator<String> ite = servlet.getFileNames();
		if (!ite.hasNext()) {
			ResultFactory.success(null);
		}
		Map<String, String> map = new HashMap<String, String>();
		String name = null;
		CloseAbleResource resource;
		while (ite.hasNext()) {
			name = ite.next();
			MultipartFile file = servlet.getFile(name);
			resource = new CloseAbleResource(file.getInputStream());
			String path = storage.storeFile(resource, file.getSize(), file.getOriginalFilename());
			ResourceUtil.close(resource);
			map.put(name, path);
		}
		return ResultFactory.success(map);

	}
}
