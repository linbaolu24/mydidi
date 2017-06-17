package cn.com.didi.domain.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.didi.core.property.IConverter;

public abstract class ABeanUtils {

	private static final Log LOGGER = LogFactory.getLog(ABeanUtils.class);

	/**
	 * 通用类型转换
	 * 
	 * @param source
	 * @param target
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public static void commonCopy(Object source, Object target)
			throws IllegalAccessException, InvocationTargetException {
		if (null == source || null == target) {
			LOGGER.error("source 或  target 不能为空");
			return;
		}
		BeanUtils.copyProperties(source, target);
	}

	/**
	 * 将bean转成map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transBean2Map(Object obj) {
		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			LOGGER.error("transBean2Map Error " + e);
		}
		return map;
	}
	public static String transBean2Xml(Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException{
		return transBean2Xml(obj,null);
	}
	/**
	 * bean转xml
	 * 
	 * @param obj
	 * @return 返回对象值不为空的xml
	 * @throws IntrospectionException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String transBean2Xml(Object obj,IConverter<String, String> nameConvert)
			throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		StringBuffer buf = new StringBuffer();
		if (obj == null) {
			return null;
		}
		buf.append("<xml>");

		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor property : propertyDescriptors) {
			String key = property.getName();
			// 过滤class属性
			if (nameConvert != null) {
				key = nameConvert.convert(key);
			}
			if (!StringUtils.isEmpty(key)&&!key.equals("class")) {
				// 得到property对应的getter方法
				Method getter = property.getReadMethod();
				Object value = getter.invoke(obj);
				if (value==null)
					continue;
				buf.append(startXml(key)).append(xmlCDATA(value)).append(endXml(key));
			}
		}

		buf.append("</xml>");
		return buf.toString();
	}

	/**
	 * 将map转为xml
	 * 
	 * @param map
	 * @param contentBlank
	 *            是否包含值为空的标签
	 * @return
	 */
	public static String transMap2Xml(Map<String, Object> map, boolean includeBlank) {
		StringBuffer buf = new StringBuffer();
		if (map == null) {
			return null;
		}
		buf.append("<xml>");

		Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if (!includeBlank) {
				if (null == value)
					continue;
			}
			buf.append(startXml(key)).append(xmlCDATA(value)).append(endXml(key));
		}

		buf.append("</xml>");
		return buf.toString();
	}

	/**
	 * 设置cdata内容
	 * 
	 * @param value
	 * @return
	 */
	private static String xmlCDATA(Object value) {
		if (null == value)
			value = "";
		return "<![CDATA[" + value + "]]>";
	}

	/**
	 * 设置XML节点开头
	 * 
	 * @param key
	 * @return
	 */
	private static String startXml(String key) {
		return "<" + key + ">";
	}

	/**
	 * 设置XML节点结束
	 * 
	 * @param key
	 * @return
	 */
	private static String endXml(String key) {
		return "</" + key + ">";
	}
}
