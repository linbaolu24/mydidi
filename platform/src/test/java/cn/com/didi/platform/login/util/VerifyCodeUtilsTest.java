package cn.com.didi.platform.login.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.Test;

public class VerifyCodeUtilsTest {
	@Test
	public void testGenerateVerifyCode() throws IOException{
		String code=VerifyCodeUtils.generateVerifyCode(4);
		 //生成图片 
        int w = 100, h = 30; 
        
        FileOutputStream fi=new FileOutputStream(new File("D:/ttt.jpg"));
        VerifyCodeUtils.outputImage(w, h, fi, code); 
        System.out.println(code);
        fi.close();
	}
}
