package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;

public class MerchantServiceDto extends MerchantServiceDtoKey implements Serializable {
    private Date createTime;

    /**
     * 0表示有效 1表示无效
     */
    private String state;

    /**
     * Certification results 0表示认证通过 1表示认证待认证 2 表示认证不通过
     */
    private String cr;

    private static final long serialVersionUID = 1L;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
     * Certification results 0表示认证通过 1表示认证待认证 2 表示认证不通过
     **/
    public String getCr() {
        return cr;
    }

    /**
     * Certification results 0表示认证通过 1表示认证待认证 2 表示认证不通过
     **/
    public void setCr(String cr) {
        this.cr = cr == null ? null : cr.trim();
    }
}