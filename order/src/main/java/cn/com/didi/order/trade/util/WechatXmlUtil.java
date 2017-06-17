package cn.com.didi.order.trade.util;

import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class WechatXmlUtil {

	private static final Log LOGGER = LogFactory.getLog(WechatXmlUtil.class);

	public static final Map<String, Object> parse(final String source) throws DocumentException {
		if (StringUtils.isBlank(source)) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("解析的数据源字符串[source]为空，不进行解析，直接返回空Map");
				return Collections.emptyMap();
			}
		}
		Map<String, Object> parseResult = new HashMap<String, Object>();
		SAXReader saxReader = new SAXReader();
		StringReader sr = new StringReader(source);
		Document document;

		document = saxReader.read(sr);

		Element root = document.getRootElement();
		parse(root, parseResult);
		return parseResult;
	}

	/**
	 * 递归解析XML,放入到map中
	 * 
	 * @param element
	 *
	 * @param storeMap
	 */
	@SuppressWarnings("unchecked")
	private static void parse(Element element, Map<String, Object> storeMap) {
		if (null == element || null == storeMap) {
			return;
		}
		List<Element> elements = element.elements();
		if (elements.isEmpty()) {
			storeMap.put(element.getName(), element.getText());
		} else {
			for (Element e : elements) {
				parse(e, storeMap);
			}
		}

	}

}
