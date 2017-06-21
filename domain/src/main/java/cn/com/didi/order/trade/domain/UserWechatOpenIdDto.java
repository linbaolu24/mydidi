package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class UserWechatOpenIdDto extends UserWechatOpenIdDtoKey implements Serializable {
    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 微信APPID
     */
    private String appid;

    /**
     * 代码描述
     */
    private String code;

    /**
     * 关注状态
     */
    private String focusestate;

    private static final long serialVersionUID = 1L;

    /**
     * 账户ID
     **/
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 账户ID
     **/
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 微信APPID
     **/
    public String getAppid() {
        return appid;
    }

    /**
     * 微信APPID
     **/
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 代码描述
     **/
    public String getCode() {
        return code;
    }

    /**
     * 代码描述
     **/
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * 关注状态
     **/
    public String getFocusestate() {
        return focusestate;
    }

    /**
     * 关注状态
     **/
    public void setFocusestate(String focusestate) {
        this.focusestate = focusestate == null ? null : focusestate.trim();
    }
}