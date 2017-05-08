package cn.com.didi.user.item.domain;

import java.io.Serializable;

import cn.com.didi.domain.domains.CityCodeDto;

public class SlsCityDtoKey implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3598330578836572793L;

	/**
     * 服务ID
     */
    private Integer serviceId;

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 服务ID
     **/
    public Integer getServiceId() {
        return serviceId;
    }

    /**
     * 服务ID
     **/
    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    /**
     * 城市代码
     **/
    public String getCityCode() {
        return cityCode;
    }

    /**
     * 城市代码
     **/
    public void setCityCode(String cityCode) {
        this.cityCode = cityCode == null ? null : cityCode.trim();
    }
    public CityCodeDto toCityCodeDto(){
    	return new CityCodeDto(getCityCode());
    }
}