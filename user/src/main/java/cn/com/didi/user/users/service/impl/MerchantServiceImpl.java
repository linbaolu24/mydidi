package cn.com.didi.user.users.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.select.IPageBound;
import cn.com.didi.core.shape.IPoint;
import cn.com.didi.core.shape.IShape;
import cn.com.didi.core.shape.IShapeGenerator;
import cn.com.didi.core.shape.impl.SimplePoint;
import cn.com.didi.domain.domains.IdStateDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.ArrivalStatusEnum;
import cn.com.didi.domain.util.CrEnum;
import cn.com.didi.domain.util.LatLngUtiil;
import cn.com.didi.domain.util.State;
import cn.com.didi.order.orders.domain.OrderEvaluationDto;
import cn.com.didi.order.orders.service.IOrderInfoService;
import cn.com.didi.thirdExt.select.ListPage;
import cn.com.didi.user.area.service.IAreaService;
import cn.com.didi.user.users.dao.mapper.MerchantAreaDtoMapper;
import cn.com.didi.user.users.dao.mapper.MerchantDtoMapper;
import cn.com.didi.user.users.dao.mapper.MerchantServiceDtoMapper;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantAreaDtoExample;
import cn.com.didi.user.users.domain.MerchantAreaDtoKey;
import cn.com.didi.user.users.domain.MerchantCrDto;
import cn.com.didi.user.users.domain.MerchantDescriptionDto;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantDtoExample;
import cn.com.didi.user.users.domain.MerchantExtDto;
import cn.com.didi.user.users.domain.MerchantHolderDto;
import cn.com.didi.user.users.domain.MerchantServiceDto;
import cn.com.didi.user.users.domain.MerchantServiceDtoExample;
import cn.com.didi.user.users.domain.MerchantServiceDtoKey;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;
import cn.com.didi.user.users.util.MerchantUtils;

