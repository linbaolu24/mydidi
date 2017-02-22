package cn.com.didi.core.property.impl.identify;

import cn.com.didi.core.property.Identity;

/**
 * @author xlm
 *
 * @param <T>
 */
public class AbstractIDentify<T>  implements Identity<T> {
	protected String description;
	protected String type;
	protected T data;
	

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}
	public boolean match(Identity<T> other) {
		if(other==null){
			return false;
		}
		if(this==other){
			return true;
		}
		if(type.equals(other.getType())){
			return matchInternal(other);
		}
		return false;
	}
	public boolean matchInternal(Identity<T> other){
		return data.equals(other.getData());
	}

}
