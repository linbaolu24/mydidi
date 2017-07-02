package cn.com.didi.app.system.domain;

import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;

public class AdWrapperJAO {
	private AdDto adDto;
	private AdPicDto adPicDto;
	public AdWrapperJAO(AdDto adDto, AdPicDto adPicDto) {
		super();
		this.adDto = adDto;
		this.adPicDto = adPicDto;
	}
	public String getCname() {
		return adDto.getCname();
	}
	public void setCname(String cname) {
		adDto.setCname(cname);
	}
	public String getLpUrl() {
		return adDto.getLpUrl();
	}
	public void setLpUrl(String lpUrl) {
		adDto.setLpUrl(lpUrl);
	}
	public Long getAdId() {
		return adPicDto.getAdId();
	}
	public void setAdId(Long adId) {
		adPicDto.setAdId(adId);
	}
	public String getImgUrl() {
		return adPicDto.getImgUrl();
	}
	public void setImgUrl(String imgUrl) {
		adPicDto.setImgUrl(imgUrl);
	}
	public Integer getWidth() {
		return adPicDto.getWidth();
	}
	public void setWidth(Integer width) {
		adPicDto.setWidth(width);
	}
	public Integer getHeight() {
		return adPicDto.getHeight();
	}
	public void setHeight(Integer height) {
		adPicDto.setHeight(height);
	}
	public String getDisplayPosition() {
		return adDto.getDisplayPosition();
	}
	public void setDisplayPosition(String displayPosition) {
		adDto.setDisplayPosition(displayPosition);
	}
	
}
