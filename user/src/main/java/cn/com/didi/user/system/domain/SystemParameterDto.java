package cn.com.didi.user.system.domain;

import java.io.Serializable;

public class SystemParameterDto implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3041755963198489153L;

	/**
     * 代码
     */
    private String paramCode;

    /**
     * 0代表收入 1代表支出
     */
    private String paramValue;

    /**
     * 描述
     */
    private Integer description;

    /**
     * 代码
     **/
    public String getParamCode() {
        return paramCode;
    }

    /**
     * 代码
     **/
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode == null ? null : paramCode.trim();
    }

    /**
     * 0代表收入 1代表支出
     **/
    public String getParamValue() {
        return paramValue;
    }

    /**
     * 0代表收入 1代表支出
     **/
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    /**
     * 描述
     **/
    public Integer getDescription() {
        return description;
    }

    /**
     * 描述
     **/
    public void setDescription(Integer description) {
        this.description = description;
    }
}