package cn.com.didi.user.item.service;

import java.util.List;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.user.item.domain.FlServiceDto;
import cn.com.didi.user.item.domain.FlServiceItemDto;
import cn.com.didi.user.item.domain.FlsItemDto;
import cn.com.didi.user.item.domain.SlServiceDto;
import cn.com.didi.user.item.domain.SlsCityDto;
import cn.com.didi.user.item.domain.SlsItemDto;
import cn.com.didi.user.item.domain.SlsSericeExtDto;

public interface IItemService {
	/**
	 * @param interval
	 * @return
	 */
	public IPage<FlServiceDto> selectFls(TimeInterval interval);
	
	/**
	 * @param interval
	 * @return
	 */
	public IPage<SlServiceDto> selectSls(TimeInterval interval);
	/**
	 * @param flServiceDto
	 */
	public void addFlService(FlServiceDto flServiceDto);
	/**
	 * 查询所有一级服务
	 * @return
	 */
	public List<FlServiceDto> selectAllFlService();
	/**
	 * @param flsId
	 * @return
	 */
	public List<SlServiceDto> selectSls(Integer flsId);
	
	/**
	 * @param flsId
	 * @return
	 */
	public List<SlServiceDto> selectSlsList(List<Integer> slsIdS);
	/**
	 * 选择最大一级服务
	 * @return
	 */
	public Integer selectMaxFlsDisplayOrder();
	/**
	 * 选择最大二级服务
	 * @return
	 */
	public Integer selectMaxSlsDisplayOrder(Integer flsId);
	/**
	 * 新增二级服务
	 * @param dto
	 */
	public void addSlsService(SlsSericeExtDto dto);
	/**
	 * @param dto
	 * @param cityList
	 */
	public void addSlsService(SlServiceDto dto,List<SlsCityDto> cityList);
	/**
	 * 修改二级服务状态
	 * @param serviceId
	 * @param state
	 */
	public void updateSlsState(Integer serviceId,String state);
	/**
	 * 修改一级服务状态
	 * @param serviceId
	 * @param state
	 */
	public void updateFlsState(Integer serviceId,String state);
	/**查询Item,该方法不过滤不存在二级服务的一级服务*/
	public List<FlServiceItemDto> selectItems();
	
	/**查询Item*/
	public List<SlsItemDto> selectSlItems(Integer flsId);
	
	/**查询Item*/
	public List<SlsItemDto> selectSlItems(List<Integer> flsId);
	
	public SlServiceDto selectSlService(Integer slsId);
	/**
	 * 查询所有一级项目
	 * @return
	 */
	public List<FlsItemDto> selectAllFlsItem();
	
}