/**
 * @author xlm
 *
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MerchantServiceImpl.class);
	@Resource
	protected MerchantDtoMapper merchantMapper;
	@Resource
	protected MerchantAreaDtoMapper merchantAreaDtoMapper;
	@Resource
	protected MerchantServiceDtoMapper merchantServiceDtoMapper;
	@Resource
	protected IUserService userService;
	@Resource
	protected IAreaService areaService;
	@Resource
	protected IShapeGenerator shapeGenerator;
	@Resource
	protected IOrderInfoService orderInfoService;
	//@Resource
	//protected IItemService itemService;

	@Override
	public IPage<MerchantDto> selectMerchants(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), false);
		Integer count= merchantMapper.selectMerchantCount(interval);
		if(count==null){
			count=0;
		}
		List<MerchantDto> list =  merchantMapper.selectMerchants(interval, pageBounds);
		return new ListPage<>(list,count);
	}

	@Override
	public MerchantDto selectMerchant(Long accountId) {
		return merchantMapper.selectByPrimaryKey(accountId);
	}

	@Override
	public List<MerchantAreaDto> selectMerchantArea(Long accountId) {
		MerchantAreaDtoKey key = new MerchantAreaDtoKey();
		key.setAccountId(accountId);
		return merchantAreaDtoMapper.selectMerchantArea(key);
	}

	@Override
	public List<MerchantServiceDto> selectMerchantService(Long accountId) {
		MerchantServiceDtoKey key = new MerchantServiceDtoKey();
		key.setAccountId(accountId);
		return merchantServiceDtoMapper.selectMerchantService(key);
	}

	@Override
	public MerchantHolderDto getMerchant(Long accountId) {
		MerchantDto dto = selectMerchantAndAdress(accountId);
		if (dto == null) {
			return null;
		}
		MerchantHolderDto dtoExt = new MerchantHolderDto();
		dtoExt.setDto(dto);
		List<MerchantAreaDto> list = selectMerchantArea(accountId);
		List<MerchantServiceDto> slist = selectMerchantService(accountId);
		dtoExt.setAreaList(list);
		dtoExt.setServiceList(slist);
		return dtoExt;
	}

	// @Override
	@Transactional
	@Deprecated
	public void addMerchant(MerchantExtDto merchant) {

		if (merchant == null) {
			return;
		}
		Date date = new Date();
		merchant.setCreateTime(date);
		addMerchant(merchant.dto());
		if (!CollectionUtils.isEmpty(merchant.getAreaList())) {
			for (MerchantAreaDto one : merchant.getAreaList()) {
				one.setAccountId(merchant.getAccountId());
				addMerchantArea(one);
			}
		}
		if (!CollectionUtils.isEmpty(merchant.getServiceList())) {
			for (MerchantServiceDto one : merchant.getServiceList()) {
				one.setAccountId(merchant.getAccountId());
				one.setCreateTime(date);
				addMerchantService(one);
			}
		}
	}

	@Transactional
	public void addMerchant(MerchantDto dto) {
		if (StringUtils.isEmpty(dto.getState())) {
			dto.setState(State.VALID.getState());
		}
		if (dto.getAccountId() == null) {
			UserDto userDto = dto.toUserDto();
			userDto.setPassword(StringUtils.isEmpty(dto.getPassword()) ? DigestUtils.md5Hex("123456")
					: DigestUtils.md5Hex(dto.getPassword()));
			userService.addUser(userDto,true);
			dto.setAccountId(userDto.getAccountId());
		}
		// if(!StringUtils)
		merchantMapper.insertSelective(dto);
	}

	public boolean addMerchantArea(MerchantAreaDto dto) {
		if (dto == null || dto.getAccountId() == null) {
			return false;
		}
		if (!StringUtils.isEmpty(dto.getShape()) && !StringUtils.isEmpty(dto.getPoint()) && dto.getLat() == null
				&& dto.getLng() == null) {
			IShape shape = shapeGenerator.generatorShape(dto.getShape(), dto.getPoint());
			IPoint[] points = shape.containingRect();
			dto.setLng(points[0].getX());
			dto.setLat(points[0].getY());
			dto.setRlng(points[1].getX());
			dto.setRlat(points[1].getY());
			if (StringUtils.isEmpty(dto.getAreaCode())) {
				dto.setAreaCode("F" + System.currentTimeMillis() + (10000 + RandomUtils.nextInt(90000)));
			}
			if(!StringUtils.isEmpty(dto.getArea())&&StringUtils.isEmpty(dto.getCname())){
				dto.setCname(dto.getArea());
			}
		}
		merchantAreaDtoMapper.insertSelective(dto);
		return true;

	}

	public boolean addMerchantService(MerchantServiceDto dto) {
		if (dto == null || dto.getAccountId() == null || dto.getSlsId() == null) {
			return false;
		}
		merchantServiceDtoMapper.insertSelective(dto);
		return true;
	}

	@Override
	@Transactional
	@Deprecated
	public void addMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) {
		if (merchant == null) {
			return;
		}
		Date date = new Date();
		merchant.setCreateTime(date);
		addMerchant(merchant);
		if (!CollectionUtils.isEmpty(areaList)) {
			for (MerchantAreaDto one : areaList) {
				one.setAccountId(merchant.getAccountId());
				addMerchantArea(one);
			}
		}
		if (!CollectionUtils.isEmpty(serviceList)) {
			for (MerchantServiceDto one : serviceList) {
				one.setAccountId(merchant.getAccountId());
				one.setCreateTime(date);
				addMerchantService(one);
			}
		}
	}

	@Override
	public MerchantDto selectMerchantAndAdress(Long accountId) {
		MerchantDto dto = selectMerchant(accountId);
		if (dto != null && !StringUtils.isEmpty(dto.getAddressCode())) {
			dto.setAddress(areaService.selectAreaName(dto.getAddressCode()));
		}
		return dto;

	}

	/*
	 * @Override public List<MerchantAreaDto> select(MerchantAreaDto leftDown,
	 * MerchantAreaDto rightTop, Integer slsId) { // TODO Auto-generated method
	 * stub return null; }
	 */

	@Override
	public List<MerchantAreaDto> select(Point center, int radius, Integer slsId) {
		double dLat = Double.parseDouble(center.getLat());
		double dLng = Double.parseDouble(center.getLng());
		double[] points = LatLngUtiil.getAround(dLat, dLng, radius*1000);
		MerchantAreaDto leftDown = build(points[0], points[1]);
		MerchantAreaDto rightTop = build(points[2], points[3]);
		return merchantAreaDtoMapper.selectPoints(leftDown, rightTop, slsId);// 查询应该存在问题应该根据商户表中的经纬度查询
	}

	protected MerchantAreaDto build(double lat, double lng) {
		MerchantAreaDto dto = new MerchantAreaDto();
		dto.setLat(new BigDecimal(lat, MathContext.DECIMAL64));
		dto.setLng(new BigDecimal(lng, MathContext.DECIMAL64));
		return dto;
	}

	protected MerchantAreaDto matchShape(MerchantAreaDto mad, Integer slsId) {
		IPoint point = new SimplePoint(mad.getLng(), mad.getLat());
		List<MerchantAreaDto> areaList = merchantAreaDtoMapper.selectAreas(point, slsId);
		if (CollectionUtils.isEmpty(areaList)) {
			return null;
		}
		IShape shape = null;
		for (MerchantAreaDto one : areaList) {
			shape = getShape(one);
			if (shape != null && shape.contains(point)) {
				return one;
			}
		}
		return null;
	}

	protected IShape getShape(MerchantAreaDto one) {
		try {
			return shapeGenerator.generatorShape(one.getShape(), one.getPoint());
		} catch (Exception e) {
			LOGGER.error("" + e.getMessage(), e);
			return null;
		}
	}

	protected MerchantAreaDto matchCircle(MerchantAreaDto mad, Integer slsId) {
		MerchantAreaDto minDto = null;
		Point pt = new Point(mad.getLng(), mad.getLat());
		List<MerchantAreaDto> areaDto = select(pt, 5, slsId);
		if (CollectionUtils.isEmpty(areaDto)) {
			return null;
		}
		double min = Double.MAX_VALUE;
		double temp;

		for (MerchantAreaDto one : areaDto) {

			temp = LatLngUtiil.getDistance(one.getLng().doubleValue(), one.getLat().doubleValue(),
					mad.getLng().doubleValue(), mad.getLat().doubleValue());
			if (temp < min) {
				minDto = one;
				min = temp;
			}
		}
		return minDto;
	}

	@Override
	public MerchantDto match(MerchantAreaDto mad, Integer slsId) {
		if (!StringUtils.isEmpty(mad.getAreaCode())) {
			List<MerchantDto> dto = merchantMapper.selectMatched(mad.getAreaCode(), slsId);
			if (!CollectionUtils.isEmpty(dto)) {
				return dto.get(0);
			}
		}
		MerchantAreaDto minDto = matchShape(mad, slsId);
		if (minDto == null) {
			minDto = matchCircle(mad, slsId);
		}
		if (minDto == null) {
			return null;
		}
		return selectMerchant(minDto.getAccountId());
	}

	@Override
	@Transactional
	public void addMerchantV2(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) {
		if (merchant == null || CollectionUtils.isEmpty(serviceList)) {
			return;
		}
		verifyMerchantService(merchant, serviceList);
		Date date = new Date();
		merchant.setCreateTime(date);
		addMerchant(merchant);
		if (!CollectionUtils.isEmpty(areaList)) {
			for (MerchantAreaDto one : areaList) {
				one.setAccountId(merchant.getAccountId());
				addMerchantArea(one);
			}
		}
		if (!CollectionUtils.isEmpty(serviceList)) {
			for (MerchantServiceDto one : serviceList) {
				one.setAccountId(merchant.getAccountId());
				one.setCreateTime(date);
				one.setState(merchant.getState());
				one.setCr(merchant.getCr());
				addMerchantService(one);
			}
		}
	}

	public void verifyMerchantService(MerchantDto dto, List<MerchantServiceDto> serviceList) {

	}

	@Override
	@Transactional
	public void checkMerchant(Long accountId, String cr, String cause) {
		if(StringUtils.isEmpty(cr)||accountId==null){
			return;
		}
		MerchantDto dto = new MerchantDto();
		dto.setAccountId(accountId);
		dto.setCause(StringUtils.defaultIfEmpty(cause, CrEnum.PASSING.codeEqual(cr)?"":null));//如果是认证通过将原因更新为空白字符串
		dto.setCr(cr);
		//dto.setState(State.VALID.getState());
		merchantMapper.updateByPrimaryKeySelective(dto);
		MerchantServiceDto serviceDto=new MerchantServiceDto();
		serviceDto.setCr(cr);
		MerchantServiceDtoExample example=new MerchantServiceDtoExample();
		MerchantServiceDtoExample.Criteria cri= example.createCriteria();
		cri.andAccountIdEqualTo(accountId);
		merchantServiceDtoMapper.updateByExampleSelective(serviceDto, example);
		
	}

	@Override
	@Transactional
	public void enterMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) {
		MerchantDto temp = merchantMapper.selectByPrimaryKey(merchant.getAccountId());
		if (temp != null) {
			return;
		}
		addMerchantV2(merchant, serviceList, areaList);
	}

	@Override
	@Transactional
	public void editMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) {
		if (merchant == null) {
			return;
		}
		//merchant.setAlipayAccount(null);// 禁止更新支付宝账号
		//merchant.setWechatAccount(null);// 禁止更新微信账号
		//merchant.setBusinessCategory(null);//禁止更新业务类型
		MerchantDto temp = merchantMapper.selectByPrimaryKey(merchant.getAccountId());
		editMerchant(merchant, serviceList, areaList);
		
	}
	/**
	 * @param merchant
	 * @param serviceList
	 * @param areaList
	 * @param temp
	 */
	protected void editMerchant(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList,MerchantDto temp){
	
		if (temp == null) {
			return;
		}
		merchant.setCr(temp.getCr());
		merchant.setState(temp.getState());
		merchantMapper.updateByPrimaryKeySelective(merchant);

		if (!CollectionUtils.isEmpty(areaList)) {
			deleteArea(merchant.getAccountId());// 先删除后插入
			for (MerchantAreaDto one : areaList) {
				one.setAccountId(merchant.getAccountId());
				addMerchantArea(one);
			}
		}
		Date date = new Date();
		if (!CollectionUtils.isEmpty(serviceList)) {
			deleteService(merchant.getAccountId());// 先删除后插入
			for (MerchantServiceDto one : serviceList) {
				one.setAccountId(merchant.getAccountId());
				one.setCreateTime(date);
				one.setState(merchant.getState());
				one.setCr(merchant.getCr());
				addMerchantService(one);
			}
		}
		if(!StringUtils.isEmpty(merchant.getBusinessCategory())||!StringUtils.isEmpty(merchant.getAlipayAccount())||
				!StringUtils.isEmpty(merchant.getWechatAccount())){//如果更新了业务类型,微信账号等
			UserLinkIdDto linkedDto=new UserLinkIdDto();
			linkedDto.setAccountId(merchant.getAccountId());
			linkedDto.setWechatAccount(StringUtils.defaultIfBlank(merchant.getWechatAccount(), null));
			linkedDto.setAlipayAccount(StringUtils.defaultIfBlank(merchant.getAlipayAccount(), null));
			linkedDto.setWechatName(StringUtils.defaultIfBlank(merchant.getWechatName(), null));
			linkedDto.setBusinessCategory(StringUtils.defaultIfBlank(merchant.getBusinessCategory(), null));
			userService.updateUserLinked(linkedDto);
		}
	}
	
	public void deleteArea(Long merchatId) {
		MerchantAreaDtoExample example = new MerchantAreaDtoExample();
		MerchantAreaDtoExample.Criteria cri = example.createCriteria();
		cri.andAccountIdEqualTo(merchatId);
		merchantAreaDtoMapper.deleteByExample(example);
	}

	public void deleteService(Long merchatId) {
		MerchantServiceDtoExample example = new MerchantServiceDtoExample();
		MerchantServiceDtoExample.Criteria cri = example.createCriteria();
		cri.andAccountIdEqualTo(merchatId);
		merchantServiceDtoMapper.deleteByExample(example);
	}

	@Override
	public void checkMerchant(List<MerchantCrDto> mdtoList) {
		if(CollectionUtils.isEmpty(mdtoList)){
			for(MerchantCrDto one:mdtoList){
				checkMerchant(one.getAccountId(), one.getCr(), one.getCause());
			}
		}
	}

	@Override
	public void onOff(List<IdStateDto> list) {
		for(IdStateDto one:list){
			onOff(one);
		}
	}
	
	
	public void onOff(IdStateDto idState) {
		if(idState==null||idState.getId()==null||StringUtils.isEmpty(idState.getState())){
			return ;
		}
		MerchantDto dto = new MerchantDto();
		dto.setAccountId(idState.getId());
		dto.setState(idState.getState());
		merchantMapper.updateByPrimaryKeySelective(dto);
	}
    protected int compare(Map<Long,Double> cached,java.awt.geom.Point2D center,MerchantAreaDto one,MerchantAreaDto two){
    	Double oneDistance=getDistance(cached, center, one);
    	Double twoDistance=getDistance(cached, center, two);
    	return oneDistance.compareTo(twoDistance);
    }
    protected Double  getDistance(Map<Long,Double> cached,java.awt.geom.Point2D center,MerchantAreaDto one){
    	Double oneDistance=cached.get(one.getAccountId());
    	if(oneDistance==null){
    		 oneDistance=LatLngUtiil.getDistance(one.getLng().doubleValue(), one.getLat().doubleValue(), center.getX(),  center.getY());
     		cached.put(one.getAccountId(), oneDistance);
    	}
    	return oneDistance;
    }
	@Override
	public IPage<MerchantDto> selectMerchants(Point center, int radius, Integer slsId,IPageBound bounds) {
		if(radius<0){
			radius=5;
		}
		return selectMerchants(center,radius,slsId,bounds,new HashMap<>());
		
	}
	public IPage<MerchantDto> selectMerchants(Point center, int radius, Integer slsId,IPageBound bounds,Map<Long,Double>  mapped) {
		List<MerchantAreaDto> lists=select(center, radius, slsId);
		if(CollectionUtils.isEmpty(lists)){
			return null;
		}
		java.awt.geom.Point2D center2=center.toDoublePoint();
		if(lists.size()>=2){
		    Collections.sort(lists,(one,two)->compare(mapped,center2,one,two));
		}else{
			getDistance(mapped, center2, lists.get(0));
		}
		List<MerchantAreaDto> areaDto=lists.subList(bounds.from(), bounds.end(lists.size()));
		List<MerchantDto> mdtos= selectMerchantList(areaDto);
		Collections.sort(mdtos,(one,two)->mapped.get(one.getAccountId()).compareTo(mapped.get(two.getAccountId())));
		return new ListPage<>(mdtos, lists.size());
	}
	
	
	@Override
	public IPage<MerchantDescriptionDto> selectMerchantDesc(Point center, int radius, Integer slsId,
			IPageBound bounds) {
		Map<Long,Double>  mapped =new HashMap<>();
		IPage<MerchantDto> page=selectMerchants(center,radius,slsId,bounds,mapped);
		if(page!=null&&!CollectionUtils.isEmpty(page.getList())){
			List<MerchantDescriptionDto> pageList=new ArrayList<MerchantDescriptionDto>(page.getList().size());
			List<OrderEvaluationDto> list=orderInfoService.selectEves(MerchantUtils.toAccoutIdList(page.getList()));
			for(MerchantDto one:page.getList()){
				MerchantDescriptionDto desc=  toMerchantDescriptionDto(one,mapped.get(one.getAccountId()),list);
		        pageList.add(desc);
			}
			return new ListPage<>(pageList, page.getCount());
		}
		return null;
	}
	
	public MerchantDescriptionDto toMerchantDescriptionDto(MerchantDto one, Double distance,
			List<OrderEvaluationDto> list) {
		MerchantDescriptionDto dto = new MerchantDescriptionDto();
		dto.setMerchantAccountId(one.getAccountId());
		dto.setAddress(one.getDetailAddress());
		dto.setCname(one.getCname());
		dto.setDescription(one.getDescription());
		dto.setContactInformation(one.getContactInformation());
		dto.setMerchantLogo(one.getMerchantLogo());
		if (one.getLat() != null) {
			dto.setLat(one.getLat().toString());
		}
		if (one.getLng() != null) {
			dto.setLng(one.getLng().toString());
		}
		if (distance != null) {
			dto.setDistance(distance.intValue());
		}
		dto.setOrderCount(0);
		dto.setMerchantEvaluation("0");
		if (!CollectionUtils.isEmpty(list)) {
			for (OrderEvaluationDto oneEve : list) {
				if (one.getAccountId().equals(oneEve.getMerchantAccountId())) {
					dto.setOrderCount(oneEve.getOrderCount());
					dto.setMerchantEvaluation(oneEve.cal());
				}
			}
		}
		return dto;
	}
	protected List<MerchantDto> selectMerchantList(List<MerchantAreaDto> merchantAreaDtoList) {
		if(CollectionUtils.isEmpty(merchantAreaDtoList)){
			return null;
		}
		List<Long> arrayList=new ArrayList<>(merchantAreaDtoList.size());
		merchantAreaDtoList.stream().forEach(one->arrayList.add(one.getAccountId()));
		return selectMerchants(arrayList);
		
	}
	@Override
	public List<MerchantDto> selectMerchants(List<Long> merchantId) {
		MerchantDtoExample example=new MerchantDtoExample();
		MerchantDtoExample.Criteria cri= example.createCriteria();
		cri.andAccountIdIn(merchantId);
		return merchantMapper.selectByExample(example);
	}

	@Override
	public String getMerchantLogo(Long accountId) {
		MerchantDto dto=selectMerchant(accountId);
		if(dto.getMpn()==null){
			return null;
		}
		return dto.getMpn();
	}

	@Override
	public void updateEve(Long accountId, int eve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrivalStatusEnum selectArrivalStatus(Long accountId) {
		MerchantDto dto=selectMerchant(accountId);
		if(dto==null){
			return ArrivalStatusEnum.NOT_ARRIVAL;
		}
		return CrEnum.PASSING.codeEqual(dto.getCr())?ArrivalStatusEnum.NORMAL:ArrivalStatusEnum.NOT_AUDIT;
	}

	@Override
	public void editMerchantWithCheck(MerchantDto merchant, List<MerchantServiceDto> serviceList,
			List<MerchantAreaDto> areaList) {
		MerchantDto dto=selectMerchant(merchant.getAccountId());
		if(dto==null){
			throw new IllegalArgumentException("商户不存在。");
		}
		if(CrEnum.PASSING.codeEqual(dto.getCr())){
			throw new IllegalArgumentException("已审核通过,不能编辑。");
		}
		editMerchant(merchant, serviceList, areaList,dto);
	}

	
}
