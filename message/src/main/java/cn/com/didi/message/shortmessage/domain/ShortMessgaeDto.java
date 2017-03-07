package cn.com.didi.message.shortmessage.domain;

public class ShortMessgaeDto {
	public String[] getPhones() {
		return phones;
	}
	public void setPhones(String[] phones) {
		this.phones = phones;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String[] phones;
	private String content;
	
	
}
