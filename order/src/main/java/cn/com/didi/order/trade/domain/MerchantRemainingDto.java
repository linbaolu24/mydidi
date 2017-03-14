package cn.com.didi.order.trade.domain;

import java.io.Serializable;

public class MerchantRemainingDto extends MerchantRemainingDtoKey implements Serializable {
    /**
     * 余额
     */
    private Integer remaining;

    private static final long serialVersionUID = 1L;

    /**
     * 余额
     **/
    public Integer getRemaining() {
        return remaining;
    }

    /**
     * 余额
     **/
    public void setRemaining(Integer remaining) {
        this.remaining = remaining;
    }
}