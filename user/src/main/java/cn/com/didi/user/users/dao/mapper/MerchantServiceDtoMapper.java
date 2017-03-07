package cn.com.didi.user.users.dao.mapper;

import cn.com.didi.user.users.domain.MerchantServiceDto;
import cn.com.didi.user.users.domain.MerchantServiceDtoExample;
import cn.com.didi.user.users.domain.MerchantServiceDtoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MerchantServiceDtoMapper {
	int countByExample(MerchantServiceDtoExample example);

	int deleteByExample(MerchantServiceDtoExample example);

	int deleteByPrimaryKey(MerchantServiceDtoKey key);

	int insert(MerchantServiceDto record);

	int insertSelective(MerchantServiceDto record);

	List<MerchantServiceDto> selectByExample(MerchantServiceDtoExample example);

	MerchantServiceDto selectByPrimaryKey(MerchantServiceDtoKey key);

	int updateByExampleSelective(@Param("record") MerchantServiceDto record,
			@Param("example") MerchantServiceDtoExample example);

	int updateByExample(@Param("record") MerchantServiceDto record,
			@Param("example") MerchantServiceDtoExample example);

	int updateByPrimaryKeySelective(MerchantServiceDto record);

	int updateByPrimaryKey(MerchantServiceDto record);

	/**
	 * @param key
	 * @return
	 */
	public List<MerchantServiceDto> selectMerchantService(MerchantServiceDtoKey key);
}