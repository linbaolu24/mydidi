package cn.com.didi.core.utils;

import org.apache.commons.lang.StringUtils;

/**
 * @author xlm
 *
 */
public class AssertUtil {
	public static void assertNotNull(Object obj, String msg) {
		if (obj == null) {
			throw new IllegalArgumentException(msg);
		}

	}
	public static void assertNotNull(String obj, String msg) {
		if (StringUtils.isEmpty(obj)) {
			throw new IllegalArgumentException(msg);
		}

	}
	
	public static void assertNotNullAppend(String obj, String msg) {
		if (StringUtils.isEmpty(obj)) {
			throw new IllegalArgumentException(msg + "不能为空。");
		}

	}

	public static void assertNotNullAppend(Object obj, String key) {
		if (obj == null) {
			throw new IllegalArgumentException(key + "不能为空。");
		}
	}
	
	public static void assertNotNegative(Long obj, String key) {
		if (obj <0) {
			throw new IllegalArgumentException(key + "不能为负。");
		}
	}

}
