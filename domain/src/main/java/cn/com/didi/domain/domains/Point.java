package cn.com.didi.domain.domains;

import java.awt.geom.Point2D;
import java.math.BigDecimal;



public class Point {
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public Point(BigDecimal lng, BigDecimal lat) {
		this(lng.toString(),lat.toString());
	}
	public Point(String lng, String lat) {
		super();
		this.lng = lng;
		this.lat = lat;
	}
	public java.awt.geom.Point2D.Double toDoublePoint(){
		return new Point2D.Double(Double.parseDouble(lng),Double.parseDouble(lat));
	}
	/**
	 * 
	 */
	private String lng;//	经度
	/**
	 * 
	 */
	private String lat;//	纬度
    
}
