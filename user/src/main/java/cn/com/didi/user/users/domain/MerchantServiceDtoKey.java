package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class MerchantServiceDtoKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2949219221515106937L;

	/**
     * 用户名
     */
    private Long accountId;

    /**
     * 二级服务Id
     */
    private Integer slsId;

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
     * 二级服务Id
     **/
    public Integer getSlsId() {
        return slsId;
    }

    /**
     * 二级服务Id
     **/
    public void setSlsId(Integer slsId) {
        this.slsId = slsId;
    }
}