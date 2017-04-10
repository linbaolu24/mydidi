package cn.com.didi.order;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.domains.AliSynResultDto;
import cn.com.didi.domain.domains.AlipayTradeAppPayResponseDto;

public class AliSignTest {
	static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4XH5JHTlzPDGLX5tHNjYrPdYIDEByKZ3SWi5L940ynkNBUYi9ZWj5/AMdVgryDNclE6GhI7sOnv8pBVusihJspoGF5ECF30oOtFOTpx2Zx6HJCM6MEJcolPh4Wrk0mUqH++R6V9fFSBhgTUUfdnPPlK6de9ez2AHmaBNMmcaxVKh+B4fNvqRMMEtRfl9u+7twvF+uAXFvXfjOhexQOY7BDvNhpAI/4JKAZ+JYPZHLmsSDYfVsNCl5L2IKPz/U7OfpOryAPbYL4dovC2IBuXFKUEhDHetoQlQLruqU77sVE2F4cKnB+k3igW+EqCeLuwI280HEUMU7sZnfwIWZ5cWQIDAQAB";
	String signed="{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017032206333439\",\"auth_app_id\":\"2017032206333439\",\"charset\":\"utf-8\",\"timestamp\":\"2017-03-31 12:23:56\",\"total_amount\":\"0.01\",\"trade_no\":\"2017033121001004350231076001\",\"seller_id\":\"2088421700572637\",\"out_trade_no\":\"19\"}";
	String signResult="Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw==";
	@Test
	public void testSign() throws Exception {
		PublicKey key = SignUtil.getPublickKeyFromX509("RSA", Base64.decodeBase64(publicKey));
		boolean result = SignUtil.verify(Constans.SHA256_WITH_RSA, key,signed.getBytes("utf-8"),
				Base64.decodeBase64(
						"Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw=="));
		String fileContent=getFile();
		System.out.println(fileContent);
		System.out.println(signed.equals(fileContent));
		System.out.println("Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw==".equals("Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw=="));
		System.out.println(result);
		result=SignUtil.verify(Constans.SHA256_WITH_RSA, key,fileContent.getBytes("utf-8"),
				Base64.decodeBase64(
						"Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw=="));
		System.out.println(result);
	}
	@Test public void test4() throws IOException{
		System.out.println("====test4====");
		String file2=getFile2();
		AliSynResultDto dto=JSON.parseObject(file2, AliSynResultDto.class);
		AlipayTradeAppPayResponseDto respones=JSON.parseObject(dto.getResult(), AlipayTradeAppPayResponseDto.class);
		System.out.println(respones.getAlipay_trade_app_pay_response());
		System.out.println(signed);
		System.out.println(respones.getAlipay_trade_app_pay_response().equals(signed));
		System.out.println(respones.getSign().equals(signResult));
		JSONObject jo=(JSONObject) JSONObject.parse(file2);
		System.out.println(jo);
		System.out.println(jo.getJSONObject("result").getString("alipay_trade_app_pay_response"));
		System.out.println("====net.sf.json.JSONObject2222====");
		net.sf.json.JSONObject joo=net.sf.json.JSONObject.fromObject(file2);
		System.out.println(joo);
		String result=joo.getString("result");
		System.err.println("======net.sf.json.JSONObject.result========");
		System.out.println(result);
		
		net.sf.json.JSONObject joo2=net.sf.json.JSONObject.fromObject(result);
		String result2=joo2.getString("alipay_trade_app_pay_response");
		System.out.println(result2);
		
		/*joo2=net.sf.json.JSONObject.fromObject(result2);
		
		System.out.println("======net.sf.json.JSONObject.result_JOO2========"+joo2);*/
		AlipayTradeAppPayResponseDto ali=(AlipayTradeAppPayResponseDto) joo2.toBean( joo2,AlipayTradeAppPayResponseDto.class);
		System.out.println(ali);
		System.err.println(result2.equals(signed));
		
	}
	
	
	@Test
	public void testSignFile() throws Exception {
		System.out.println("====testSignFile====");
		String source = FileUtils.readFileToString(new File("file.txt"));
		PublicKey key = SignUtil.getPublickKeyFromX509("RSA", Base64.decodeBase64(publicKey));
		boolean result = SignUtil.verify(Constans.SHA256_WITH_RSA, key, source.getBytes("utf-8"), Base64.decodeBase64(
				"XL0CFD8wmEO019+RNJhGYtQ+lvP5WuM00xBSkoz+/Rl4qroSxviFaYY4ocPVMKP3aYmYKf4bEM86gq9n868++IcX3rDgBmT8zTDjpSuQkTHLh6Przlj64X5e8mDaVNmy72Rh+6xDPETSCi7ScacT4JO5EWR2J3o4hhioOloJ2u1gTatIqWi0ZyB5/CWSXxPEGzX5eTxtfdNMkZEDxN9PWnOV0p2UJ8e7zcnJvBmfdfEvkzpNdKbww1vTfYpgPLMiL4xmpPCA2EpZjX0tf7+rg3BDQdKhzone1MRENZxfe3HaNQeJhBgxXprdsBXiiVwO+e0sz9GS4Qx3Z2xDZvCSyQ=="));
		System.out.println(result);
		KeyPairGenerator generator =  KeyPairGenerator.getInstance("RSA");
		KeyPair pair = generator.generateKeyPair();
		byte[] bytes=SignUtil.sign(Constans.SHA256_WITH_RSA, pair.getPrivate(), source.getBytes());
		System.out.println(SignUtil.verify(Constans.SHA256_WITH_RSA, pair.getPublic(),  source.getBytes(), bytes));
	}
	@Test
	public  void test3 () {
		System.out.println("====TEST3====");
		boolean result=publicKey.equals("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4XH5JHTlzPDGLX5tHNjYrPdYIDEByKZ3SWi5L940ynkNBUYi9ZWj5/AMdVgryDNclE6GhI7sOnv8pBVusihJspoGF5ECF30oOtFOTpx2Zx6HJCM6MEJcolPh4Wrk0mUqH++R6V9fFSBhgTUUfdnPPlK6de9ez2AHmaBNMmcaxVKh+B4fNvqRMMEtRfl9u+7twvF+uAXFvXfjOhexQOY7BDvNhpAI/4JKAZ+JYPZHLmsSDYfVsNCl5L2IKPz/U7OfpOryAPbYL4dovC2IBuXFKUEhDHetoQlQLruqU77sVE2F4cKnB+k3igW+EqCeLuwI280HEUMU7sZnfwIWZ5cWQIDAQAB");
	    System.out.println(result);
	}
	 String getFile() throws IOException{
		return IOUtils.toString(getClass().getResourceAsStream("file"), "utf-8");
	}
	 String getFile2() throws IOException{
			return IOUtils.toString(getClass().getResourceAsStream("file2"), "utf-8");
		}
}
