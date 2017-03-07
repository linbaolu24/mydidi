package cn.com.didi.thirdExt;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;

import cn.com.didi.core.resource.impl.CloseAbleResource;
import cn.com.didi.thirdExt.resource.QiniuFileStorage;

public class QiniuFileStorageTest {
	QiniuFileStorage store=new QiniuFileStorage();
	@Before
	public void setUp(){
		store.setAccessKey("a9dqbEJ1HZXpTYNykMamw1km2mbD45WLwO2sWzyX");
		store.setSecretKey("s4MU_ZMvKNGd3ZNa2pH58Uqyiy_eWKBUxpjP2QEc");
		store.setZone(2);
		store.setBucket("didi");
		store.setIndividual(false);
		store.setDownLoadUrl("http://olrp4dx0c.bkt.clouddn.com");
		store.init();
	}
	@Test
	public void testFile() throws IOException{
		FileInputStream fi=new FileInputStream("D:/ttt.jpg");
		CloseAbleResource<InputStream> resource=new CloseAbleResource<InputStream>(fi);
		String str=store.storeFile(resource, -1, null);
		System.out.println(str);
	}
}
