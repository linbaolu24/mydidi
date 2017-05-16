package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class VipDtoKey implements Serializable {
    /**
     * 账户ID
     */
    private Long accountId;

    /**
     * 用户名
     */
    private Integer slsId;

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
     * 用户名
     **/
    public Integer getSlsId() {
        return slsId;
    }

    /**
     * 用户名
     **/
    public void setSlsId(Integer slsId) {
        this.slsId = slsId;
    }
}