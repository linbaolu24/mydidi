package cn.com.didi.user.ad.domain;

import java.io.Serializable;
import java.util.Date;

public class AdDto implements Serializable {
    /**
     * 广告ID
     */
    private Long adId;

    /**
     * 广告名称
     */
    private String cname;

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     */
    private String displayPosition;

    /**
     * 落地页URL
     */
    private String lpUrl;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 广告排期开始
     */
    private Date adsStart;

    /**
     * 广告排期结束
     */
    private Date adsEnd;

    /**
     * 广告排期时间开始表示24小时
     */
    private Integer adsTimeStart;

    /**
     * 广告排期时间结束表示24小时
     */
    private Integer adsTimeEnd;

    /**
     * 状态 0表示正常 1 表示结束 2表示暂停
     */
    private String state;

    /**
     * 曝光量
     */
    private Integer exposure;

    /**
     * 点击量
     */
    private Integer clickRate;

    /**
     * 图片URL
     */
    private String imgUrl;

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
     * 广告名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 广告名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     **/
    public String getDisplayPosition() {
        return displayPosition;
    }

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     **/
    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition == null ? null : displayPosition.trim();
    }

    /**
     * 落地页URL
     **/
    public String getLpUrl() {
        return lpUrl;
    }

    /**
     * 落地页URL
     **/
    public void setLpUrl(String lpUrl) {
        this.lpUrl = lpUrl == null ? null : lpUrl.trim();
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
     * 广告排期开始
     **/
    public Date getAdsStart() {
        return adsStart;
    }

    /**
     * 广告排期开始
     **/
    public void setAdsStart(Date adsStart) {
        this.adsStart = adsStart;
    }

    /**
     * 广告排期结束
     **/
    public Date getAdsEnd() {
        return adsEnd;
    }

    /**
     * 广告排期结束
     **/
    public void setAdsEnd(Date adsEnd) {
        this.adsEnd = adsEnd;
    }

    /**
     * 广告排期时间开始表示24小时
     **/
    public Integer getAdsTimeStart() {
        return adsTimeStart;
    }

    /**
     * 广告排期时间开始表示24小时
     **/
    public void setAdsTimeStart(Integer adsTimeStart) {
        this.adsTimeStart = adsTimeStart;
    }

    /**
     * 广告排期时间结束表示24小时
     **/
    public Integer getAdsTimeEnd() {
        return adsTimeEnd;
    }

    /**
     * 广告排期时间结束表示24小时
     **/
    public void setAdsTimeEnd(Integer adsTimeEnd) {
        this.adsTimeEnd = adsTimeEnd;
    }

    /**
     * 状态 0表示正常 1 表示结束 2表示暂停
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态 0表示正常 1 表示结束 2表示暂停
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

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
     * 图片URL
     **/
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 图片URL
     **/
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }
}