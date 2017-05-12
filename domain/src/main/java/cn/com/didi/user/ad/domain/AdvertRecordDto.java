package cn.com.didi.user.ad.domain;

import java.io.Serializable;
import java.util.Date;

public class AdvertRecordDto implements Serializable {
    /**
     * 记录ID
     */
    private Long recordId;

    /**
     * 广告ID
     */
    private Long adId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 表示 0 表示曝光 1 表示点击
     */
    private String category;

    private String phoneType;

    private Long accountId;

    private String displayPosition;

    private static final long serialVersionUID = 1L;

    /**
     * 记录ID
     **/
    public Long getRecordId() {
        return recordId;
    }

    /**
     * 记录ID
     **/
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
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
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 表示 0 表示曝光 1 表示点击
     **/
    public String getCategory() {
        return category;
    }

    /**
     * 表示 0 表示曝光 1 表示点击
     **/
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getDisplayPosition() {
        return displayPosition;
    }

    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition == null ? null : displayPosition.trim();
    }
}