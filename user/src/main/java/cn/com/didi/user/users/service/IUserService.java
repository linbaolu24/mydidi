package cn.com.didi.user.users.service;

import java.util.List;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

/**
 * 用户服务
 * @author xlm
 *
 */
public interface IUserService {
	/**
	 * 根据accountId查询,不会对密码解密
	 * @param accountId
	 * @return
	 */
	public UserDto selectUser(Long accountId);
	/**
	 * 根据用户名查询,会对密码解密
	 * @param accountId
	 * @return
	 */
	public UserDto selectUser(String userName,String role);
	/**
	 * 新增用户,自动产生accountId
	 * @param userDto
	 */
	public void addUser(UserDto userDto);
	/**
	 * 获取解码后的密码
	 * @param userName
	 * @return
	 */
	public String getPassword(String userName,String role);
	
	/**
	 * 修改密码
	 * @param userName
	 * @return
	 */
	public int updatePassword(String userName,String role,String newPassword);
	
	/**
	 * 修改密码
	 * @param userName
	 * @return
	 */
	public int updatePassword(String userName,String role,String newPassword,String oPassword);
	
	/**
	 *修改密码
	 * @param userName
	 * @return
	 */
	public int updatePassword(Long accountID,String newPassword,String oPassword);
	/**
	 * TODO 因为地区表原因sql未写
	 * 选择用户
	 * @param interval
	 * @return
	 */
	public IPage<UserDto> selectUsers(TimeInterval interval);
	
	/**
	 * TODO 因为地区表原因sql未写
	 * 选择用户
	 * @param interval
	 * @return
	 */
	public IPage<UserDto> selectPlatformUsers(TimeInterval interval);
	
	public void updatePlatformUser(UserDto dto);
	/**
	 * 更新用户状态
	 * @param accountId
	 * @param state
	 */
	public void updateUserState(Long accountId,String state);
	
	/**
	 * h
	 * @param accountId
	 */
	public UserLinkIdDto selectUserLinkedId(Long accountId);
	
	/**
	 * h
	 * @param accountId
	 */
	public List<UserLinkIdDto> selectUserLinkedId(List<Long> accountId);
	/**
	 * @param accountId
	 * @param gtCid
	 * @param ryToken
	 */
	public int updateLinkedId(Long accountId,String gtCid,String ryToken);
}
