package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1894010944720621916L;

	/**
     * 账户ID
     */
    private Long accountId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码编码方式
     */
    private String pcode;

    /**
     * 名称
     */
    private String cname;

    /**
     * 绑定手机
     */
    private String bpn;

    /**
     * 0表示有效 1表示无效
     */
    private String state;

    /**
     * 用户头像
     */
    private String profilePhoto;

    /**
     * 角色
     */
    private String role;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 地区代码
     */
    private String addressCode;
    
    
    /**
     * 业务类型只对商户有用:0表示自营 1表示第三方 
     */
    private String businessCategory;
    /**
     * 备注
     */
    private String remark;

    /**
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;
    //add by my
    /**
     * 地区
     */
    private String address;

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
     * 用户名
     **/
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     **/
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     **/
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     **/
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 密码编码方式
     **/
    public String getPcode() {
        return pcode;
    }

    /**
     * 密码编码方式
     **/
    public void setPcode(String pcode) {
        this.pcode = pcode == null ? null : pcode.trim();
    }

    /**
     * 名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 绑定手机
     **/
    public String getBpn() {
        return bpn;
    }

    /**
     * 绑定手机
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
     * 用户头像
     **/
    public String getProfilePhoto() {
        return profilePhoto;
    }

    /**
     * 用户头像
     **/
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto == null ? null : profilePhoto.trim();
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


    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode == null ? null : addressCode.trim();
    }

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
     * 备注
     **/
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     **/
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}