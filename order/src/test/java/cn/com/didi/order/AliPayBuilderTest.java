package cn.com.didi.order;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.utils.SignUtil;
import cn.com.didi.domain.domains.AliPAyRequestDto;
import cn.com.didi.order.trade.util.AliPayBuilder;

public class AliPayBuilderTest {
	
	private static String KEY="MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQC1422R7W+cwSEj"
	+"hYBu/1ks0kdxAnWNodMRoHvc4YST9T6yqFGviRzckdLMf9QpmIylPgi9i5uqji7z"
	+"oZhMG32LFBQVz63MmBkMxKFyr+6P7nMCWrnC+8Bm+WU0DWZwwt0xG3/3Q7sWwTjj"
	+"3OOwMQe4ubeN/bzBcDICDYwxnQ4kLbggPnn/eZ3tC7VHuw5EFB9ebyV/QmWjL4B2"
	+"AIATR1feQq91eQSJUEVGmBtuq9ox9CkRgtdh2dpJQk7mZUHH1uK6HoKCc4MkPf1M"
	+"TUDKS2SIrC9HSsWfwcRTslyF3Xtmh7tTJOIGFc6ktOvpip+ZA+wexLq54mYsljvg"
	+"CMrO2II/AgMBAAECggEBAI+J3iNGNEmJY+SLa3KtEqu7emOlpC1UYqRLeNQ0KJM2"
	+"+/azvFsBooQylB+MVVtKJtwUm4Mx1HQBZu4fPXf6TdXez4by8N4/va0E08AVNPfj"
	+"HmHiRSypzgm1kPdGB+y+6x3g7NiuW8u2SnYQEKMv3j8aWM+2SSqCpwRVuqhAoDNN"
	+"iKPzQgg//5khfdv1rFQDl8mINSSvuctpp+fSH3pbWuAXpXR9GqvBeWmLnidooxc8"
	+"FisszAXQcXoH7PAdje7zIK/1gazWU86B1dzkxXpV0znhr8CuChS6LqwCsnvACnBc"
	+"mh/rWB0SBnjKWvvBwQo1LBvBrTfa4li6dO8jtgzhyokCgYEA2YYzxC/sbZoPjibM"
	+"fsOcZtUo8Ek4CTgRCTBC5dPMvx8r4kw5TTW4RffpqDshpbbGfoUrrk4MWwkOo6iz"
	+"VhWDSdWeg/TyPswBG+NU2TYbqA5S8jsYAsDvEg8d7gA4mRGcVHiB89JbEhq62mNT"
	+"+qC0lrnWkj7R1OBF0cEe2n54OE0CgYEA1g+V+T6hYT4dtnQbKXj+ap2PBDVWl0Qm"
	+"2W4XamaMduHp2Cn2vrjmrcslB41qNPT83SdnYAb8PeVZyujm7ZmK36y1pHHGqQtz"
	+"9JO9FsmLSRG6wbxdFBVViwKHC4Ir8bBkYMctJjPMTPxVX8F1jsUq0TCSdnZr2KbY"
	+"7rBgRzhV6rsCgYAfYCElYjg7Eua9fVEv061Ck+qExjrj8YdbiO+NUQCObMOar53w"
	+"ShhbV0gHsQfIbFWzOCg1yyIzG1pWNuYvO4klZD+qDvHGJwzd5et+LTTOB8rlD6sF"
	+"8qhJNY7+nl+LjKO892jfL29dbCeZiow5cWa6+vE9RPxlNTg86hs2LtnOXQKBgQCh"
	+"Nnm/jdCiWNKtz4GJWWtaRjEofTcqkxQjGoueAX92JSwlNW10M/LfHqhTqMg8++jY"
	+"cc2j/37G6s0WSRNqDAIP9JWUNG/mbg61OvLkj4NL04NwVvlOn8ad+5hTLYUE79CZ"
	+"Jkg1hjsuG7f8dsmfbZSSmV8kwoj0UKv+D21jFeV28wKBgQCDijkgqPGJJ7VLlOFW"
	+"WleeDwTUc26zDYBHliLx8OgTwyffeZ1qsv5m6w2mlM8HxqoGwQczmPu2FlgYTJzK"
	+"dE88UQly6QC2V8JtN83RCwBHindfYBueZLyzSZ0CbjfXDwzNg7ySNdM+tXBOBxv+"
	+"YX1M/rDBqjUwPvd/C52LY1jOdQ==";
	//private static String  KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAn4XH5JHTlzPDGLX5tHNjYrPdYIDEByKZ3SWi5L940ynkNBUYi9ZWj5/AMdVgryDNclE6GhI7sOnv8pBVusihJspoGF5ECF30oOtFOTpx2Zx6HJCM6MEJcolPh4Wrk0mUqH++R6V9fFSBhgTUUfdnPPlK6de9ez2AHmaBNMmcaxVKh+B4fNvqRMMEtRfl9u+7twvF+uAXFvXfjOhexQOY7BDvNhpAI/4JKAZ+JYPZHLmsSDYfVsNCl5L2IKPz/U7OfpOryAPbYL4dovC2IBuXFKUEhDHetoQlQLruqU77sVE2F4cKnB+k3igW+EqCeLuwI280HEUMU7sZnfwIWZ5cWQIDAQAB";
	public static void main(String[] args) throws Exception {
		System.out.println(KEY);
		PrivateKey pri=SignUtil.getPrivateKeyFromPKCS8("RSA",Base64.decodeBase64(KEY));
		AliPayBuilder builder=new AliPayBuilder();
		builder.appid("2017032206333439");
		builder.signType("RSA2");
		builder.version("1.0");
		builder.charset("utf-8");
		//builder.format("json");
		builder.bzout_trade_no(10000000000L);
		
		builder.totalAmount(5);
		builder.timestamp(new Date());
		
		
		builder.method("alipay.trade.app.pay");
		builder.notify_url("http://118.178.226.138/api/app/c/order/aliAsnyNotify");
		
		builder.bcbody("服务");
		builder.bcsubject("服务");
		builder.seller_id("2088421700572637");
		builder.product_code("QUICK_MSECURITY_PAY");
		AliPAyRequestDto dto=builder.build(pri);
		System.out.println(dto.getOrderInfo());
		testBuilder();
		
		
	}
	public static void main2(String[] args) {
		boolean result="f1hRHtsL6nvVjROIOzh8hLNSYrwdhe0O1XB0xTavbCcwR0BHOLKUonG1v/6bv98tpTTcEK4JWMfu9ZOl9FGBZyBHMK9H7Gsm2PheaeDJSoPLAwDOx6M96be9mLR88FCQaKzReQ46CPOXekObcisjZb/scrssuL0RedX1TeeCOEWJTFRd+Yk5stpqwn5T80prCgntjY8tdgKFENoWgu7RKX37o8kT66M1mCMxd792G6AASCozy6EwCGFPUYn7FUpskUBFN/aOW/cShJmC2bYqD6EHS7Apqs2PBqCmxsReSAi5bIeYn9IXpZ+JLJHq7L/LhOUBHXRAr2r3AATGAn668Q==".equals("f1hRHtsL6nvVjROIOzh8hLNSYrwdhe0O1XB0xTavbCcwR0BHOLKUonG1v/6bv98tpTTcEK4JWMfu9ZOl9FGBZyBHMK9H7Gsm2PheaeDJSoPLAwDOx6M96be9mLR88FCQaKzReQ46CPOXekObcisjZb/scrssuL0RedX1TeeCOEWJTFRd+Yk5stpqwn5T80prCgntjY8tdgKFENoWgu7RKX37o8kT66M1mCMxd792G6AASCozy6EwCGFPUYn7FUpskUBFN/aOW/cShJmC2bYqD6EHS7Apqs2PBqCmxsReSAi5bIeYn9IXpZ+JLJHq7L/LhOUBHXRAr2r3AATGAn668Q==");
		System.out.println(result);
	}
	
	
	
	
	public static void testBuilder(){
		String resultStatus="9000";
		String result="{\"alipay_trade_app_pay_response\":{\"code\":\"10000\",\"msg\":\"Success\",\"app_id\":\"2017032206333439\",\"auth_app_id\":\"2017032206333439\",\"charset\":\"utf-8\",\"timestamp\":\"2017-03-31 12:23:56\",\"total_amount\":\"0.01\",\"trade_no\":\"2017033121001004350231076001\",\"seller_id\":\"2088421700572637\",\"out_trade_no\":\"19\"},\"sign\":\"Cuj1SidUBzZGy3g/+VMSWua2pe1VMsMv44rSouTYBycQ9xdXdXLRPq+BK1+UPW+seXOsstrHwz0O+YWzsdcrE3/SLRWWuBVuhGcQsDorbHGfYOZt9RDMDy4gScbNLSJ5yAWgTA1cNdC1yU1BG/IgKx3jj161pRy7IHFfzJOQfGvf8e9pyLpodVmQcBHGUFDCUmN6jvZswU4fwYxec6bR3uyM2gRN6xToBDRE5qLk10ZepeXdC73ZSwW0PjIWpLLrJZ6Mzo1GFdzjTNAnnssJ10wGr6gr5NdsGPFoho8kkZyhB7eF4kgKhps3zAiPrMiRsj714Zz3AMb7k7j0vQA5nw==\",\"sign_type\":\"RSA2\"}";
		String  memo="";
		Map p=new HashMap();
		p.put("resultStatus", resultStatus);
		p.put("result", result);
		p.put("memo", memo);
		System.out.println(JSON.toJSONString(p));
	}
	
	
	
	
}
