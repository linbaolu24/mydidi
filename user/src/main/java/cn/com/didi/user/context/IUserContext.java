package cn.com.didi.user.context;

import cn.com.didi.core.property.ICastAble;

/**
 * <p>用户上下文</p>
 * @author xlm
 *
 */
public interface IUserContext extends ICastAble{
	/**
	 * @return
	 */
	String getUserName();
	/**
	 * @return
	 */
	Long getAccountId();
	/**
	 * 获取电话
	 * @return
	 */
	String getPhone();
	/**
	 * 获取角色名
	 * @return
	 */
	String getRole();
	/**
	 * 获取联系方式
	 * @return
	 */
	String getContactInformation();
	/**
	 * 获取图像
	 * @return
	 */
	String getProfilePhoto();
	/**
	 * 判断是否有效状态
	 * @return
	 */
	boolean isValid();
	/**
	 * 获取用户名
	 * @return
	 */
	String getName();

}
