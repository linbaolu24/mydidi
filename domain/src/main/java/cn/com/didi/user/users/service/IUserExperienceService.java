package cn.com.didi.user.users.service;

public interface IUserExperienceService {
	/**
	 * 如果可以增加体验返回true
	 * @param accountId
	 * @param slsId
	 * @return
	 */
	public boolean addExperience(Long accountId,Integer slsId);
	
	/**
	 * 如果可以增加体验返回true
	 * @param accountId
	 * @param slsId
	 * @return
	 */
	public boolean releaseExperience(Long accountId,Integer slsId);
}
