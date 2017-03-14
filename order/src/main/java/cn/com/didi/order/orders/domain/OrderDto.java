package cn.com.didi.order.orders.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDto implements Serializable {
    /**
     * 订单id
     */
    private Long orderId;

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
     * 状态
     */
    private String state;

    /**
     * 失败活着取消原因
     */
    private String cause;

    /**
     * 客户账户id
     */
    private Long consumerAccountId;

    /**
     * 商户账户id
     */
    private Long merchantAccountId;

    /**
     * 经度
     */
    private BigDecimal lng;

    /**
     * 纬度
     */
    private BigDecimal lat;

    /**
     * 业务费用:0代表免费 1代表收费
     */
    private String businessCharge;

    /**
     * 业务类型:0表示自营 1表示第三方
     */
    private String businessCategory;

    /**
     * 师傅
     */
    private String masterName;

    /**
     * 师傅联系方式
     */
    private String mci;

    /**
     * 费用
     */
    private Integer cost;

    /**
     * 交易Id
     */
    private Long dealId;

    /**
     * 接单时间
     */
    private Date ort;

    /**
     * 订单创建时间
     */
    private Date oct;

    /**
     * service_start_tine 开始服务时间
     */
    private Date sst;

    /**
     * order_end_time 订单结束时间
     */
    private Date oet;

    /**
     * 订单完成服务时间
     */
    private Date ofst;

    /**
     * 星级评价
     */
    private Integer evaluation;

    /**
     * 文字评价
     */
    private String textEvaluation;

    /**
     * 取消标志 0 表示正常 1 表示取消
     */
    private String cancelFlag;

    /**
     * 佣金
     */
    private Integer commission;

    private Integer poundage;

    /**
     * 商户经度
     */
    private BigDecimal mlng;

    /**
     * 商户纬度
     */
    private BigDecimal mlat;

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     **/
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     **/
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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
     * 状态
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 失败活着取消原因
     **/
    public String getCause() {
        return cause;
    }

    /**
     * 失败活着取消原因
     **/
    public void setCause(String cause) {
        this.cause = cause == null ? null : cause.trim();
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
     * 商户账户id
     **/
    public Long getMerchantAccountId() {
        return merchantAccountId;
    }

    /**
     * 商户账户id
     **/
    public void setMerchantAccountId(Long merchantAccountId) {
        this.merchantAccountId = merchantAccountId;
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
     * 师傅
     **/
    public String getMasterName() {
        return masterName;
    }

    /**
     * 师傅
     **/
    public void setMasterName(String masterName) {
        this.masterName = masterName == null ? null : masterName.trim();
    }

    /**
     * 师傅联系方式
     **/
    public String getMci() {
        return mci;
    }

    /**
     * 师傅联系方式
     **/
    public void setMci(String mci) {
        this.mci = mci == null ? null : mci.trim();
    }

    /**
     * 费用
     **/
    public Integer getCost() {
        return cost;
    }

    /**
     * 费用
     **/
    public void setCost(Integer cost) {
        this.cost = cost;
    }

    /**
     * 交易Id
     **/
    public Long getDealId() {
        return dealId;
    }

    /**
     * 交易Id
     **/
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    /**
     * 接单时间
     **/
    public Date getOrt() {
        return ort;
    }

    /**
     * 接单时间
     **/
    public void setOrt(Date ort) {
        this.ort = ort;
    }

    /**
     * 订单创建时间
     **/
    public Date getOct() {
        return oct;
    }

    /**
     * 订单创建时间
     **/
    public void setOct(Date oct) {
        this.oct = oct;
    }

    /**
     * service_start_tine 开始服务时间
     **/
    public Date getSst() {
        return sst;
    }

    /**
     * service_start_tine 开始服务时间
     **/
    public void setSst(Date sst) {
        this.sst = sst;
    }

    /**
     * order_end_time 订单结束时间
     **/
    public Date getOet() {
        return oet;
    }

    /**
     * order_end_time 订单结束时间
     **/
    public void setOet(Date oet) {
        this.oet = oet;
    }

    /**
     * 订单完成服务时间
     **/
    public Date getOfst() {
        return ofst;
    }

    /**
     * 订单完成服务时间
     **/
    public void setOfst(Date ofst) {
        this.ofst = ofst;
    }

    /**
     * 星级评价
     **/
    public Integer getEvaluation() {
        return evaluation;
    }

    /**
     * 星级评价
     **/
    public void setEvaluation(Integer evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * 文字评价
     **/
    public String getTextEvaluation() {
        return textEvaluation;
    }

    /**
     * 文字评价
     **/
    public void setTextEvaluation(String textEvaluation) {
        this.textEvaluation = textEvaluation == null ? null : textEvaluation.trim();
    }

    /**
     * 取消标志 0 表示正常 1 表示取消
     **/
    public String getCancelFlag() {
        return cancelFlag;
    }

    /**
     * 取消标志 0 表示正常 1 表示取消
     **/
    public void setCancelFlag(String cancelFlag) {
        this.cancelFlag = cancelFlag == null ? null : cancelFlag.trim();
    }

    /**
     * 佣金
     **/
    public Integer getCommission() {
        return commission;
    }

    /**
     * 佣金
     **/
    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
    }

    /**
     * 商户经度
     **/
    public BigDecimal getMlng() {
        return mlng;
    }

    /**
     * 商户经度
     **/
    public void setMlng(BigDecimal mlng) {
        this.mlng = mlng;
    }

    /**
     * 商户纬度
     **/
    public BigDecimal getMlat() {
        return mlat;
    }

    /**
     * 商户纬度
     **/
    public void setMlat(BigDecimal mlat) {
        this.mlat = mlat;
    }
    @Override
    public String toString(){
    	return "订单编号:"+orderId+"消费者Id:"+consumerAccountId+"商户ID:"+merchantAccountId+"状态:"+state+"cost:"+cost;
    }
}