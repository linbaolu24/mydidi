package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class MerchantDayRemainingDtoKey implements Serializable {
    /**
     * 用8位整数表示年月日
     */
    private Integer daytime;

    /**
     * 0表示是系统余额
     */
    private Long accountId;

    /**
     * 账户类型 0 表示支付宝1 微信
     */
    private String pat;

    /**
     * 表示收入账户 支出账户
     */
    private String category;

    private static final long serialVersionUID = 1L;

    /**
     * 用8位整数表示年月日
     **/
    public Integer getDaytime() {
        return daytime;
    }

    /**
     * 用8位整数表示年月日
     **/
    public void setDaytime(Integer daytime) {
        this.daytime = daytime;
    }

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
     * 表示收入账户 支出账户
     **/
    public String getCategory() {
        return category;
    }

    /**
     * 表示收入账户 支出账户
     **/
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }
}