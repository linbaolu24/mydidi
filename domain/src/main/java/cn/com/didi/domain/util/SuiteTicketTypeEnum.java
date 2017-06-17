package cn.com.didi.domain.util;


public enum SuiteTicketTypeEnum {

    SUITE_TICKET ("suite_ticket","推送suite_ticket协议"),
    CHANGE_AUTH ("change_auth","变更授权协议"),
    CANCEL_AUTH ("cancel_auth","取消授权");
    
    SuiteTicketTypeEnum(String code,String desc ){
     this.code = code;
     this.desc = desc;
    }
    private String code ;
    
    private String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
}
