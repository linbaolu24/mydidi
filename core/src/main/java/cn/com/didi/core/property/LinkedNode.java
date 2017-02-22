package cn.com.didi.core.property;

public interface LinkedNode {
	public LinkedNode getPrevious();

	public LinkedNode getNext();

	public boolean isHeadNode();

	public boolean isTailNode();
}
