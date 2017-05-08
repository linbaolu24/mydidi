package cn.com.didi.order.trade.domain;

import java.io.Serializable;
import java.util.Date;

public class DepositDto implements Serializable {
    /**
     * 交易id
     */
    private Long depositId;

    /**
     * 状态 0 表示正常 1表示退款
     */
    private String state;

    /**
     * 失败活着取消原因
     */
    private String cause;

    /**
     * 金额 单位分 
     */
    private Long amount;

    private Date endTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 备注
     */
    private String cment;

    /**
     * 源账户ID
     */
    private Long sai;

    /**
     * 订单ID
     */
    private Long dealId;

    /**
     * 账户类型 0 表示支付宝1 微信
     */
    private String pat;

    /**
     * 0 表示待确认 1表示已确认 2失败 
     */
    private String payState;

    /**
     * 绑定手机号
     */
    private String bpn;

    private static final long serialVersionUID = 1L;

    /**
     * 交易id
     **/
    public Long getDepositId() {
        return depositId;
    }

    /**
     * 交易id
     **/
    public void setDepositId(Long depositId) {
        this.depositId = depositId;
    }

    /**
     * 状态 0 表示正常 1表示退款
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态 0 表示正常 1表示退款
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
     * 金额 单位分 
     **/
    public Long getAmount() {
        return amount;
    }

    /**
     * 金额 单位分 
     **/
    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
     * 订单ID
     **/
    public Long getDealId() {
        return dealId;
    }

    /**
     * 订单ID
     **/
    public void setDealId(Long dealId) {
        this.dealId = dealId;
    }

    /**
     * 账户类型 0 表示支付宝1 微信
     **/
    public String getPat() {
        return pat;
    }

    /**
     * 账户类型 0 表示支付宝1 微信
     **/
    public void setPat(String pat) {
        this.pat = pat == null ? null : pat.trim();
    }

    /**
     * 0 表示待确认 1表示已确认 2失败 
     **/
    public String getPayState() {
        return payState;
    }

    /**
     * 0 表示待确认 1表示已确认 2失败 
     **/
    public void setPayState(String payState) {
        this.payState = payState == null ? null : payState.trim();
    }

    /**
     * 绑定手机号
     **/
    public String getBpn() {
        return bpn;
    }

    /**
     * 绑定手机号
     **/
    public void setBpn(String bpn) {
        this.bpn = bpn == null ? null : bpn.trim();
    }
}