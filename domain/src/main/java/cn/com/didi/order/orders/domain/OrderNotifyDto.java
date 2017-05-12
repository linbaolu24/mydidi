package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderNotifyDto extends OrderNotifyDtoKey implements Serializable {
    /**
     * 二级服务id
     */
    private Integer slsId;

    /**
     * 一级服务id
     */
    private Integer flsId;

    /**
     * 描述
     */
    private String description;

    /**
     * 服务名称
     */
    private String cname;

    /**
     * 客户联系方式
     */
    private String cci;

    /**
     * 客户地址
     */
    private String consumerAddress;

    /**
     * 客户地址代码
     */
    private String cas;

    /**
     * 客户名称
     */
    private String consumerName;

    /**
     * 客户账户id
     */
    private Long consumerAccountId;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 有效标志 0 表示正常 1 表示无效
     */
    private String validFlag;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    /**
     * 二级服务id
     **/
    public Integer getSlsId() {
        return slsId;
    }

    /**
     * 二级服务id
     **/
    public void setSlsId(Integer slsId) {
        this.slsId = slsId;
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
     * 描述
     **/
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     **/
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
     * 客户联系方式
     **/
    public String getCci() {
        return cci;
    }

    /**
     * 客户联系方式
     **/
    public void setCci(String cci) {
        this.cci = cci == null ? null : cci.trim();
    }

    /**
     * 客户地址
     **/
    public String getConsumerAddress() {
        return consumerAddress;
    }

    /**
     * 客户地址
     **/
    public void setConsumerAddress(String consumerAddress) {
        this.consumerAddress = consumerAddress == null ? null : consumerAddress.trim();
    }

    /**
     * 客户地址代码
     **/
    public String getCas() {
        return cas;
    }

    /**
     * 客户地址代码
     **/
    public void setCas(String cas) {
        this.cas = cas == null ? null : cas.trim();
    }

    /**
     * 客户名称
     **/
    public String getConsumerName() {
        return consumerName;
    }

    /**
     * 客户名称
     **/
    public void setConsumerName(String consumerName) {
        this.consumerName = consumerName == null ? null : consumerName.trim();
    }

    /**
     * 客户账户id
     **/
    public Long getConsumerAccountId() {
        return consumerAccountId;
    }

    /**
     * 客户账户id
     **/
    public void setConsumerAccountId(Long consumerAccountId) {
        this.consumerAccountId = consumerAccountId;
    }

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
     * 有效标志 0 表示正常 1 表示无效
     **/
    public String getValidFlag() {
        return validFlag;
    }

    /**
     * 有效标志 0 表示正常 1 表示无效
     **/
    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag == null ? null : validFlag.trim();
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
}