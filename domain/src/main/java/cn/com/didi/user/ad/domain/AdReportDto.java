package cn.com.didi.user.ad.domain;

import java.io.Serializable;

public class AdReportDto extends AdReportDtoKey implements Serializable {
    /**
     * 曝光量
     */
    private Integer exposure;

    /**
     * 点击量
     */
    private Integer clickRate;

    /**
     * 曝光日期
     */
    private String exposureDate;

    private static final long serialVersionUID = 1L;

    //add by my
    private String time;
    
    /**
     * 曝光量
     **/
    public Integer getExposure() {
        return exposure;
    }

    /**
     * 曝光量
     **/
    public void setExposure(Integer exposure) {
        this.exposure = exposure;
    }

    /**
     * 点击量
     **/
    public Integer getClickRate() {
        return clickRate;
    }

    /**
     * 点击量
     **/
    public void setClickRate(Integer clickRate) {
        this.clickRate = clickRate;
    }

    /**
     * 曝光日期
     **/
    public String getExposureDate() {
        return exposureDate;
    }

    /**
     * 曝光日期
     **/
    public void setExposureDate(String exposureDate) {
        this.exposureDate = exposureDate == null ? null : exposureDate.trim();
    }

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
    
}