package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class MerchantServiceDtoKey implements Serializable {
    /**
     * 用户名
     */
    private Long accountId;

    /**
     * 二级服务Id
     */
    private Integer slsId;

    private static final long serialVersionUID = 1L;

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
    
    
    //TODO add by myself
	public void setServiceId(Integer slsId){
		setSlsId(slsId);
	}
}