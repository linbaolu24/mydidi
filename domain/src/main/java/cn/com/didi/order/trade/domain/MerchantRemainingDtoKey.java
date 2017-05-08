package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class MerchantRemainingDtoKey implements Serializable {
    /**
     * 0表示是系统余额
     */
    private Long accountId;

    /**
     * 账户类型
     */
    private String pat;

    private static final long serialVersionUID = 1L;

    /**
     * 0表示是系统余额
     **/
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 0表示是系统余额
     **/
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 账户类型
     **/
    public String getPat() {
        return pat;
    }

    /**
     * 账户类型
     **/
    public void setPat(String pat) {
        this.pat = pat == null ? null : pat.trim();
    }
}