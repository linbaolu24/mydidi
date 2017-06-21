package cn.com.didi.domain.util;

/**
 * @author xlm
 *
 */
public class WechatConsts {
	public static final String SUCCESS="SUCCESS";
	public static final String ERROR="ERROR";
	 /**
     * 企业获取code response_type值
     */
    public static String WECHATOAUTHBUSINESS_RESPONSE_TYPE = "code";
    /**
     * 企业获取code scope值
     */
    public static String WECHATOAUTHBUSINESS_SCOPE         = "snsapi_base";
    
    /**
     * 第三方回调协议InfoType
     */
    public static String  SUITETICKET_INFOTYPE = "InfoType";
    /**
     * 第三方回调协议InfoType 应用套件的SuiteId
     */
    public static String  SUITETICKET_SUITEID = "SuiteId";
    /**
     * 第三方回调协议InfoType Ticket内容
     */
    public static String  SUITETICKET_SUITETICKET = "SuiteTicket";
    /**
     * 第三方回调协议InfoType 时间戳
     */
    public static String  SUITETICKET_TIMESTAMP = "TimeStamp";
    /**
     * 第三方回调协议InfoType 授权方企业号的corpid
     */
    public static String  SUITETICKET_AUTHCORPID = "AuthCorpId";
    /**
     * 企业号永久授权码。长度为64至512个字节
     */
    public static String  CORP_PERMANENT_CODE =  "permanent_code";
    /**
     * 授权方（企业）access_token
     */
    public static String  CORP_ACCESS_TOKEN  =  "access_token";
    /**
     * 授权方（企业）access_token超时时间
     */
    public static String  CORP_EXPIRES_IN =  "expires_in";
    /**
     * 授权方企业号id
     */
    public static String  CORP_ID =  "corpid";
    /**
     * 签名串
     */
    public static String SIGN_MSG_SIGNATURE = "msg_signature";
    /**
     * 签名时间戳
     */
    public static String SIGN_TIMESTAMP = "timestamp";
    /**
     * 签名随机串
     */
    public static String SIGN_NONCE = "nonce";
    
    

    /**
     * 此回调信息的目标   
     */
    public static final String TO_USER_NAME= "ToUserName";
    /**
     * 此回调信息的来源 如微信模板消息的消息发送对象openid值
     */
    public static final String FROM_USER_NAME= "FromUserName";
    /**
     * 回调时间
     */
    public static final String CREATE_TIME= "CreateTime";
    /**
     * 消息类型 一般为event 等
     */
    public static final String MSG_TYPE= "MsgType";
    /**
     * 事件类型 如关注,取消关注 ,模板推送结果等
     */
    public static final String EVENT= "Event";
    /**
     * 用户点击菜单的键值
     */
    public static final String EVENT_KEY= "EventKey";
    /**
     * 加密内容
     */
    public static final String ENCRYPT= "Encrypt";
    /**
     * 消息ID
     */
    public static final String MSGID = "MsgID";
    /**
     * 状态值
     */
    public static final String STATUS =  "Status";
    /**
     * 内容
     */
    public static final String CONTENT =  "Content";
    /**
     * 
     */
    public static final String CACHE_ACCESS_TOKEN_PREFIX="wechat_access";
    
    /**
     * 
     */
    public static final String WECHAT_CHARSET="UTF-8";
    
}
