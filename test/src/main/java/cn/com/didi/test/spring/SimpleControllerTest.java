package cn.com.didi.test.spring;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import cn.com.didi.test.annotion.FilePath;
import cn.com.didi.test.annotion.Ignore;
import cn.com.didi.test.annotion.Uri;

public class SimpleControllerTest extends BaseSpringWebTest {
	public void test(String filename, String url) throws Exception {
		String applyedFileName = applyFileName(filename);
		File file = new File(applyedFileName);
		if (!file.exists()) {
			throw new IOException(filename + " not exists");
		}
		String content = FileUtils.readFileToString(file);
		System.out.println("请求字符串\n" + content);
		content=preHandle(url,content);
		MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
		        .characterEncoding("UTF-8").accept(MediaType.APPLICATION_JSON).content(content)).andReturn();
		System.out.println("反馈报文\n" + result.getResponse().getContentAsString());
	}

	protected String applyFileName(String fileName) {
		return "src/test/resources/testData/" + fileName;
	}
	protected String preHandle(String url,String content){
		return content;
	}
	@Test
	public void testAllMethod() throws Exception {
		Class clazz = this.getClass();
		Method methods[] = clazz.getMethods();
		for (Method method : methods) {
			Uri uriAnnotation = method.getAnnotation(Uri.class);
			FilePath filePathAnnotation = method.getAnnotation(FilePath.class);
			if (null == uriAnnotation || null == filePathAnnotation) {
				continue;
			}
			if (filter(method)) {
				continue;
			}
			String uri = uriAnnotation.value();
			String filePath = filePathAnnotation.value();
			test(filePath, uri);
		}
	}

	protected boolean filter(Method method) {
		Ignore ignore=method.getAnnotation(Ignore.class);
		return ignore!=null&&ignore.value();
	}
}
