package cn.com.didi.user.context;

import java.util.List;

/**
 * 商户上下文
 * @author xlm
 *
 */
public interface IMerchantContext extends IUserContext{
	/**
	 * 获取服务地区代码列表
	 * @return
	 */
	List<String> getArea();
	/**
	 * 获取地址 代码
	 * @return
	 */
	String getAddress();
	/**
	 * 获取详细信息
	 * @return
	 */
	String getDetailAddress();
	/**
	 * @return
	 */
	String getMerchantName();
}
