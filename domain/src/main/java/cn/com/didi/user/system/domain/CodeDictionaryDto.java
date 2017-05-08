package cn.com.didi.user.system.domain;

import java.io.Serializable;

public class CodeDictionaryDto extends CodeDictionaryDtoKey implements Serializable {
    /**
     * 0代表收入 1代表支出
     */
    private String text;

    /**
     * 显示顺序
     */
    private Integer displayOrder;

    private static final long serialVersionUID = 1L;

    /**
     * 0代表收入 1代表支出
     **/
    public String getText() {
        return text;
    }

    /**
     * 0代表收入 1代表支出
     **/
    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    /**
     * 显示顺序
     **/
    public Integer getDisplayOrder() {
        return displayOrder;
    }

    /**
     * 显示顺序
     **/
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
}