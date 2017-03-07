package cn.com.didi.user.item.domain;

import java.util.Date;

import cn.com.didi.domain.domains.CityCodeDto;

public class SlsCityDto extends SlsCityDtoKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8478330533025868806L;
	private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
   
}