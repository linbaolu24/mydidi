package cn.com.didi.domain.domains;

import java.io.Serializable;

public class UseAbleDto<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int used;
	private int count;
	private T data;
	private String description;
	public int getUsed() {
		return used;
	}
	public void setUsed(int used) {
		this.used = used;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UseAbleDto(int used, int count, String description) {
		super();
		this.used = used;
		this.count = count;
		this.description = description;
	}
	
}
