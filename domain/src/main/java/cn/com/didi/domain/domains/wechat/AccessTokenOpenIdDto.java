package cn.com.didi.domain.domains.wechat;


public class AccessTokenOpenIdDto extends AccessTokenDto {

    /**
     * 用户刷新access_token
     */
    private String refresh_token;
    /**
     * 授权用户唯一标识
     */
    private String openid;
    /**
     * 用户授权的作用域，使用逗号（,）分隔
     */
    private String scope;

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return super.toString()+"  AccessTokenOpenIdVo [refresh_token=" + refresh_token + ", openid=" + openid + ", scope=" + scope + "]";
    }

    
}
