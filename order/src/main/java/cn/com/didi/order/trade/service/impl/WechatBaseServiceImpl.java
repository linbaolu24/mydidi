package cn.com.didi.order.trade.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import cn.com.didi.domain.domains.SuiteTicketInfoDto;
import cn.com.didi.domain.domains.wechat.AccessTokenOpenIdDto;
import cn.com.didi.domain.domains.wechat.WechatUserInfo;
import cn.com.didi.domain.util.SuiteTicketTypeEnum;
import cn.com.didi.domain.util.WechatConsts;
import cn.com.didi.order.trade.service.IWechatBaseService;
import cn.com.didi.order.trade.service.IWechatTransferService;
import cn.com.didi.order.trade.util.SHA1;
import cn.com.didi.order.trade.util.WXBizMsgCrypt;
import cn.com.didi.order.trade.util.WechatXmlUtil;
import cn.com.didi.thirdExt.produce.IAppEnv;

@Service
public class WechatBaseServiceImpl implements IWechatBaseService {
	private static final Logger LOGGER = LoggerFactory.getLogger(WechatBaseServiceImpl.class);
	@Resource
	protected IAppEnv appEnv;
	private static final String TOKEN = "436346346346363";

	private static final String ENCODINGAESKEY = "1234567890123456789012345678901234567890abc";

	private static final String APPID = "tjbcf4ad9e075c54b2"; // 套件ID

	private static final Map<String, String> SECRETMAP = new HashMap<String, String>(); // 秘钥
	static {
		SECRETMAP.put(APPID, "VYDcST6kePVIahlLpBAVmQ8AZIZuYoPxL9uC4eP7IQaMgUb5ySzSVUt8IjUYO3cc");
	}
	@Resource
	protected IWechatTransferService wechatTransferService;
	@Override
	public void suiteTicketPostData(Map<String, String> map, String postData, String suiteId) {
		String msgSignature = (String) map.get(WechatConsts.SIGN_MSG_SIGNATURE);
		String timeStamp = (String) map.get(WechatConsts.SIGN_TIMESTAMP);
		String nonce = (String) map.get(WechatConsts.SIGN_NONCE);

		SuiteTicketInfoDto ticketInfo = null;
		Map<String, Object> xmlMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(postData)) {
			try {
				String token = TOKEN;
				String encodingAesKey = ENCODINGAESKEY;
				String xmlStr = new WXBizMsgCrypt(token, encodingAesKey, suiteId).DecryptMsg(msgSignature, timeStamp,
						nonce, postData); // 验证签名串并解密postdata
				if (LOGGER.isDebugEnabled()) {
					LOGGER.info("微信回调事件xml内容:" + xmlStr);
				}

				xmlMap = WechatXmlUtil.parse(xmlStr); // 解析xml字符串
				String infotype = (String) xmlMap.get(WechatConsts.SUITETICKET_INFOTYPE);
				if (SuiteTicketTypeEnum.SUITE_TICKET.getCode().equals(infotype)) { // 如果是推送suiteticket协议,则放入缓存
					ticketInfo = new SuiteTicketInfoDto();
					ticketInfo.setInfoType(infotype);
					ticketInfo.setSuiteId((String) xmlMap.get(WechatConsts.SUITETICKET_SUITEID));
					ticketInfo.setSuiteTicket((String) xmlMap.get(WechatConsts.SUITETICKET_SUITETICKET));
					ticketInfo.setTimeStamp((String) xmlMap.get(WechatConsts.SUITETICKET_TIMESTAMP));
					// wechatStorage.putSuiteTicket(ticketInfo.getSuiteId(),
					// ticketInfo); // 将postdata信息存储到redis里面
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("微信系统事件回调suiteTieket:" + ticketInfo);
					}

				} else if (SuiteTicketTypeEnum.CHANGE_AUTH.getCode().equals(infotype)) { // 如果是变更授权

				} else if (SuiteTicketTypeEnum.CANCEL_AUTH.getCode().equals(infotype)) { // 如果是取消授权

				}
			} catch (Exception e) {
				if (e instanceof RuntimeException) {
					throw (RuntimeException) e;
				}
				throw new RuntimeException(e);
			}

		}

	}

	@Override
	public String valiadate(String signature, String timestamp, String nonce, String echostr) {
		String token = appEnv.getWechatValidatorToken();
		if (StringUtils.isEmpty(token) || StringUtils.isEmpty(timestamp) || StringUtils.isEmpty(nonce)) {
			return "";
		}
		// 对微信返回的数据进行字典序排序
		List<String> tempList = new ArrayList<String>();
		tempList.add(token);
		tempList.add(timestamp);
		tempList.add(nonce);
		Collections.sort(tempList, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		// 将排序后的三个字符串拼加在一起，进行sha1加密
		String sortedStr = tempList.get(0) + tempList.get(1) + tempList.get(2);
		String encStr = new SHA1().getDigestOfString(sortedStr.getBytes()); 

		// 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		if (signature.equalsIgnoreCase(encStr)) {
			return echostr;
		}

		return "";
	}

	@Override
	public WechatUserInfo getUserInfo(Long accountId,String code) {
		AccessTokenOpenIdDto dto=wechatTransferService.getUnionAccess(appEnv.getWechatAppId(), appEnv.getWechatAppkey(), code);
		return wechatTransferService.getUser(dto.getAccess_token(), dto.getOpenid());
	}

}
