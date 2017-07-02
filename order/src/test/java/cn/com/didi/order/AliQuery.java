package cn.com.didi.order;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;

public class AliQuery extends AlTestBase{
	public static void main(String[] args) throws AlipayApiException {
		AliQuery form =new AliQuery();
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",form.getAppid()
				,form.getPriKey(),"json","UTF-8",form.getAliPubKey(),"RSA2");
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		/*request.setBizContent("{" +
		"\"out_trade_no\":\"20150320010101001\"," +
		"\"trade_no\":\"2014112611001004680 073956707\"" +
		"  }");*/
		AlipayTradeQueryModel model=new AlipayTradeQueryModel();
		model.setOutTradeNo("85");
		request.setBizModel(model);
		AlipayTradeQueryResponse response = alipayClient.execute(request);
		System.out.println(JSON.toJSONString(response));
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
	}
}
