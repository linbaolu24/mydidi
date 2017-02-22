package cn.com.didi.core.property.impl.setAble;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.core.filter.IFilter;
import cn.com.didi.core.property.ISetAble;

/**
 * @author xlm
 *
 */
public abstract class AbstractSetAble implements ISetAble {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSetAble.class);
	private IFilter<String> filter;

	public void set(String name, Object obj) {
		if (filter != null && !filter.filter(name)) {
			return;
		}
		try {
			setInternal(name, obj);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			throw new IllegalArgumentException("设置参数不合法",e);
		}
	}

	protected void setInternal(String name, Object obj) throws IllegalAccessException, InvocationTargetException {
		BeanUtils.setProperty(this, name, obj);
	}

}
