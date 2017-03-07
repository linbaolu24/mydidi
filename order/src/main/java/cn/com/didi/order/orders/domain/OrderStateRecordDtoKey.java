package cn.com.didi.order.orders.domain;

import java.io.Serializable;

public class OrderStateRecordDtoKey implements Serializable {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 当前状态
     */
    private String cstate;

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     **/
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     **/
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 当前状态
     **/
    public String getCstate() {
        return cstate;
    }

    /**
     * 当前状态
     **/
    public void setCstate(String cstate) {
        this.cstate = cstate == null ? null : cstate.trim();
    }
}