package cn.com.didi.core.shape;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

public interface IPoint {
	public BigDecimal getX();
	public BigDecimal getY();
	Point2D toPoint();
}
