package cn.com.didi.domain.domains.wechat;


public  class AWechatCodeDto {
    
    /**
     * 返回状态码
     */
    private Integer errcode ;  
    
    /**
     * 返回状态码描述
     */
    private String errmsg ;

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
    @Override
    public String toString(){
        return "errcode:"+errcode+"  errmsg:"+errmsg;
        
    }

}
