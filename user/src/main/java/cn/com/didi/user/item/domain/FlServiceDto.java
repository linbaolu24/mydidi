package cn.com.didi.user.item.domain;

import java.io.Serializable;
import java.util.Date;

public class FlServiceDto implements Serializable {
    /**
     * 服务ID
     */
    private Integer serviceId;

    /**
     * 服务名称
     */
    private String cname;

    /**
     * 显示位序
     */
    private Integer displayOrder;

    /**
     * 0表示正常 1表示草稿 2表示隐藏
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 二级服务数量
     */
    private Integer slsNum;

    /**
     * 虚拟标志 0表示正常 1表示虚拟
     */
    private String virtualFlag;

    /**
     * 1 表示美容美发
     */
    private String specialType;

    private String ext1;

    private String ext2;

    private static final long serialVersionUID = 1L;

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
     * 服务名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 服务名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 显示位序
     **/
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 显示位序
     **/
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    /**
     * 0表示正常 1表示草稿 2表示隐藏
     **/
    public String getState() {
        return state;
    }

    /**
     * 0表示正常 1表示草稿 2表示隐藏
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 创建时间
     **/
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     **/
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 二级服务数量
     **/
    public Integer getSlsNum() {
        return slsNum;
    }

    /**
     * 二级服务数量
     **/
    public void setSlsNum(Integer slsNum) {
        this.slsNum = slsNum;
    }

    /**
     * 虚拟标志 0表示正常 1表示虚拟
     **/
    public String getVirtualFlag() {
        return virtualFlag;
    }

    /**
     * 虚拟标志 0表示正常 1表示虚拟
     **/
    public void setVirtualFlag(String virtualFlag) {
        this.virtualFlag = virtualFlag == null ? null : virtualFlag.trim();
    }

    /**
     * 1 表示美容美发
     **/
    public String getSpecialType() {
        return specialType;
    }

    /**
     * 1 表示美容美发
     **/
    public void setSpecialType(String specialType) {
        this.specialType = specialType == null ? null : specialType.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }
}