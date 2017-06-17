package cn.com.didi.order.trade.domain;

import java.io.Serializable;
import java.util.Date;

public class DealDto implements Serializable {
    /**
     * 交易id
     */
    private Long dealId;

    /**
     * 0 表示待确认 1表示已确认 2失败
     */
    private String state;

    /**
     * 失败活着取消原因
     */
    private String cause;

    /**
     * 0代表收入 1代表支出
     */
    private String dealType;

    /**
     * 源账户类型
     */
    private String sat;

    /**
     * source account源账户
     */
    private String sa;

    /**
     * 目标账户类型
     */
    private String dat;

    /**
     * 目标账户
     */
    private String da;

    /**
     * 单位分
     */
    private Integer amount;

    /**
     * 0表示平台收入 1表示体现 2表示违约金
     */
    private String category;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String cment;

    /**
     * 余额
     */
    private Long remain;

    /**
     * 源账户ID
     */
    private Long sai;

    /**
     * 目标账户ID
     */
    private Long dai;

    /**
     * 佣金,用于违约
     */
    private Integer commission;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 交易系统支付ID
     */
    private String tradeId;

    /**
     * 用于手续费
     */
    private Integer poundage;

    /**
     * 扩展1
     */
    private String ext1;

    /**
     * 扩展2
     */
    private String ext2;

    /**
     * 特殊类型 0 表示正常 1 表示美容美发
     */
    private String specialType;

    /**
     * 交易系统信息
     */
    private String tradeinfo;

    private static final long serialVersionUID = 1L;

    /**
     * 交易id
     **/
    public Long getDealId() {
        return dealId;
    }

    /**
     * 交易id
     **/
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    /**
     * 0 表示待确认 1表示已确认 2失败
     **/
    public String getState() {
        return state;
    }

    /**
     * 0 表示待确认 1表示已确认 2失败
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
     * 0代表收入 1代表支出
     **/
    public String getDealType() {
        return dealType;
    }

    /**
     * 0代表收入 1代表支出
     **/
    public void setDealType(String dealType) {
        this.dealType = dealType == null ? null : dealType.trim();
    }

    /**
     * 源账户类型
     **/
    public String getSat() {
        return sat;
    }

    /**
     * 源账户类型
     **/
    public void setSat(String sat) {
        this.sat = sat == null ? null : sat.trim();
    }

    /**
     * source account源账户
     **/
    public String getSa() {
        return sa;
    }

    /**
     * source account源账户
     **/
    public void setSa(String sa) {
        this.sa = sa == null ? null : sa.trim();
    }

    /**
     * 目标账户类型
     **/
    public String getDat() {
        return dat;
    }

    /**
     * 目标账户类型
     **/
    public void setDat(String dat) {
        this.dat = dat == null ? null : dat.trim();
    }

    /**
     * 目标账户
     **/
    public String getDa() {
        return da;
    }

    /**
     * 目标账户
     **/
    public void setDa(String da) {
        this.da = da == null ? null : da.trim();
    }

    /**
     * 单位分
     **/
    public Integer getAmount() {
        return amount;
    }

    /**
     * 单位分
     **/
    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /**
     * 0表示平台收入 1表示体现 2表示违约金
     **/
    public String getCategory() {
        return category;
    }

    /**
     * 0表示平台收入 1表示体现 2表示违约金
     **/
    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
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
     * 更新时间
     **/
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     **/
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 备注
     **/
    public String getCment() {
        return cment;
    }

    /**
     * 备注
     **/
    public void setCment(String cment) {
        this.cment = cment == null ? null : cment.trim();
    }

    /**
     * 余额
     **/
    public Long getRemain() {
        return remain;
    }

    /**
     * 余额
     **/
    public void setRemain(Long remain) {
        this.remain = remain;
    }

    /**
     * 源账户ID
     **/
    public Long getSai() {
        return sai;
    }

    /**
     * 源账户ID
     **/
    public void setSai(Long sai) {
        this.sai = sai;
    }

    /**
     * 目标账户ID
     **/
    public Long getDai() {
        return dai;
    }

    /**
     * 目标账户ID
     **/
    public void setDai(Long dai) {
        this.dai = dai;
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

    /**
     * 订单ID
     **/
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     **/
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 交易系统支付ID
     **/
    public String getTradeId() {
        return tradeId;
    }

    /**
     * 交易系统支付ID
     **/
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId == null ? null : tradeId.trim();
    }

    public Integer getPoundage() {
        return poundage;
    }

    public void setPoundage(Integer poundage) {
        this.poundage = poundage;
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

    /**
     * 特殊类型 0 表示正常 1 表示美容美发
     **/
    public String getSpecialType() {
        return specialType;
    }

    /**
     * 特殊类型 0 表示正常 1 表示美容美发
     **/
    public void setSpecialType(String specialType) {
        this.specialType = specialType == null ? null : specialType.trim();
    }

    /**
     * 交易系统信息
     **/
    public String getTradeinfo() {
        return tradeinfo;
    }

    /**
     * 交易系统信息
     **/
    public void setTradeinfo(String tradeinfo) {
        this.tradeinfo = tradeinfo == null ? null : tradeinfo.trim();
    }
}