package cn.com.didi.user.users.domain;

import java.util.Date;

public class MerchantServiceDto extends MerchantServiceDtoKey {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7069070482716368157L;
	private Date createTime;
    /**
     * 二级服务名
     */
    private String cname;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
    
}