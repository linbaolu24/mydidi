package cn.com.didi.order.orders.domain;

import java.io.Serializable;

public class OrderNotifyDtoKey implements Serializable {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商户账户id
     */
    private Long merchantAccountId;

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
     * 商户账户id
     **/
    public Long getMerchantAccountId() {
        return merchantAccountId;
    }

    /**
     * 商户账户id
     **/
    public void setMerchantAccountId(Long merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
    }
}