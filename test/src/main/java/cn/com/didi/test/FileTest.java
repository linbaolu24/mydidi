package cn.com.didi.test;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
public class FileTest {
	public static void main(String[] args) throws IOException {
		byte[] bytes=FileUtils.readFileToByteArray(new File("D:/catalina.out"));
		String str=Base64.getEncoder().encodeToString(bytes);
		System.out.println(str);
	}
}