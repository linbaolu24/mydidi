package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class UserLinkIdDto implements Serializable {
    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 支付宝账号
     */
    private String alipayAccount;

    /**
     * 微信账号
     */
    private String wechatAccount;

    /**
     * 个推CID
     */
    private String gtCid;

    /**
     * 融云token
     */
    private String ryToken;

    /**
     * 角色
     */
    private String role;

    /**
     * 业务类型只对商户有用:0表示自营 1表示第三方 
     */
    private String businessCategory;

    /**
     * 微信nickName
     */
    private String wechatName;

    /**
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;

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
     * 支付宝账号
     **/
    public String getAlipayAccount() {
        return alipayAccount;
    }

    /**
     * 支付宝账号
     **/
    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount == null ? null : alipayAccount.trim();
    }

    /**
     * 微信账号
     **/
    public String getWechatAccount() {
        return wechatAccount;
    }

    /**
     * 微信账号
     **/
    public void setWechatAccount(String wechatAccount) {
        this.wechatAccount = wechatAccount == null ? null : wechatAccount.trim();
    }

    /**
     * 个推CID
     **/
    public String getGtCid() {
        return gtCid;
    }

    /**
     * 个推CID
     **/
    public void setGtCid(String gtCid) {
        this.gtCid = gtCid == null ? null : gtCid.trim();
    }

    /**
     * 融云token
     **/
    public String getRyToken() {
        return ryToken;
    }

    /**
     * 融云token
     **/
    public void setRyToken(String ryToken) {
        this.ryToken = ryToken == null ? null : ryToken.trim();
    }

    /**
     * 角色
     **/
    public String getRole() {
        return role;
    }

    /**
     * 角色
     **/
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 业务类型只对商户有用:0表示自营 1表示第三方 
     **/
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * 业务类型只对商户有用:0表示自营 1表示第三方 
     **/
    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    /**
     * 微信nickName
     **/
    public String getWechatName() {
        return wechatName;
    }

    /**
     * 微信nickName
     **/
    public void setWechatName(String wechatName) {
        this.wechatName = wechatName == null ? null : wechatName.trim();
    }

    /**
     * 扩展1
     **/
    public String getExt1() {
        return ext1;
    }

    /**
     * 扩展1
     **/
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 扩展2
     **/
    public String getExt2() {
        return ext2;
    }

    /**
     * 扩展2
     **/
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
    public String userName(){
    	return ext1;
    }
}