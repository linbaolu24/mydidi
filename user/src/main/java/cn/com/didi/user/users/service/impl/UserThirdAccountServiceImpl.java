package cn.com.didi.user.users.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.excpetion.BaseRuntimeException;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.domain.util.Role;
import cn.com.didi.thirdExt.http.FormHttpHandler;
import cn.com.didi.thirdExt.http.Http200Hanle;
import cn.com.didi.thirdExt.http.IHttpService;
import cn.com.didi.thirdExt.produce.IAppEnv;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IUserThirdAccountService;
import cn.com.didi.user.util.MessageConstans;

@Service
public class UserThirdAccountServiceImpl implements IUserThirdAccountService {
	private static final String RO_URL = "http://api.cn.ronghub.com/user/getToken.json";
	private static final Integer SUCCESS = Integer.valueOf(200);
	private static final Logger LOGGER = LoggerFactory.getLogger(UserThirdAccountServiceImpl.class);
	private static final String APPKEY = "RC-App-Key";
	private static final String NONCE = "RC-Nonce";
	private static final String TIMESTAMP = "RC-Timestamp";
	private static final String SIGNATURE = "RC-Signature";
	@Resource
	protected IAppEnv appEnv;
	@Resource
	protected IHttpService httpExeService;

	@Override
	public IResult<UserLinkIdDto> generatorUserLink(String phone, String role) {
		if (!StringUtils.isEmpty(phone) && !StringUtils.isEmpty(role)) {
			 return ResultFactory.success(null);
		}
		String appkey;
		String appSecret;
		if(Role.COUSMER.codeEqual(role)){
			appkey=appEnv.getRyAppKey();
			appSecret=appEnv.getRyAppSecret();
		}else if(Role.BUSINESS.codeEqual(role)){
			appkey=appEnv.getRyBAppKey();
			appSecret=appEnv.getRyBAppSecret();
		}else{
		   return ResultFactory.success(null);
		}
		FormHttpHandler handler = new FormHttpHandler();
		handler.setUrl(RO_URL);
		NameValuePair pair = new BasicNameValuePair("userId", phone + "_" + role);
		handler.setPair(Arrays.asList(pair));
		
		Map<String, String> header = generatorSign(appkey, appSecret);
		handler.setHeaderMap(header);
		httpExeService.post(new Http200Hanle(handler));
		try {
			String result = handler.getResultAndThrow();
			LOGGER.debug("容云返回{}", result);
			TokenResult tokenResult = JSON.parseObject(result, TokenResult.class);
			if (tokenResult != null || SUCCESS.equals(tokenResult.getCode())) {
				UserLinkIdDto dto=new UserLinkIdDto();
				dto.setRyToken(tokenResult.getToken());
				return ResultFactory.success(dto);
			}
		} catch (BaseRuntimeException e) {
			LOGGER.error(e.getMessage(), e);
			return ResultFactory.error(e.getCode(), e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);

		}
		return ResultFactory.error(MessageConstans.USER_CREATE_ROY_TOKEN_ERROR);
	}

	protected Map<String, String> generatorSign(String appKey, String appSecret) {
		Map<String, String> headMap = new HashMap<>(4);
		String nonce = String.valueOf(RandomUtils.nextInt());
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
		StringBuilder toSign = new StringBuilder(appSecret).append(nonce).append(timestamp);
		String sign = DigestUtils.sha1Hex(toSign.toString());
		headMap.put(NONCE, nonce);
		headMap.put(APPKEY, appKey);
		headMap.put(TIMESTAMP, timestamp);
		headMap.put(SIGNATURE, sign);
		return headMap;
	}

	/**
	 * getToken 返回结果
	 */
	public class TokenResult {
		// 返回码，200 为正常.如果您正在使用开发环境的 AppKey，您的应用只能注册 100 名用户，达到上限后，将返回错误码
		// 2007.如果您需要更多的测试账户数量，您需要在应用配置中申请“增加测试人数”。
		Integer code;
		// 用户 Token，可以保存应用内，长度在 256 字节以内.用户 Token，可以保存应用内，长度在 256 字节以内。
		String token;
		// 用户 Id，与输入的用户 Id 相同.用户 Id，与输入的用户 Id 相同。
		String userId;
		// 错误信息。
		String errorMessage;

		public TokenResult(Integer code, String token, String userId, String errorMessage) {
			this.code = code;
			this.token = token;
			this.userId = userId;
			this.errorMessage = errorMessage;
		}

		/**
		 * 设置code
		 *
		 */
		public void setCode(Integer code) {
			this.code = code;
		}

		/**
		 * 获取code
		 *
		 * @return Integer
		 */
		public Integer getCode() {
			return code;
		}

		/**
		 * 设置token
		 *
		 */
		public void setToken(String token) {
			this.token = token;
		}

		/**
		 * 获取token
		 *
		 * @return String
		 */
		public String getToken() {
			return token;
		}

		/**
		 * 设置userId
		 *
		 */
		public void setUserId(String userId) {
			this.userId = userId;
		}

		/**
		 * 获取userId
		 *
		 * @return String
		 */
		public String getUserId() {
			return userId;
		}

		/**
		 * 设置errorMessage
		 *
		 */
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		/**
		 * 获取errorMessage
		 *
		 * @return String
		 */
		public String getErrorMessage() {
			return errorMessage;
		}
	}
}
