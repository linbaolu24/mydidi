package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.util.Date;

public class OrderStateRecordDto extends OrderStateRecordDtoKey implements Serializable {
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 之前状态
     */
    private String bstate;

    private static final long serialVersionUID = 1L;

    /**
     * 更新时间
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 之前状态
     **/
    public String getBstate() {
        return bstate;
    }

    /**
     * 之前状态
     **/
    public void setBstate(String bstate) {
        this.bstate = bstate == null ? null : bstate.trim();
    }
}