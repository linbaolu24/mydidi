package cn.com.didi.app.user.domain;

import cn.com.didi.user.users.domain.VipDescrptionDto;
import cn.com.didi.user.users.domain.VipDto;

public class VipDescriptionJAO {
	 private VipDescrptionDto vipDto;


	public String getCondition() {
		return vipDto.getCondition();
	}

	public void setCondition(String condition) {
		vipDto.setCondition(condition);
	}

	public String getUsable() {
		return vipDto.getUsable();
	}

	public void setUsable(String usable) {
		vipDto.setUsable(usable);
	}

	public String getVipName() {
		return vipDto.getVipName();
	}

	public void setVipName(String vipName) {
		vipDto.setVipName(vipName);
	}

	public String getTitle() {
		return vipDto.getTitle();
	}

	public void setTitle(String title) {
		vipDto.setTitle(title);
	}

	public VipDto vipDto() {
		return vipDto.getVipDto();
	}

	public String getCname() {
		return vipDto.getVipDto().getCname();
	}

	public void setCname(String cname) {
		vipDto.getVipDto().setCname(cname);
	}

	public String getProfilePhoto() {
		return vipDto.getVipDto().getProfilePhoto();
	}

	public void setProfilePhoto(String profilePhoto) {
		vipDto.getVipDto().setProfilePhoto(profilePhoto);
	}

	public String getBpn() {
		return vipDto.getVipDto().getBpn();
	}

	public void setBpn(String bpn) {
		vipDto.getVipDto().setBpn(bpn);
	}

	public void vipDto(VipDescrptionDto vipDto) {
		this.vipDto = vipDto;
	}

	public VipDescriptionJAO(VipDescrptionDto vipDto) {
		super();
		this.vipDto = vipDto;
	}


}
