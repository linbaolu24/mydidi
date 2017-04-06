package cn.com.didi.core.shape.impl;

import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

import cn.com.didi.core.shape.IPoint;
import cn.com.didi.core.shape.IShape;

public class PolygonShape implements IShape {
	protected GeneralPath polygon;
	protected IPoint[] pointArray;
	protected IPoint[] leftAndRight;

	@Override
	public boolean contains(IPoint point) {
		return contains(point.toPoint());
	}

	public boolean contains(Point2D point) {
		init();
		return polygon.contains(point);
	}

	public void init() {
		if (polygon == null) {
			polygon = new GeneralPath();
			IPoint first = null;
			Point2D first2D = null;
			for (IPoint one : pointArray) {
				Point2D temp = one.toPoint();
				if (first == null) {
					first = one;
					first2D = temp;
					polygon.moveTo(temp.getX(), temp.getX());
				}
				polygon.lineTo(temp.getX(), temp.getX());
			}
			polygon.lineTo(first2D.getX(), first2D.getX());
			polygon.closePath();
		}
	}

	public PolygonShape(IPoint[] list) {
		this.pointArray = list;
	}

	public void initRect(){
		if(leftAndRight==null){
			BigDecimal minX=null;
			BigDecimal minY=null;
			BigDecimal maxX=null;
			BigDecimal maxY=null;
			for (IPoint one : pointArray) {
				minX=min(minX, one.getX());
				minY=min(minY, one.getY());
				maxX=max(maxX, one.getX());
				maxY=max(maxY,one.getY());
			}
			leftAndRight= new IPoint[2];
			leftAndRight[0]=new SimplePoint(minX,minY);
			leftAndRight[1]=new SimplePoint(maxX,maxY);
		}
	}
	protected BigDecimal min(BigDecimal one,BigDecimal two){
		if(one==null){
			return two;
		}
		return one.compareTo(two)>=0?two:one;
	}
	
	protected BigDecimal max(BigDecimal one,BigDecimal two){
		if(one==null){
			return two;
		}
		return one.compareTo(two)<0?two:one;
	}

	@Override
	public IPoint[] containingRect() {
		initRect();
		return leftAndRight;
	}

}
