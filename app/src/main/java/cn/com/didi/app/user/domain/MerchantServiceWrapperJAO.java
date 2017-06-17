package cn.com.didi.app.user.domain;

import cn.com.didi.user.users.domain.MerchantServiceDto;

public class MerchantServiceWrapperJAO {
	private MerchantServiceDto serviceDto;

	public MerchantServiceWrapperJAO(MerchantServiceDto serviceDto) {
		super();
		this.serviceDto = serviceDto;
	}

	public Integer getSlsId() {
		return serviceDto.getSlsId();
	}

	public void setSlsId(Integer slsId) {
		serviceDto.setSlsId(slsId);
	}

	public String getCname() {
		return serviceDto.getCname();
	}

	public void setCname(String cname) {
		serviceDto.setCname(cname);
	}
	
}
