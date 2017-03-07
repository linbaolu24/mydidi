package cn.com.didi.domain.query;

public class CountObject {
	public static final CountObject ZERO=new CountObject(0);
	private int count;

	public CountObject(int count) {
		super();
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
