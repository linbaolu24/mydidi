package cn.com.didi.domain.util;

import cn.com.didi.core.property.ICodeAble;

/**
 * 广告分类
 * @author xlm
 *
 */
public enum AdCategoryEnum implements ICodeAble{
	EXPOSURE("0"),//曝光量
	CLICK_RATE("1");//点击量
	private String code;
	
	AdCategoryEnum(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}

}
