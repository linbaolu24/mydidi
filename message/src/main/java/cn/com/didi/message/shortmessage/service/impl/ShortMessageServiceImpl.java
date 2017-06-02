package cn.com.didi.message.shortmessage.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import javax.annotation.Resource;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.core.excpetion.MessageObjectException;
import cn.com.didi.core.message.Message;
import cn.com.didi.core.property.IResult;
import cn.com.didi.core.property.ResultFactory;
import cn.com.didi.core.property.impl.result.Result;
import cn.com.didi.message.shortmessage.domain.ShortMessgaeDto;
import cn.com.didi.message.shortmessage.service.IShortMessageService;
import cn.com.didi.message.util.MessageConstants;
import cn.com.didi.thirdExt.http.IHttpHandler;
import cn.com.didi.thirdExt.http.IHttpService;

public class ShortMessageServiceImpl implements IShortMessageService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ShortMessageServiceImpl.class);

	public IHttpService getHttpService() {
		return httpService;
	}

	public void setHttpService(IHttpService httpService) {
		this.httpService = httpService;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	private String url = "http://120.55.197.77:1210/Services/MsgSend.asmx/sendMsgByEncrypt";
	private String userPass;
	private String channel;
	private String userCode;
	@Resource
	private IHttpService httpService;
	private String encryptKey;

	public void init() throws NoSuchAlgorithmException {
		encryptKey = SHA1(userPass).substring(0, 8).toUpperCase();
	}

	public IResult<Void> sendMessage(ShortMessgaeDto dto) {
		SmHttpHandler sm = new SmHttpHandler(dto);
		try {
			httpService.post(sm);
		} catch (MessageObjectException e) {
			return ResultFactory.error(e.getCode(), e.getMessage());
		}
		return sm.getResult();

	}

	public String DESEncrypt(String encryptStr) throws InvalidKeyException, InvalidAlgorithmParameterException,
			InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException {
		String result = "";
		DESKeySpec desKeySpec = new DESKeySpec(encryptKey.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKeySpec);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		IvParameterSpec ivp = new IvParameterSpec(encryptKey.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, securekey, ivp);
		byte[] encryptResult = cipher.doFinal(encryptStr.getBytes("GB2312"));
		result = BinaryToHexString(encryptResult);

		return result;
	}

	public String BinaryToHexString(byte[] bytes) {
		String hexStr = "0123456789ABCDEF";
		String result = "";
		String hex = "";
		for (int i = 0; i < bytes.length; i++) {
			// 字节高4位
			hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
			// 字节低4位
			hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
			result += hex;
		}
		return result;
	}

	public String SHA1(String key) throws NoSuchAlgorithmException {
		StringBuffer sb = new StringBuffer();
		MessageDigest md = MessageDigest.getInstance("SHA-1");
		md.update(key.getBytes());
		byte[] resultsha = md.digest();
		for (byte b : resultsha) {
			int i = b & 0xff;
			if (i < 0xf) {
				sb.append(0);
			}
			sb.append(Integer.toHexString(i));
		}
		return sb.toString();
	}

	protected class SmHttpHandler implements IHttpHandler {
		private ShortMessgaeDto dto;
		private IResult<Void> result;

		public SmHttpHandler(ShortMessgaeDto dto) {
			super();
			this.dto = dto;
		}

		public boolean preForRequest(HttpEntityEnclosingRequestBase post) {
			try {

				post.setURI(new URI(getUrl()));
				StringBuilder sBuilder = new StringBuilder();
				sBuilder.append("userPass=").append(userPass);
				sBuilder.append("&DesNo=");
				String[] phones = dto.getPhones();
				for (int i = 0; i < phones.length; i++) {
					if (i != 0) {
						sBuilder.append(",");
					}
					sBuilder.append(phones[i]);
				}
				sBuilder.append("&Msg=").append(dto.getContent());
				sBuilder.append("&Channel=").append(channel);
				String encryptStr;
				String source=sBuilder.toString();
				LOGGER.debug("请求参数为{}",source);
				encryptStr = DESEncrypt(source);
				LOGGER.debug("加密串为{}", encryptStr);
				NameValuePair[] pair = new NameValuePair[2];
				pair[0] = new BasicNameValuePair("userCode", userCode);
				pair[1] = new BasicNameValuePair("submitInfo", encryptStr);
				UrlEncodedFormEntity entiry = new UrlEncodedFormEntity(Arrays.asList(pair), Charsets.UTF_8);
				post.setEntity(entiry);
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				constructResult(MessageConstants.SM_CONTENT_CODE_ERROR);
			}
			return false;

		}

		public void forResponse(HttpResponse post) {
			try {
                if (post.getStatusLine().getStatusCode() != 200) {
                	constructResult(MessageConstants.SM_GATE_REQUEST_ERROR);
                	return;
				} 
				String str = EntityUtils.toString(post.getEntity(), Charsets.UTF_8);
				String code=getCode(str);
				//result = ResultFactory.success();
				if (code.startsWith("-")) {
					LOGGER.error("短信网关返回:" + str);
					constructResult(MessageConstants.SM_GATE_ERROR);
				}else{
					LOGGER.debug("流水号为{}",str);
					result=ResultFactory.success();
				}
			} catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
				constructResult(MessageConstants.SM_PARSE_RESULT_ERROR);
			}
		}

		protected String getCode(String str) {
			if (StringUtils.isEmpty(str)) {
				LOGGER.error("短信网关返回为空");
				return "-";
			}
			int k = str.indexOf("</string");
			if (k != -1) {
				int k1 = str.lastIndexOf(">", k);
				if (k1 != -1) {
					return str.substring(k1 + 1, k);
				}
			}
			LOGGER.error("短信网关返回结果未知");
			return "-";

		}

		protected void constructResult(Message message) {
			result = new Result<Void>(message.getMessage(), message.getCode(), null, null);
		}

		public IResult<Void> getResult() {
			return result;
		}

	}
}
