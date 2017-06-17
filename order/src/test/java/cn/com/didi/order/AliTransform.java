package cn.com.didi.order;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.gexin.fastjson.JSON;

public class AliTransform extends AlTestBase{
	public static void main(String[] args) throws AlipayApiException {
		AliTransform form =new AliTransform();
		AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",form.getAppid()
				,form.getPriKey(),"json","UTF-8",form.getAliPubKey(),"RSA2");
		//AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
		/*request.setBizContent("{" +
		"    \"out_biz_no\":\"3142321423432\"," +
		"    \"payee_type\":\"ALIPAY_LOGONID\"," +
		"    \"payee_account\":\"abc@sina.com\"," +
		"    \"amount\":\"12.23\"," +
		"    \"payer_show_name\":\"上海交通卡退款\"," +
		"    \"payee_real_name\":\"张三\"," +
		"    \"remark\":\"转账备注\"" +
		"  }");*/
		AlipayFundTransToaccountTransferModel model=new AlipayFundTransToaccountTransferModel();
		model.setAmount("0.1");
		model.setOutBizNo("444444");
		model.setPayeeType("ALIPAY_LOGONID");
		model.setPayeeAccount("273300540@qq.com");
		model.setPayerShowName("嘀嘀服务");
		model.setRemark("提现");
	    request.setBizModel(model);
		AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
		
		if(response.isSuccess()){
		System.out.println("调用成功");
		} else {
		System.out.println("调用失败");
		}
		System.out.println(JSON.toJSONString(response));
	}
}
