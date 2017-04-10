package cn.com.didi.order;

import static cn.com.didi.domain.util.AlipayConstants.CHARSET;

import java.io.IOException;
import java.io.StringReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.util.AlipayConstants;

public class AliAsnySignTest {
	static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4XH5JHTlzPDGLX5tHNjYrPdYIDEByKZ3SWi5L940ynkNBUYi9ZWj5/AMdVgryDNclE6GhI7sOnv8pBVusihJspoGF5ECF30oOtFOTpx2Zx6HJCM6MEJcolPh4Wrk0mUqH++R6V9fFSBhgTUUfdnPPlK6de9ez2AHmaBNMmcaxVKh+B4fNvqRMMEtRfl9u+7twvF+uAXFvXfjOhexQOY7BDvNhpAI/4JKAZ+JYPZHLmsSDYfVsNCl5L2IKPz/U7OfpOryAPbYL4dovC2IBuXFKUEhDHetoQlQLruqU77sVE2F4cKnB+k3igW+EqCeLuwI280HEUMU7sZnfwIWZ5cWQIDAQAB";
	String signed = "{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017032206333439\",\"auth_app_id\":\"2017032206333439\",\"charset\":\"utf-8\",\"timestamp\":\"2017-03-31 12:23:56\",\"total_amount\":\"0.01\",\"trade_no\":\"2017033121001004350231076001\",\"seller_id\":\"2088421700572637\",\"out_trade_no\":\"19\"}";
	String signResult = "jRfGpFsnyyyqhNzCYiDks4RC75g5l229638PxgV0YpDrfT17opCE8268fa3unkKcScEdeSKKaOjHjvaJUPT1+NnVi+KEbAd9AVFwhT+A/ddZgeU9kl1o70Yvl4P34Xg6uV2wW71clYUljPanJ3NfRj0Z1mB0kHGN3AIw/hchQGER1jRg3tiTygchqA396XnlnRs4S6rf7glRgP1V2Ru5+psxFYSAXrgAGapnaw8uOaDlRJ8kpMe+gFomCuUjQN6/6jZ0zphC/IxUpfojQw2MVA5Le/nq0R+xSGdSQfnSn16zalDSHzqX9/za+bpx/91O1HxNtDysiFeCGefazDnEZw==";

	
	  @Test public void testSign() throws Exception {
		  String str=
				  signResult;
	   byte[] bytes=Base64.decodeBase64(str);
	  System.out.println(bytes.length);
	 
	  
	  }
	
	@Test
	public void testAsnySign() throws Exception { 
		System.out.println("  = testAsnySign = ");
		/*Map<String, String> map = stringToMap(getFile6());
		String charset = StringUtils.defaultIfEmpty(map.get(CHARSET), Constans.CHARSET_UTF_8);
		boolean isSuccess = false;
		isSuccess = AlipaySignature.rsaCheckV1(map, publicKey, charset, map.get(AlipayConstants.SIGN_TYPE));
		System.out.println(isSuccess);*/
		String content=getFile7();
		System.out.println(content);
		boolean result=AlipaySignature.rsaCheck(content, signResult,
				publicKey, "utf-8", "RSA2");
		System.out.println("result = "+result);
		PublicKey key = SignUtil.getPublickKeyFromX509("RSA", Base64.decodeBase64(publicKey));
	   result=SignUtil.verify(Constans.SHA256_WITH_RSA, key,content.getBytes("utf-8"),
				Base64.decodeBase64(
						signResult));
		System.out.println("result = "+result);
	}
	
	@Test
	public void testAsnySign2() throws AlipayApiException, IOException {
		Map map1 =getFile4();
		Map map2=new HashMap(map1);
		Map<String, String> map=map1;
		System.out.println(getSignCheckContentV1(map));
		String charset = StringUtils.defaultIfEmpty(map.get(CHARSET), Constans.CHARSET_UTF_8);
		boolean isSuccess = false;
		isSuccess = AlipaySignature.rsaCheckV1(map, publicKey, charset, map.get(AlipayConstants.SIGN_TYPE));
		System.out.println(isSuccess);
		
		map=map2;
		isSuccess = AlipaySignature.rsaCheckV2(map, publicKey, charset, map.get(AlipayConstants.SIGN_TYPE));
		System.out.println(isSuccess);
	}

	@Test
	public void file9ToFile8() throws AlipayApiException, IOException {
		System.out.println("  = file9ToFile8 = ");
		String content=getFile9();
		System.out.println(content);
		String content1=getFile8();
		System.out.println(content1);
		System.out.println(content.equals(content1));
	}
	
	
	public static Map<String, String> stringToMap(String str) {
		Map<String, String> map = new HashMap<>();
		String[] arrays = str.split("&|,\\s");
		for (int i = 0; i < arrays.length; i++) {
			String[] couple = arrays[i].trim().split("=");
			map.put(couple[0], couple.length >= 2 ? couple[1] : "");
		}
		return map;
	}

	String getFile3() throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream("file3"), "utf-8");
	}
	
	String getFile6() throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream("file6"), "utf-8");
	}
	
	String getFile7() throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream("file7"), "utf-8");
	}
	
	String getFile8() throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream("file8"), "utf-8");
	}
	
	String getFile9() throws IOException {
		return IOUtils.toString(getClass().getResourceAsStream("file9"), "utf-8");
	}

	Properties getFile4() throws IOException {
		Properties prs = new Properties();
		prs.load(new StringReader(IOUtils.toString(getClass().getResourceAsStream("file4.properties"), "utf-8")));
		return prs;
	}
	  public static String getSignCheckContentV1(Map<String, String> params) {
	        if (params == null) {
	            return null;
	        }

	        StringBuffer content = new StringBuffer();
	        List<String> keys = new ArrayList<String>(params.keySet());
	        Collections.sort(keys);

	        for (int i = 0; i < keys.size(); i++) {
	            String key = keys.get(i);
	            String value = params.get(key);
	            content.append((i == 0 ? "" : "&") + key + "=" + value);
	        }

	        return content.toString();
	    }
}
