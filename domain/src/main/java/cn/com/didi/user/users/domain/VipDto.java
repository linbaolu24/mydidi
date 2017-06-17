package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;

public class VipDto extends VipDtoKey implements Serializable {
    /**
     * 会员名
     */
    private String cname;

    /**
     * 会员头像
     */
    private String profilePhoto;

    /**
     * 会员手机
     */
    private String bpn;

    /**
     * 0表示有效 1表示无效
     */
    private String state;

    /**
     * 角色
     */
    private String role;

    /**
     * 业务类型只对商户有效:0表示自营 1表示第三方
     */
    private String businessCategory;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 会员手机
     */
    private String phone;

    /**
     * 账户类型 0 表示支付宝1 微信
     */
    private String pat;

    /**
     * 推荐人
     */
    private String referee;

    /**
     * 订单ID
     */
    private Long dealId;

    private static final long serialVersionUID = 1L;

    /**
     * 会员名
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 会员名
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 会员头像
     **/
    public String getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * 会员头像
     **/
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto == null ? null : profilePhoto.trim();
    }

    /**
     * 会员手机
     **/
    public String getBpn() {
        return bpn;
    }

    /**
     * 会员手机
     **/
    public void setBpn(String bpn) {
        this.bpn = bpn == null ? null : bpn.trim();
    }

    /**
     * 0表示有效 1表示无效
     **/
    public String getState() {
        return state;
    }

    /**
     * 0表示有效 1表示无效
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
     * 业务类型只对商户有效:0表示自营 1表示第三方
     **/
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * 业务类型只对商户有效:0表示自营 1表示第三方
     **/
    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    /**
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 会员手机
     **/
    public String getPhone() {
        return phone;
    }

    /**
     * 会员手机
     **/
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 账户类型 0 表示支付宝1 微信
     **/
    public String getPat() {
        return pat;
    }

    /**
     * 账户类型 0 表示支付宝1 微信
     **/
    public void setPat(String pat) {
        this.pat = pat == null ? null : pat.trim();
    }

    /**
     * 推荐人
     **/
    public String getReferee() {
        return referee;
    }

    /**
     * 推荐人
     **/
    public void setReferee(String referee) {
        this.referee = referee == null ? null : referee.trim();
    }

    /**
     * 订单ID
     **/
    public Long getDealId() {
        return dealId;
    }

    /**
     * 订单ID
     **/
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }
}