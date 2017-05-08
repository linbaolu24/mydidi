package cn.com.didi.user.system.domain;

import java.io.Serializable;

public class CodeDictionaryDtoKey implements Serializable {
    /**
     * 名字
     */
    private String cname;

    /**
     * 失败活着取消原因
     */
    private String dcode;

    private static final long serialVersionUID = 1L;

    /**
     * 名字
     **/
    public String getCname() {
        return cname;
    }

    /**
     * 名字
     **/
    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }

    /**
     * 失败活着取消原因
     **/
    public String getDcode() {
        return dcode;
    }

    /**
     * 失败活着取消原因
     **/
    public void setDcode(String dcode) {
        this.dcode = dcode == null ? null : dcode.trim();
    }
}