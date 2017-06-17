package cn.com.didi.domain.domains;


public class SuiteTicketInfoDto {

    private String SuiteId ;
    
    private String InfoType;
    
    private String TimeStamp;
    
    private String SuiteTicket;

    public String getSuiteId() {
        return SuiteId;
    }

    public void setSuiteId(String suiteId) {
        SuiteId = suiteId;
    }

    public String getInfoType() {
        return InfoType;
    }

    public void setInfoType(String infoType) {
        InfoType = infoType;
    }

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    public String getSuiteTicket() {
        return SuiteTicket;
    }

    public void setSuiteTicket(String suiteTicket) {
        SuiteTicket = suiteTicket;
    }
    
    @Override
    public String toString(){
        return "SuiteId:"+SuiteId+"  InfoType:"+InfoType+"  TimeStamp:"+TimeStamp+"  SuiteTicket:"+SuiteTicket;
        
    }
    
}
