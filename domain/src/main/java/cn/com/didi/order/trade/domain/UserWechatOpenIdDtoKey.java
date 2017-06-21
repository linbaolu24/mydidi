package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class UserWechatOpenIdDtoKey implements Serializable {
    /**
     * 微信uniondID
     */
    private String unionid;

    /**
     * 微信openid
     */
    private String openid;

    private static final long serialVersionUID = 1L;

    /**
     * 微信uniondID
     **/
    public String getUnionid() {
        return unionid;
    }

    /**
     * 微信uniondID
     **/
    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
    }

    /**
     * 微信openid
     **/
    public String getOpenid() {
        return openid;
    }

    /**
     * 微信openid
     **/
    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}