package cn.com.didi.core.shape.impl;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

import cn.com.didi.core.shape.IPoint;


public class SimplePoint implements IPoint{
	private BigDecimal x;
	private BigDecimal y;
	
	public SimplePoint(BigDecimal x, BigDecimal y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public SimplePoint() {
		
	}

	@Override
	public Point2D toPoint() {
		return new Point2D.Double(x.doubleValue(), y.doubleValue());
	}

	public BigDecimal getX() {
		return x;
	}

	public void setX(BigDecimal x) {
		this.x = x;
	}

	public BigDecimal getY() {
		return y;
	}

	public void setY(BigDecimal y) {
		this.y = y;
	}

}
