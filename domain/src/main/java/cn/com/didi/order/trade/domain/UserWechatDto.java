package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class UserWechatDto implements Serializable {
    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 微信uniondID
     */
    private String unionid;

    /**
     * 微信昵称
     */
    private String nickname;

    /**
     * 普通用户性别，1为男性，2为女性
     */
    private String sex;

    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * 微信openid
     */
    private String openid;

    /**
     * 省份
     */
    private String province;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;

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
     * 微信昵称
     **/
    public String getNickname() {
        return nickname;
    }

    /**
     * 微信昵称
     **/
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 普通用户性别，1为男性，2为女性
     **/
    public String getSex() {
        return sex;
    }

    /**
     * 普通用户性别，1为男性，2为女性
     **/
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    /**
     * 微信头像
     **/
    public String getHeadimgurl() {
        return headimgurl;
    }

    /**
     * 微信头像
     **/
    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
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

    /**
     * 省份
     **/
    public String getProvince() {
        return province;
    }

    /**
     * 省份
     **/
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * 城市
     **/
    public String getCity() {
        return city;
    }

    /**
     * 城市
     **/
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 国家
     **/
    public String getCountry() {
        return country;
    }

    /**
     * 国家
     **/
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }
}