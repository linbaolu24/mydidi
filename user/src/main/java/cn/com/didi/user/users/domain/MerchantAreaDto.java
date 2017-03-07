package cn.com.didi.user.users.domain;

import java.math.BigDecimal;

public class MerchantAreaDto extends MerchantAreaDtoKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3435859193180654020L;

	/**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;
    /**
     * 地区
     */
    private String area;

    /**
     * 经度
     **/
    public BigDecimal getLng() {
        return lng;
    }

    /**
     * 经度
     **/
    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    /**
     * 纬度
     **/
    public BigDecimal getLat() {
        return lat;
    }

    /**
     * 纬度
     **/
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
}