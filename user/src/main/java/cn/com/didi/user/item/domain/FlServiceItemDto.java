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
    
}