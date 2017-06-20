package cn.com.didi.order.trade.service.impl;

import java.beans.IntrospectionException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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
	private static final String ACCESS_TOKEN_URL="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=${APPID}&secret=${SECRET}";
	protected IFilter<Field> field = new IFilter<Field>() {

		@Override
		public boolean filter(Field obj) {
			return Modifier.isStatic(obj.getModifiers())&&!signMap.containsKey(obj.getName());
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
		
		transMap=new HashMap<>();
		transMap.put("appid", "mch_appid");
		transMap.put("mch_id","mchid");
		signMap.put("mch_appid", "Y");
		signMap.put("cost", "Y");
		signMap.put("sign", "Y");
		signMap.put("source", "Y");
	}
	@Override
	public IResult<WechatPayCustomerReturnVo> transferAppPay(WechatPayCustomerReqVo reqVo) {
		try {
			reqVo.setTrade_type("APP");
			return transferInternal(reqVo, appEnv.getWechatAppPayURI(),payConvert);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| IntrospectionException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_BUILD_REQUEST_ERROR);
		}catch(DocumentException |ParseException|IOException e){
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(OrderMessageConstans.DEAL_WECHAT_TYXD_PARSE_RESPONSE_ERROR);
		}
	}
	protected IResult<WechatPayCustomerReturnVo> transferInternal(WechatPayCustomerReqVo reqVo,URI url,IConverter<String, String> nameConvert) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException, IOException, DocumentException{
		LOGGER.debug("微信交易请求对象{}",reqVo);
		String str = ABeanUtils.transBean2Xml(reqVo,nameConvert);
		LOGGER.debug("微信交易请求对象{},报文{}",reqVo,str);
		WechatStringHttpHandler handler=new WechatStringHttpHandler(str,url);
		httpService.post(handler);
		String response=handler.getResponseAndThrow();
		LOGGER.debug("微信返回{}",response);
		Map<String, Object> map=WechatXmlUtil.parse(response);
		WechatPayCustomerReturnVo vo=new WechatPayCustomerReturnVo();
		BeanUtils.copyProperties(vo, map);
		LOGGER.debug("微信返回对象{}",vo);
		IResult<WechatPayCustomerReturnVo> result=handleResult(vo);
		if(result!=null){
			return result;
		}
		return ResultFactory.success(vo);
	}
	@Override
	public IResult<WechatPayCustomerReturnVo> transferForTransferAccounts(WechatPayCustomerReqVo reqVo) {
		try {
			if (StringUtils.isEmpty(reqVo.getCheck_name())) {
				reqVo.setCheck_name(StringUtils.isEmpty(reqVo.getRe_user_name()) ? "NO_CHECK" : "FORCE_CHECK");
			}
			if(StringUtils.isEmpty(reqVo.getDesc())){
				reqVo.setDesc("提现");
			}
			return transferInternal(reqVo, null,convert);
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
			return SignatureUtils.verifySign(vo, appEnv.getWechatAppkey(), appEnv.getWechatCharSet(), vo.getSign(), field);
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
	public WechatUserInfo getUser(String accessToken, String openId) {
		Map<String, Object> parms = new HashMap<String, Object>();
		 parms.put("ACCESS_TOKEN", accessToken);
         parms.put("OPENID", openId);
         JsonGetHttpHandler<WechatUserInfo> handler = new JsonGetHttpHandler(parms, AccessTokenOpenIdDto.class,
        		 UNION_USER_URL);
        try{
        	httpService.get(handler);
        	WechatUserInfo userInfo=handler.getResultAndThrow();
        	return userInfo;
        } catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw new MessageObjectException(OrderMessageConstans.WECHAT_GET_USER_INFO_ERROR);
        }
	}
	@Override
	public WechatUserInfo getUserFromCode(String appid, String secret, String code) {
		AccessTokenOpenIdDto dto=getUnionAccess(appid, secret, code);
		if(!dto.normalSuccess()){
			LOGGER.error("获取access微信返回代码错误{}",dto);
			throw new MessageObjectException(OrderMessageConstans.WECHAT_GET_ACCESS_CODE_ERROR);
		}
		WechatUserInfo info= getUser(dto.getAccess_token(), dto.getOpenid());
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
		return getUrl(parms, AccessTokenDto.class, OrderMessageConstans.WECHAT_GET_ACCESS_TOKEN_ERROR);
	}

	protected <T extends AWechatCodeDto> T getUrl(Map<String, String> params, Class<T> target, Message error) {
		JsonGetHttpHandler<T> handler = new JsonGetHttpHandler(params, target, UNION_USER_URL);
		try {
			httpService.get(handler);
			T result = handler.getResultAndThrow();
			return result;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new MessageObjectException(error);
		}

	}
}
