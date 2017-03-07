package cn.com.didi.user.area.domain;

import java.io.Serializable;

public class AreaCodeDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7651703836281529069L;

	/**
     * 地区代码
     */
    private String areaCode;

    /**
     * 名字
     */
    private String cname;

    /**
     * 父地区代码
     */
    private String pAreaCode;

    /**
     * 地区类型 P代表省 C代表市 D 代表区 T代表镇
     */
    private String type;

    /**
     * 地区代码
     **/
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 地区代码
     **/
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 名字
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 名字
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 父地区代码
     **/
    public String getpAreaCode() {
        return pAreaCode;
    }

    /**
     * 父地区代码
     **/
    public void setpAreaCode(String pAreaCode) {
        this.pAreaCode = pAreaCode == null ? null : pAreaCode.trim();
    }

    /**
     * 地区类型 P代表省 C代表市 D 代表区 T代表镇
     **/
    public String getType() {
        return type;
    }

    /**
     * 地区类型 P代表省 C代表市 D 代表区 T代表镇
     **/
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
    
}