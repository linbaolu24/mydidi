package cn.com.didi.user.users.domain;

import java.io.Serializable;
import java.util.Date;

public class UserExperienceDto extends UserExperienceDtoKey implements Serializable {
    /**
     * 体验次数
     */
    private Integer num;

    /**
     * 最后体验时间
     */
    private Date lastTime;

    /**
     * 倒数第二次体验时间 next to last time
     */
    private Date ntlTime;

    private static final long serialVersionUID = 1L;

    /**
     * 体验次数
     **/
    public Integer getNum() {
        return num;
    }

    /**
     * 体验次数
     **/
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 最后体验时间
     **/
    public Date getLastTime() {
        return lastTime;
    }

    /**
     * 最后体验时间
     **/
    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    /**
     * 倒数第二次体验时间 next to last time
     **/
    public Date getNtlTime() {
        return ntlTime;
    }

    /**
     * 倒数第二次体验时间 next to last time
     **/
    public void setNtlTime(Date ntlTime) {
        this.ntlTime = ntlTime;
    }
}