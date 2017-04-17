package cn.com.didi.core.property;

public class Couple<A,B> implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1000854262629616918L;
	public A getFirst() {
		return first;
	}
	public void setFirst(A first) {
		this.first = first;
	}
	public B getSecond() {
		return second;
	}
	public void setSecond(B second) {
		this.second = second;
	}
	public Couple(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}
	public Couple(){
		
	}
	private A first;
	private B second;
	public static <AA,BB> Couple<AA,BB> empty(){
		return new Couple<>();
	}
}
