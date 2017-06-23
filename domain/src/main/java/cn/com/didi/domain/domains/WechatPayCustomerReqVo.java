package cn.com.didi.domain.domains;

import java.io.Serializable;


/**
 * @author xlm
 *
 */
public class WechatPayCustomerReqVo implements Serializable {

    private static final long serialVersionUID = -8865019350920142854L;
    /**
     * appid
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 商户订单号
     */
    private String partner_trade_no;
    /**
     * 用户openid
     */
    private String openid;
    /**
     * 校验用户姓名选项
     */
    private String check_name;
    /**
     * 收款用户姓名
     */
    private String re_user_name;
    /**
     * 金额
     */
    private int amount;
    /**
     * 企业付款描述信息
     */
    private String desc;
    /**
     * Ip地址
     */
    private String spbill_create_ip;
    /**
     * 商品描述	body	是	String(128)	腾讯充值中心-QQ会员充值	
商品描述交易字段格式根据不同的应用场景按照以下格式：
APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     */
    private String body;
    /**
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     */
    private String notify_url;
    /**
     * 交易类型	trade_type	是	String(16)	APP	支付类型
     */
    private String trade_type;

   

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getPartner_trade_no() {
        return partner_trade_no;
    }

    public void setPartner_trade_no(String partner_trade_no) {
        this.partner_trade_no = partner_trade_no;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getCheck_name() {
        return check_name;
    }

    public void setCheck_name(String check_name) {
        this.check_name = check_name;
    }

    public String getRe_user_name() {
        return re_user_name;
    }

    public void setRe_user_name(String re_user_name) {
        this.re_user_name = re_user_name;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	@Override
	public String toString() {
		return "WechatPayCustomerReqVo [appid=" + appid + ", mch_id=" + mch_id + ", device_info=" + device_info
				+ ", nonce_str=" + nonce_str + ", sign=" + sign + ", partner_trade_no=" + partner_trade_no + ", openid="
				+ openid + ", check_name=" + check_name + ", re_user_name=" + re_user_name + ", amount=" + amount
				+ ", desc=" + desc + ", spbill_create_ip=" + spbill_create_ip + ", body=" + body + ", notify_url="
				+ notify_url + ", trade_type=" + trade_type + "]";
	}

   

}
