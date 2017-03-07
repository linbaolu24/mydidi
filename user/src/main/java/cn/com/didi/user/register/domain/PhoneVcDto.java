package cn.com.didi.user.register.domain;

import java.io.Serializable;
import java.util.Date;

public class PhoneVcDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1777282933169004091L;

	/**
     * 验证码Id
     */
    private Long vcId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 0表示未使用 1表示已使用
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 验证码
     */
    private Integer vc;

    /**
     * 最后发送时间
     */
    private Date ltt;

    /**
     * 验证码Id
     **/
    public Long getVcId() {
        return vcId;
    }

    /**
     * 验证码Id
     **/
    public void setVcId(Long vcId) {
        this.vcId = vcId;
    }

    /**
     * 手机号
     **/
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号
     **/
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 0表示未使用 1表示已使用
     **/
    public String getState() {
        return state;
    }

    /**
     * 0表示未使用 1表示已使用
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
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
     * 验证码
     **/
    public Integer getVc() {
        return vc;
    }

    /**
     * 验证码
     **/
    public void setVc(Integer vc) {
        this.vc = vc;
    }

    /**
     * 最后发送时间
     **/
    public Date getLtt() {
        return ltt;
    }

    /**
     * 最后发送时间
     **/
    public void setLtt(Date ltt) {
        this.ltt = ltt;
    }
}