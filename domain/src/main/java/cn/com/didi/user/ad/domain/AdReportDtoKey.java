package cn.com.didi.user.ad.domain;

import java.io.Serializable;

public class AdReportDtoKey implements Serializable {
    /**
     * 广告ID
     */
    private Long adId;

    /**
     * 到小时的时间单元
     */
    private String timeuint;

    private static final long serialVersionUID = 1L;

    /**
     * 广告ID
     **/
    public Long getAdId() {
        return adId;
    }

    /**
     * 广告ID
     **/
    public void setAdId(Long adId) {
        this.adId = adId;
    }

    /**
     * 到小时的时间单元
     **/
    public String getTimeuint() {
        return timeuint;
    }

    /**
     * 到小时的时间单元
     **/
    public void setTimeuint(String timeuint) {
        this.timeuint = timeuint == null ? null : timeuint.trim();
    }
}