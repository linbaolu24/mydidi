package cn.com.didi.user.item.domain;

import java.io.Serializable;
import java.util.Date;

public class FlServiceDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8528117085771664988L;

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
}