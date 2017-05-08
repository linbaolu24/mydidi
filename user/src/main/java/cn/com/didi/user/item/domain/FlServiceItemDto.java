package cn.com.didi.user.item.domain;

import java.io.Serializable;

public class FlServiceItemDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1901134262616176031L;
	public Integer getServiceId() {
		return serviceId;
	}
	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public Integer getSlsId() {
		return slsId;
	}
	public void setSlsId(Integer slsId) {
		this.slsId = slsId;
	}
	
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
     * 
     */
    private Integer slsId;
    /**
     * 虚拟标志 0表示正常 1表示虚拟
     */
    private String virtualFlag;
    /**
     * 特殊类型 1 表示美容美发
     */
    private String specialType;
	
	public String getSpecialType() {
		return specialType;
	}
	public void setSpecialType(String specialType) {
		this.specialType = specialType;
	}
	public String getVirtualFlag() {
		return virtualFlag;
	}
	public void setVirtualFlag(String virtualFlag) {
		this.virtualFlag = virtualFlag;
	}
    
    
}