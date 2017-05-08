package cn.com.didi.user.message.domain;

import java.io.Serializable;
import java.util.Date;

public class TMessageDto implements Serializable {
    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 消息类型 0表示系统 1表示其他
     */
    private String mesageType;

    /**
     * 消息角色
     */
    private String role;

    /**
     * 消息账号
     */
    private Long accountId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 状态 0表示正常 1 表示结束 
     */
    private String state;

    /**
     * 三方商户标志
     */
    private String tpmFlag;

    /**
     * 自营商户标志
     */
    private String smFlag;

    /**
     * 用户标志
     */
    private String userFlag;

    /**
     * 消息名称
     */
    private String cname;

    /**
     * 消息内容
     */
    private String text;

    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     **/
    public Long getMessageId() {
        return messageId;
    }

    /**
     * 消息ID
     **/
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * 消息类型 0表示系统 1表示其他
     **/
    public String getMesageType() {
        return mesageType;
    }

    /**
     * 消息类型 0表示系统 1表示其他
     **/
    public void setMesageType(String mesageType) {
        this.mesageType = mesageType == null ? null : mesageType.trim();
    }

    /**
     * 消息角色
     **/
    public String getRole() {
        return role;
    }

    /**
     * 消息角色
     **/
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 消息账号
     **/
    public Long getAccountId() {
        return accountId;
    }

    /**
     * 消息账号
     **/
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
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
     * 状态 0表示正常 1 表示结束 
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态 0表示正常 1 表示结束 
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 三方商户标志
     **/
    public String getTpmFlag() {
        return tpmFlag;
    }

    /**
     * 三方商户标志
     **/
    public void setTpmFlag(String tpmFlag) {
        this.tpmFlag = tpmFlag == null ? null : tpmFlag.trim();
    }

    /**
     * 自营商户标志
     **/
    public String getSmFlag() {
        return smFlag;
    }

    /**
     * 自营商户标志
     **/
    public void setSmFlag(String smFlag) {
        this.smFlag = smFlag == null ? null : smFlag.trim();
    }

    /**
     * 用户标志
     **/
    public String getUserFlag() {
        return userFlag;
    }

    /**
     * 用户标志
     **/
    public void setUserFlag(String userFlag) {
        this.userFlag = userFlag == null ? null : userFlag.trim();
    }

    /**
     * 消息名称
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 消息名称
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 消息内容
     **/
    public String getText() {
        return text;
    }

    /**
     * 消息内容
     **/
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}