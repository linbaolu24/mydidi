package cn.com.didi.user.users.domain;

public class VipDescrptionDto {
	private String condition;
	private String usable;
	private String vipName;
	private String title;
	private VipDto vipDto;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getUsable() {
		return usable;
	}
	public void setUsable(String usable) {
		this.usable = usable;
	}
	public String getVipName() {
		return vipName;
	}
	public void setVipName(String vipName) {
		this.vipName = vipName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public VipDto getVipDto() {
		return vipDto;
	}
	public void setVipDto(VipDto vipDto) {
		this.vipDto = vipDto;
	}
	public VipDescrptionDto cloneSelf(){
		VipDescrptionDto dto=new VipDescrptionDto();
		dto.setCondition(condition);
		dto.setTitle(title);
		dto.setUsable(usable);
		dto.setVipDto(vipDto);
		dto.setVipName(vipName);
		return dto;
	}
	

}
