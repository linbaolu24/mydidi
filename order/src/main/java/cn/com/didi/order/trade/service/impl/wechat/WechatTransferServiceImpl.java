package cn.com.didi.order.trade.service.impl.wechat;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.message.Message;
import cn.com.didi.core.property.IConverter;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.domains.WechatPayCustomerReqVo;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.domains.wechat.AWechatCodeDto;
import cn.com.didi.domain.domains.wechat.AccessTokenDto;
import cn.com.didi.domain.domains.wechat.AccessTokenOpenIdDto;
import cn.com.didi.domain.domains.wechat.WechatUserInfo;
import cn.com.didi.domain.util.ABeanUtils;
import cn.com.didi.domain.util.SignatureUtils;
import cn.com.didi.order.trade.service.IWechatTransferService;
import cn.com.didi.order.trade.util.WechatXmlUtil;
import cn.com.didi.order.util.OrderMessageConstans;
import cn.com.didi.thirdExt.http.IHttpService;
import cn.com.didi.thirdExt.http.JsonGetHttpHandler;
import cn.com.didi.thirdExt.http.StringHttpHandler;
import cn.com.didi.thirdExt.produce.IAppEnv;
@Service
public class WechatTransferServiceImpl implements IWechatTransferService {
	private static Logger LOGGER = LoggerFactory.getLogger(WechatTransferServiceImpl.class);
	@Resource
	protected IHttpService httpService;
	@Resource
	protected IAppEnv appEnv;
	private static final String UNION_ACCESS_URL="https://api.weixin.qq.com/sns/oauth2/access_token?appid=${APPID}&secret=${SECRET}&code=${CODE}&grant_type=authorization_code";
	private static final String UNION_USER_URL="https://api.weixin.qq.com/sns/userinfo?access_token=${ACCESS_TOKEN}&openid=${OPENID}&lang=zh_CN";
	
	private static final String OPEN_UNION_USER_URL="https://api.weixin.qq.com/cgi-bin/user/info?access_token=${ACCESS_TOKEN}&openid=${OPENID}&lang=zh_CN";
	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${APPID}&secret=${SECRET}";
	private static final String ORDER_QUERY="https://api.mch.weixin.qq.com/pay/orderquery";
	private static  URI ORDER_QUERY_URI;
	static{
		try {
			ORDER_QUERY_URI=new URI(ORDER_QUERY);
		} catch (URISyntaxException e) {
			LOGGER.error(e.getMessage(),e);
			throw new RuntimeException(e);
		}
	}
	protected IFilter<Field> field = new IFilter<Field>() {

		@Override
		public boolean filter(Field obj) {//如果为true表示要过滤
			return Modifier.isStatic(obj.getModifiers())||signMap.containsKey(obj.getName());//签名过滤
		}
	};
	protected IConverter<String, String> convert=new IConverter<String, String>() {
		
		@Override
		public String convert(String source) {
			String value=transMap.get(source);
			if(StringUtils.isEmpty(value)){
				return source;
			}
			if("N".equals(value)){
				return null;
			}
			return value;
		}
	};
	
