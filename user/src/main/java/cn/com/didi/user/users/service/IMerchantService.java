package cn.com.didi.user.users.service;

import java.util.List;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.users.MerchantCrDto;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantHolderDto;
import cn.com.didi.user.users.domain.MerchantServiceDto;

/**
 * @author xlm
 *
 */
public interface IMerchantService {
	IPage<MerchantDto> selectMerchants(TimeInterval interval);

	/**
	 * @param accountId
	 * @return
	 */
	MerchantDto selectMerchant(Long accountId);

	/**
	 * @param accountId
	 * @return
	 */
	MerchantDto selectMerchantAndAdress(Long accountId);

	/**
	 * @param accountId
	 * @return
	 */
	MerchantHolderDto getMerchant(Long accountId);

	/**
	 * @param accountId
	 * @return
	 */
	List<MerchantAreaDto> selectMerchantArea(Long accountId);

	/**
	 * @param accountId
	 * @return
	 */
	List<MerchantServiceDto> selectMerchantService(Long accountId);

	/**
	 * @param Merchant
	 */
	//void addMerchant(MerchantExtDto Merchant);

	/**
	 * @param Merchant
	 */
	@Deprecated
	void addMerchant(MerchantDto Merchant, List<MerchantServiceDto> serviceList, List<MerchantAreaDto> areaList);
	
	void addMerchantV2(MerchantDto Merchant, List<MerchantServiceDto> serviceList, List<MerchantAreaDto> areaList);
	/**入驻商户*/
	void enterMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList, List<MerchantAreaDto> areaList);

	// List<MerchantAreaDto> select(MerchantAreaDto leftDown,MerchantAreaDto
	// rightTop,Integer slsId);

	List<MerchantAreaDto> select(Point center, int radius, Integer slsId);

	MerchantDto match(MerchantAreaDto mad, Integer slsId);
	
	/**
	 * 认证
	 * @param accountId
	 * @param cr
	 * @param cause
	 */
	void checkMerchant(Long accountId,String cr,String cause);
	
	/**认证
	 * @param mdtoList
	 */
	void checkMerchant(List<MerchantCrDto> mdtoList);
	/**
	 * @param list
	 */
	void onOff(List<IdStateDto> list);
	
	/**<p>编辑商户</p>
	 * @param merchant
	 * @param serviceList
	 * @param areaList
	 */
	public void editMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) ;
}
