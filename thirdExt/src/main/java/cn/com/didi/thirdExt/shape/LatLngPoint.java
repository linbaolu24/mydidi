package cn.com.didi.thirdExt.shape;

import java.awt.geom.Point2D;
import java.math.BigDecimal;

import cn.com.didi.core.shape.IPoint;
import cn.com.didi.core.shape.impl.SimplePoint;
import cn.com.didi.domain.domains.Point;

public class LatLngPoint extends SimplePoint  implements IPoint{
	

	@Override
	public Point2D toPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	Point point=new Point((String)null,(String)null);
	public String getLng() {
		return point.getLng();
	}

	public void setLng(String lng) {
		point.setLng(lng);
		super.setY(new BigDecimal(lng));
	}

	public String getLat() {
		return point.getLat();
	}

	public void setLat(String lat) {
		point.setLat(lat);
		super.setX(new BigDecimal(lat));
	}
	
}
