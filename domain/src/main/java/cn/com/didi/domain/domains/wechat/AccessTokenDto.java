package cn.com.didi.domain.domains.wechat;

import java.util.Date;


public class AccessTokenDto extends AWechatCodeDto{

    /**
     * access_token
     */
    private String access_token;
    /**
     * 过期时间 秒
     */
    private int expires_in;
    /**
     * 过期时间
     */
    private Date expiresTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public Date getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Date expiresTime) {
        this.expiresTime = expiresTime;
    }

    @Override
    public String toString() {
        return super.toString() + "\n access_token:" + access_token + "  expires_in:" + expires_in + " expiresTime:"
                + expiresTime;
    }
}
