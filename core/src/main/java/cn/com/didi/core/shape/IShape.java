package cn.com.didi.core.shape;

public interface IShape {
	boolean contains(IPoint point);
	/**
	 * 包含该图形的左下角和右上角
	 * @return
	 */
	IPoint[] containingRect();
}
