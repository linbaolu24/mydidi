package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class MerchantDayRemainingDto extends MerchantDayRemainingDtoKey implements Serializable {
    /**
     * 余额
     */
    private Long remaining;

    private static final long serialVersionUID = 1L;

    /**
     * 余额
     **/
    public Long getRemaining() {
        return remaining;
    }

    /**
     * 余额
     **/
    public void setRemaining(Long remaining) {
        this.remaining = remaining;
    }
}