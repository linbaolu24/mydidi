package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class MerchantAreaDtoKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3820611223513916440L;

	/**
     * 用户名
     */
    private Long accountId;

    /**
     * 地区
     */
    private String areaCode;

    /**
     * 用户名
     **/
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 用户名
     **/
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     * 地区
     **/
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 地区
     **/
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }
}