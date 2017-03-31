package cn.com.didi.order;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import cn.com.didi.core.utils.Constans;
import cn.com.didi.core.utils.SignUtil;

public class AliSignTest {
	static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4XH5JHTlzPDGLX5tHNjYrPdYIDEByKZ3SWi5L940ynkNBUYi9ZWj5/AMdVgryDNclE6GhI7sOnv8pBVusihJspoGF5ECF30oOtFOTpx2Zx6HJCM6MEJcolPh4Wrk0mUqH++R6V9fFSBhgTUUfdnPPlK6de9ez2AHmaBNMmcaxVKh+B4fNvqRMMEtRfl9u+7twvF+uAXFvXfjOhexQOY7BDvNhpAI/4JKAZ+JYPZHLmsSDYfVsNCl5L2IKPz/U7OfpOryAPbYL4dovC2IBuXFKUEhDHetoQlQLruqU77sVE2F4cKnB+k3igW+EqCeLuwI280HEUMU7sZnfwIWZ5cWQIDAQAB";

	@Test
	public void testSign() throws Exception {
		PublicKey key = SignUtil.getPublickKeyFromX509("RSA", Base64.decodeBase64(publicKey));
		boolean result = SignUtil.verify(Constans.SHA256_WITH_RSA, key,
				"{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017032206333439\",\"auth_app_id\":\"2017032206333439\",\"charset\":\"utf-8\",\"timestamp\":\"2017-03-31 12:23:56\",\"total_amount\":\"0.01\",\"trade_no\":\"2017033121001004350231076001\",\"seller_id\":\"2088421700572637\",\"out_trade_no\":\"19\"}"
						.getBytes("utf-8"),
				Base64.decodeBase64(
						"Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw=="));
		System.out.println(result);
	}

	@Test
	public void testSignFile() throws Exception {
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
}
