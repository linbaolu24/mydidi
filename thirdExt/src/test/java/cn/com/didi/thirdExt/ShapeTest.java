package cn.com.didi.thirdExt;

import java.math.BigDecimal;

import org.junit.Test;

import cn.com.didi.core.shape.IShape;
import cn.com.didi.core.shape.impl.SimplePoint;
import cn.com.didi.thirdExt.shape.JsonConfigureShapeGenerator;

public class ShapeTest {
	@Test
	public void test() {
		JsonConfigureShapeGenerator js = new JsonConfigureShapeGenerator();
		IShape shape = js.generatorShape("P",
				"[{\"lat\":\"25.317627\",\"lng\":\"115.186617\"},{\"lat\":\"35.317627\",\"lng\":\"115.186617\"},{\"lat\":\"35.317627\",\"lng\":\"125.186617\"},{\"lat\":\"25.317627\",\"lng\":\"125.186617\"}]");
		boolean contained=shape.contains(new SimplePoint(new BigDecimal("27.317627"), new BigDecimal("120.186617")));
		System.out.println(contained);
	}
}
