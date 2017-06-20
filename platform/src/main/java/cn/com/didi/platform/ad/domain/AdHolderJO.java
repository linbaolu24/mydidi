package cn.com.didi.platform.ad.domain;

import java.util.Date;
import java.util.List;

import cn.com.didi.user.ad.domain.AdDto;
import cn.com.didi.user.ad.domain.AdPicDto;

public class AdHolderJO {
	private AdDto adDto;
	private List<AdPicDto> imgList;
	public AdHolderJO(){
		adDto=new AdDto();
	}
	public Long getAdId() {
		return adDto.getAdId();
	}
	public void setAdId(Long adId) {
		adDto.setAdId(adId);
	}
	public String getCname() {
		return adDto.getCname();
	}
	public void setCname(String cname) {
		adDto.setCname(cname);
	}
	public String getDisplayPosition() {
		return adDto.getDisplayPosition();
	}
	public void setDisplayPosition(String displayPosition) {
		adDto.setDisplayPosition(displayPosition);
	}
	public String getLpUrl() {
		return adDto.getLpUrl();
	}
	public void setLpUrl(String lpUrl) {
		adDto.setLpUrl(lpUrl);
	}
	public Date getCreateTime() {
		return adDto.getCreateTime();
	}
	public void setCreateTime(Date createTime) {
		adDto.setCreateTime(createTime);
	}
	public Date getAdsStart() {
		return adDto.getAdsStart();
	}
	public void setAdsStart(Date adsStart) {
		adDto.setAdsStart(adsStart);
	}
	public Date getAdsEnd() {
		return adDto.getAdsEnd();
	}
	public void setAdsEnd(Date adsEnd) {
		adDto.setAdsEnd(adsEnd);
	}
	public Integer getAdsTimeStart() {
		return adDto.getAdsTimeStart();
	}
	public void setAdsTimeStart(Integer adsTimeStart) {
		adDto.setAdsTimeStart(adsTimeStart);
	}
	public Integer getAdsTimeEnd() {
		return adDto.getAdsTimeEnd();
	}
	public void setAdsTimeEnd(Integer adsTimeEnd) {
		adDto.setAdsTimeEnd(adsTimeEnd);
	}
	public String getState() {
		return adDto.getState();
	}
	public void setState(String state) {
		adDto.setState(state);
	}
	public Integer getExposure() {
		return adDto.getExposure();
	}
	public void setExposure(Integer exposure) {
		adDto.setExposure(exposure);
	}
	public Integer getClickRate() {
		return adDto.getClickRate();
	}
	public void setClickRate(Integer clickRate) {
		adDto.setClickRate(clickRate);
	}
	public String getImgUrl() {
		return adDto.getImgUrl();
	}
	public void setImgUrl(String imgUrl) {
		adDto.setImgUrl(imgUrl);
	}
	public List<AdPicDto> getImgList() {
		return imgList;
	}
	public void setImgList(List<AdPicDto> imgList) {
		this.imgList = imgList;
	}
	public AdDto dto(){
		return adDto;
	}
}
