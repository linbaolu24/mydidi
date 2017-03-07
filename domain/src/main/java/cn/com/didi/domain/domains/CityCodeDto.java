package cn.com.didi.domain.domains;

import java.io.Serializable;

public class CityCodeDto implements Serializable {
	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2484638243408286941L;

	public CityCodeDto(String code) {
		this.cityCode = code;
	}

	public CityCodeDto() {

	}

	private String cityCode;

}
