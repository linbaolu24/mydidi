package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerchantAreaDto extends MerchantAreaDtoKey implements Serializable {
    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 区域形状
     */
    private String shape;

    /**
     * 点
     */
    private String point;

    /**
     * 右上角经度
     */
    private BigDecimal rlat;

    /**
     * 右上角纬度
     */
    private BigDecimal rlng;
    
    // add by my
    private String area;

    private static final long serialVersionUID = 1L;

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

    /**
     * 区域形状
     **/
    public String getShape() {
        return shape;
    }

    /**
     * 区域形状
     **/
    public void setShape(String shape) {
        this.shape = shape == null ? null : shape.trim();
    }

    /**
     * 点
     **/
    public String getPoint() {
        return point;
    }

    /**
     * 点
     **/
    public void setPoint(String point) {
        this.point = point == null ? null : point.trim();
    }

    /**
     * 右上角经度
     **/
    public BigDecimal getRlat() {
        return rlat;
    }

    /**
     * 右上角经度
     **/
    public void setRlat(BigDecimal rlat) {
        this.rlat = rlat;
    }

    /**
     * 右上角纬度
     **/
    public BigDecimal getRlng() {
        return rlng;
    }

    /**
     * 右上角纬度
     **/
    public void setRlng(BigDecimal rlng) {
        this.rlng = rlng;
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
}