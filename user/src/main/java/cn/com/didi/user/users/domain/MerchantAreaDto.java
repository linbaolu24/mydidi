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

    /**
     * 形状名称
     */
    private String cname;

    /**
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;
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

    /**
     * 形状名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 形状名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 扩展1
     **/
    public String getExt1() {
        return ext1;
    }

    /**
     * 扩展1
     **/
    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    /**
     * 扩展2
     **/
    public String getExt2() {
        return ext2;
    }

    /**
     * 扩展2
     **/
    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
}