package cn.com.didi.user.item.domain;

import java.io.Serializable;
import java.util.Date;

public class SlServiceDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6068177226052269744L;

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
     * 状态 0表示正常 1表示草稿 2表示隐藏
     */
    private String state;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 一级服务id
     */
    private Integer flsId;

    /**
     * 业务类型:0表示自营 1表示第三方
     */
    private String businessCategory;

    /**
     * 城市
     */
    private String city;

    /**
     * 业务费用:0代表免费 1代表收费
     */
    private String businessCharge;

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
     * 状态 0表示正常 1表示草稿 2表示隐藏
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态 0表示正常 1表示草稿 2表示隐藏
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
     * 一级服务id
     **/
    public Integer getFlsId() {
        return flsId;
    }

    /**
     * 一级服务id
     **/
    public void setFlsId(Integer flsId) {
        this.flsId = flsId;
    }

    /**
     * 业务类型:0表示自营 1表示第三方
     **/
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * 业务类型:0表示自营 1表示第三方
     **/
    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory == null ? null : businessCategory.trim();
    }

    /**
     * 城市
     **/
    public String getCity() {
        return city;
    }

    /**
     * 城市
     **/
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * 业务费用:0代表免费 1代表收费
     **/
    public String getBusinessCharge() {
        return businessCharge;
    }

    /**
     * 业务费用:0代表免费 1代表收费
     **/
    public void setBusinessCharge(String businessCharge) {
        this.businessCharge = businessCharge == null ? null : businessCharge.trim();
    }
}