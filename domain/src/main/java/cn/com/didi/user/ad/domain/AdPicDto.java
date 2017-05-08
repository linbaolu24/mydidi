package cn.com.didi.user.ad.domain;

import java.io.Serializable;

public class AdPicDto implements Serializable {
    /**
     * bigint(20)
     */
    private Long picId;

    /**
     * 广告ID
     */
    private Long adId;

    /**
     * 到小时的时间单元
     */
    private String imgUrl;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 手机型号
     */
    private String phoneType;

    private static final long serialVersionUID = 1L;

    /**
     * bigint(20)
     **/
    public Long getPicId() {
        return picId;
    }

    /**
     * bigint(20)
     **/
    public void setPicId(Long picId) {
        this.picId = picId;
    }

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
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 到小时的时间单元
     **/
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 宽
     **/
    public Integer getWidth() {
        return width;
    }

    /**
     * 宽
     **/
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 高
     **/
    public Integer getHeight() {
        return height;
    }

    /**
     * 高
     **/
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 手机型号
     **/
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 手机型号
     **/
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }
}