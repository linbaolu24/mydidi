package cn.com.didi.domain.domains;

public class IdStateDto {
    private Long id;

    private String state;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    public void setAccountId(Long accountId){
    	setId(accountId);
    }
    
    public void setMessageId(Long messageId){
    	setId(messageId);
    }
}