	/**
	 * 用于app支付的命名转换
	 */
	protected IConverter<String, String> payConvert=new IConverter<String, String>() {
		
		@Override
		public String convert(String source) {
			String value=payMap.get(source);
			if(StringUtils.isEmpty(value)){
				return source;
			}
			if("N".equals(value)){
				return null;
			}
			return value;
		}
	};
	protected Map<String,String> signMap=new HashMap<>();
	protected Map<String,String> payMap;
	protected Map<String,String> transMap;
	{
		payMap=new HashMap<>();
		payMap.put("amount", "total_fee");
		payMap.put("partner_trade_no", "out_trade_no");
		payMap.put("signKey", "N");

		transMap=new HashMap<>();
		transMap.put("appid", "mch_appid");
		transMap.put("mch_id","mchid");
		transMap.put("signKey","N");
		//signMap.put("mch_appid", "Y");
		signMap.put("cost", "Y");
		signMap.put("sign", "Y");
		signMap.put("signKey", "Y");
		signMap.put("source", "Y");
	}
	@Override
	public IResult<WechatPayCustomerReturnVo> transferAppPay(WechatPayCustomerReqVo reqVo) {
		try {
			
			return transferInternal(reqVo,  appEnv.getWechatAppPayURI(),payConvert,true);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_BUILD_REQUEST_ERROR);
		}catch(DocumentException |ParseException|IOException e){
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_PARSE_RESPONSE_ERROR);
		}
	}
	@Override
	public IResult<WechatPayNotifyReturnVO> transferAppPayQuery(WechatPayCustomerReqVo reqVo) {
       try {
			if(StringUtils.isEmpty(reqVo.getSign())&&!StringUtils.isEmpty(reqVo.getSignKey())){
				reqVo.setSign(getAppPaySign(reqVo, reqVo.getSignKey(), appEnv.getWechatCharSet()));
			}
			return transferInternal(reqVo,ORDER_QUERY_URI,payConvert,true,WechatPayNotifyReturnVO::new);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TRADE_QUERY_EXCEPTION);
		}catch(DocumentException |ParseException|IOException e){
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TRADE_QUERY_PARE_RES_EXCEPTION);
		}
	}
	@Override
	public String getAppPaySign(WechatPayCustomerReqVo req, String signKey,String charset) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		//req.setTrade_type("JSAPI");
		//req.setAppid("wxe29a2f519cf39295");
		//req.setTrade_type("APP");
		//req.setAppid("wxf0f6836240fdaf3e");
		return SignatureUtils.getPaySign(req, signKey, charset, field, payConvert);
	}
	@Override
	public String getAppTransferSign(WechatPayCustomerReqVo req, String signKey, String charSet)
			throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException {
		return SignatureUtils.getPaySign(req, signKey, charSet, field, convert);
	}
	protected <T extends WechatPayCustomerReturnVo> IResult<T> transferInternal(WechatPayCustomerReqVo reqVo,URI url,IConverter<String, String> nameConvert,boolean pay,Supplier<T> sipplier) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, IOException, DocumentException{
		LOGGER.debug("微信交易请求对象{},url:{}",reqVo,url);
		String str = ABeanUtils.transBean2Xml(reqVo,nameConvert,pay);
		LOGGER.debug("微信交易请求对象{},报文{}",reqVo,str);
		WechatStringHttpHandler handler=new WechatStringHttpHandler(str,url);
		httpService.post(handler);
		String response=handler.getResponseAndThrow();
		LOGGER.debug("url:{},微信返回{}",url,response);
		Map<String, Object> map=WechatXmlUtil.parse(response);
		//WechatPayCustomerReturnVo vo=new WechatPayCustomerReturnVo();
		T vo=sipplier.get();
		BeanUtils.copyProperties(vo, map);
		LOGGER.debug("微信返回对象{}",vo);
		IResult<T> result=handleResult(vo);
		if(result!=null){
			return result;
		}
		return ResultFactory.success(vo);
	}
	protected IResult<WechatPayCustomerReturnVo> transferInternal(WechatPayCustomerReqVo reqVo,URI url,IConverter<String, String> nameConvert,boolean pay) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, IOException, DocumentException {
		return transferInternal(reqVo, url, nameConvert, pay,WechatPayCustomerReturnVo::new);
	}
	@Override
	public IResult<WechatPayCustomerReturnVo> transferForTransferAccounts(WechatPayCustomerReqVo reqVo) {
		try {
			return transferInternal(reqVo, appEnv.getWechatTransferURI(),convert,false);
		}  catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TRANSFER_BUILD_REQUEST_ERROR);
		}catch(DocumentException |ParseException|IOException e){
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TRANSFER_PARSE_RESPONSE_ERROR);
		}
	}
	
	
	
	protected <T> IResult<T> handleResult(WechatPayCustomerReturnVo vo){
		return null;
	}
	public static class WechatStringHttpHandler extends StringHttpHandler {
		IOException e;
		RuntimeException runE;
		public WechatStringHttpHandler(String request,URI uri){
			setRequest(request);
			setUri(uri);
		}
		@Override
		public void forResponse(HttpResponse post) {
			try {
				super.forResponseInternal(post);
			} catch (IOException e) {
				LOGGER.error(e.getMessage(),e);
				this.e=e;
			}catch(ParseException e){
				LOGGER.error(e.getMessage(),e);
				this.runE=e;
			}
		}
		public String getResponseAndThrow() throws IOException   {
			if(e!=null){
				throw e;
			}
			if(runE!=null){
				throw runE;
			}
			return super.getResponse();
		}
		
	}
	@Override
	public IResult<WechatPayNotifyReturnVO> parseNotifyResponse(String xml) {
		Map<String, Object> map;
		try {
			
			map = WechatXmlUtil.parse(xml);
			WechatPayNotifyReturnVO vo=new WechatPayNotifyReturnVO();
			BeanUtils.copyProperties(vo, map);
			vo.setSource(xml);
			return ResultFactory.success(vo);
		} catch (DocumentException | IllegalAccessException | InvocationTargetException e) {
			LOGGER.error(e.getMessage(),e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_PAY_NOTIFY_PARSE_RESPONSE_ERROR);
		}
		
	}
	@Override
	public boolean verifySign(WechatPayNotifyReturnVO vo) {
		try {
			return SignatureUtils.verifySign(vo, appEnv.getWechatAppSignedkey(), appEnv.getWechatCharSet(), vo.getSign(), field,null);
		} catch (IllegalArgumentException | IllegalAccessException | UnsupportedEncodingException e) {
			LOGGER.error(e.getMessage(),e);
			return false;
		}
	}

	@Override
	public AccessTokenOpenIdDto getUnionAccess(String appid, String secret, String code) {
		Map<String, Object> parms = new HashMap<String, Object>();
		parms.put("APPID", appid);
		parms.put("SECRET", secret);
		parms.put("CODE", code);
		JsonGetHttpHandler<AccessTokenOpenIdDto> handler = new JsonGetHttpHandler(parms, AccessTokenOpenIdDto.class,
				UNION_ACCESS_URL);
		try {
			httpService.get(handler);
			return handler.getResultAndThrow();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new MessageObjectException(OrderMessageConstans.WECHAT_GET_ACCESS_CODE_ERROR);
		}
	}

	@Override
	public WechatUserInfo getUser(String url, String accessToken, String openId) {
		if (StringUtils.isEmpty(url)) {
			url=OPEN_UNION_USER_URL;
		}
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("ACCESS_TOKEN", accessToken);
		parms.put("OPENID", openId);
		return getUrl(parms, WechatUserInfo.class, url, OrderMessageConstans.WECHAT_GET_USER_INFO_ERROR);

	}

	@Override
	public WechatUserInfo getUserFromCode(String appid, String secret, String code) {
		AccessTokenOpenIdDto dto=getUnionAccess(appid, secret, code);
		if(!dto.normalSuccess()){
			LOGGER.error("获取access微信返回代码错误{}",dto);
			throw new MessageObjectException(OrderMessageConstans.WECHAT_GET_ACCESS_CODE_ERROR);
		}
		WechatUserInfo info= getUser(UNION_USER_URL,dto.getAccess_token(), dto.getOpenid());
		if(!info.normalSuccess()){
			LOGGER.error("获取用户信息微信返回代码错误{}",info);
			throw new MessageObjectException(OrderMessageConstans.WECHAT_GET_USER_INFO_ERROR);
		}
		return info;
	}
	@Override
	public AccessTokenDto getAccessToken(String appId, String secret) {
		Map<String, String> parms = new HashMap<String, String>();
		parms.put("APPID", appId);
		parms.put("SECRET", secret);
		return getUrl(parms, AccessTokenDto.class,ACCESS_TOKEN_URL, OrderMessageConstans.WECHAT_GET_ACCESS_TOKEN_ERROR);
	}

	protected <T extends AWechatCodeDto> T getUrl(Map<String, String> params, Class<T> target,String url, Message error) {
		JsonGetHttpHandler<T> handler = new JsonGetHttpHandler(params, target, url);
		try {
			httpService.get(handler);
			T result = handler.getResultAndThrow();
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MessageObjectException(error);
		}
	}

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, UnsupportedEncodingException, InvocationTargetException, IntrospectionException {
		WechatTransferServiceImpl impl=new WechatTransferServiceImpl();
		/**appid=wxe29a2f519cf39295&body=嘀嘀服务-年费&mch_id=1482609172
		 * &nonce_str=32768&notify_url=https://118.178.226.138/api/app/trade/deposit/wechatAsnyNotify
		 * &out_trade_no=98&spbill_create_ip=115.238.29.228&total_fee=1&trade_type=JSAPI&key=a4IZm4bhhUw3qAxcwVZjaokJlNuVlPbc*/
		WechatPayCustomerReqVo reqVo=new WechatPayCustomerReqVo();
		reqVo.setAmount(1);
		reqVo.setAppid("wxf0f6836240fdaf3e");
		reqVo.setBody("嘀嘀服务商户端-年费");
		reqVo.setMch_id("1480906112");
		reqVo.setNonce_str("32768");
		reqVo.setNotify_url("https://118.178.226.138/api/app/trade/deposit/wechatAsnyNotify");
		reqVo.setPartner_trade_no("139");
		reqVo.setSpbill_create_ip("115.238.29.228");
		reqVo.setTrade_type("APP");
		//reqVo.setOpenid("odRaHwaX8GWHMFJcMXA_ehA-l6ac");
		reqVo.setSign(impl.getAppPaySign(reqVo, "a4IZm4bhhUw3qAxcwVZjaokJlNuVlPbc", "utf-8"));

		System.out.println(ABeanUtils.transBean2Xml(reqVo, impl.payConvert));
		//<xml><total_fee>1</total_fee><appid>wxf0f6836240fdaf3e</appid><body>&#22016;&#22016;&#26381;&#21153;-&#24180;&#36153;</body><mch_id>1480906112</mch_id><nonce_str>32768</nonce_str><notify_url>https://118.178.226.138/api/app/trade/deposit/wechatAsnyNotify</notify_url><out_trade_no>101</out_trade_no><sign>15710892FB57C79F8CFF152819D9EE8F</sign><spbill_create_ip>115.238.29.228</spbill_create_ip><trade_type>APP</trade_type></xml>
	}

	
	/*protected IResult<WechatPayCustomerReturnVo> transferInternal(WechatPayCustomerReqVo reqVo,URI url,IConverter<String, String> nameConvert,boolean pay) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, IOException, DocumentException{
	LOGGER.debug("微信交易请求对象{},url:{}",reqVo,url);
	String str = ABeanUtils.transBean2Xml(reqVo,nameConvert,pay);
	LOGGER.debug("微信交易请求对象{},报文{}",reqVo,str);
	WechatStringHttpHandler handler=new WechatStringHttpHandler(str,url);
	httpService.post(handler);
	String response=handler.getResponseAndThrow();
	LOGGER.debug("url:{},微信返回{}",url,response);
	Map<String, Object> map=WechatXmlUtil.parse(response);
	WechatPayCustomerReturnVo vo=new WechatPayCustomerReturnVo();
	BeanUtils.copyProperties(vo, map);
	LOGGER.debug("微信返回对象{}",vo);
	IResult<WechatPayCustomerReturnVo> result=handleResult(vo);
	if(result!=null){
		return result;
	}
	return ResultFactory.success(vo);
}*/
}
