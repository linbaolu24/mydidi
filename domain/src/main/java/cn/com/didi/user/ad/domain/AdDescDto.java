package cn.com.didi.user.ad.domain;

public class AdDescDto {
	 /**
     * 广告ID
     */
    private Long adId;
    /**
     * 落地页URL
     */
    private String lpUrl;

    /**
     * 图片URL
     */
    private String imgUrl;

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getLpUrl() {
		return lpUrl;
	}

	public void setLpUrl(String lpUrl) {
		this.lpUrl = lpUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
    
}
