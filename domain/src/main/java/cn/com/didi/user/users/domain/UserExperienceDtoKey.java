package cn.com.didi.user.users.domain;

import java.io.Serializable;

public class UserExperienceDtoKey implements Serializable {
    /**
     * 用户ID
     */
    private Long accoutid;

    /**
     * 二级服务ID
     */
    private Integer slsId;

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     **/
    public Long getAccoutid() {
        return accoutid;
    }

    /**
     * 用户ID
     **/
    public void setAccoutid(Long accoutid) {
        this.accoutid = accoutid;
    }

    /**
     * 二级服务ID
     **/
    public Integer getSlsId() {
        return slsId;
    }

    /**
     * 二级服务ID
     **/
    public void setSlsId(Integer slsId) {
        this.slsId = slsId;
    }
}