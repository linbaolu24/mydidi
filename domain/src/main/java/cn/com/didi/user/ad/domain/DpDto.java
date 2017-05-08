package cn.com.didi.user.ad.domain;

import java.io.Serializable;

public class DpDto implements Serializable {
    /**
     * bigint(20)
     */
    private Long dpId;

    /**
     * 宽
     */
    private Integer width;

    /**
     * 高
     */
    private Integer height;

    /**
     * 手机型号
     */
    private String phoneType;

    /**
     * 状态 0表示正常 1 表示无效
     */
    private String state;

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     */
    private String displayPosition;

    /**
     * 展示名字
     */
    private String cname;

    private static final long serialVersionUID = 1L;

    /**
     * bigint(20)
     **/
    public Long getDpId() {
        return dpId;
    }

    /**
     * bigint(20)
     **/
    public void setDpId(Long dpId) {
        this.dpId = dpId;
    }

    /**
     * 宽
     **/
    public Integer getWidth() {
        return width;
    }

    /**
     * 宽
     **/
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * 高
     **/
    public Integer getHeight() {
        return height;
    }

    /**
     * 高
     **/
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 手机型号
     **/
    public String getPhoneType() {
        return phoneType;
    }

    /**
     * 手机型号
     **/
    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType == null ? null : phoneType.trim();
    }

    /**
     * 状态 0表示正常 1 表示无效
     **/
    public String getState() {
        return state;
    }

    /**
     * 状态 0表示正常 1 表示无效
     **/
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     **/
    public String getDisplayPosition() {
        return displayPosition;
    }

    /**
     * 展示位置 0 表示启动页 1表示接单提示框 2表示侧边栏
     **/
    public void setDisplayPosition(String displayPosition) {
        this.displayPosition = displayPosition == null ? null : displayPosition.trim();
    }

    /**
     * 展示名字
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 展示名字
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}