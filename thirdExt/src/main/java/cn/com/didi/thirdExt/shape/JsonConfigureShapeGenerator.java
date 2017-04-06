package cn.com.didi.thirdExt.shape;

import java.util.List;


import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;

import cn.com.didi.core.shape.IShape;
import cn.com.didi.core.shape.IShapeGenerator;
import cn.com.didi.core.shape.impl.PolygonShape;
import cn.com.didi.domain.util.ShapeEnum;

public class JsonConfigureShapeGenerator implements IShapeGenerator{
	private static final Logger LOGGER=org.slf4j.LoggerFactory.getLogger(JsonConfigureShapeGenerator.class);
	@Override
	public IShape generatorShape(String type, String configure) {
		if(!ShapeEnum.P.getCode().equalsIgnoreCase(type)){
			LOGGER.info("不支持的形状"+type);
			return null;
		}
		List<LatLngPoint> points=JSON.parseArray(configure, LatLngPoint.class);
		return new PolygonShape(points.toArray(new LatLngPoint[0]));
	}
}
