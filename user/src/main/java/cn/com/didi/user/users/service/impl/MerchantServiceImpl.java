package cn.com.didi.user.users.service.impl;

import java.awt.Shape;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.shape.IPoint;
import cn.com.didi.core.shape.IShape;
import cn.com.didi.core.shape.IShapeGenerator;
import cn.com.didi.core.shape.impl.SimplePoint;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.LatLngUtiil;
import cn.com.didi.domain.util.State;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
import cn.com.didi.user.area.service.IAreaService;
import cn.com.didi.user.users.dao.mapper.MerchantAreaDtoMapper;
import cn.com.didi.user.users.dao.mapper.MerchantDtoMapper;
import cn.com.didi.user.users.dao.mapper.MerchantServiceDtoMapper;
import cn.com.didi.user.users.domain.MerchantAreaDto;
import cn.com.didi.user.users.domain.MerchantAreaDtoExample;
import cn.com.didi.user.users.domain.MerchantAreaDtoKey;
import cn.com.didi.user.users.domain.MerchantDto;
import cn.com.didi.user.users.domain.MerchantExtDto;
import cn.com.didi.user.users.domain.MerchantHolderDto;
import cn.com.didi.user.users.domain.MerchantServiceDto;
import cn.com.didi.user.users.domain.MerchantServiceDtoKey;
import cn.com.didi.user.users.domain.UserDto;
import cn.com.didi.user.users.service.IMerchantService;
import cn.com.didi.user.users.service.IUserService;

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

	@Override
	public IPage<MerchantDto> selectMerchants(TimeInterval interval) {
		PageBounds pageBounds = new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<MerchantDto> list = (PageList<MerchantDto>) merchantMapper.selectMerchants(interval, pageBounds);
		return new MybatisPaginatorPage<>(list);
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
		UserDto userDto = dto.toUserDto();
		userDto.setPassword(StringUtils.isEmpty(dto.getPassword())?DigestUtils.md5Hex("123456"):DigestUtils.md5Hex(dto.getPassword()));
		userService.addUser(userDto);
		dto.setAccountId(userDto.getAccountId());
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
			if(StringUtils.isEmpty(dto.getAreaCode())){
				dto.setAreaCode("F"+System.currentTimeMillis()+(10000+RandomUtils.nextInt(90000)));
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
		double[] points = LatLngUtiil.getAround(dLat, dLng, radius);
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

	protected MerchantAreaDto matchShape(MerchantAreaDto mad, Integer slsId){
		IPoint point=new SimplePoint(mad.getLng(), mad.getLat());
		 List<MerchantAreaDto> areaList=merchantAreaDtoMapper.selectAreas(point, slsId);
		 if(CollectionUtils.isEmpty(areaList)){
			 return null;
		 }
		 IShape shape=null;
		 for(MerchantAreaDto one:areaList){
			 shape=getShape(one);
			 if(shape!=null&&shape.contains(point)){
				 return one;
			 }
		 }
		 return null;
	}

	protected IShape getShape(MerchantAreaDto one) {
		try {
			return shapeGenerator.generatorShape(one.getShape(), one.getPoint());
		} catch (Exception e) {
			LOGGER.error(""+e.getMessage(), e);
			return null;
		}
	}
	
	protected MerchantAreaDto matchCircle(MerchantAreaDto mad, Integer slsId){
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
		if(minDto==null){
			minDto=matchCircle(mad, slsId);
		}
		if(minDto==null){
			return null;
		}
		return selectMerchant(minDto.getAccountId());
	}

	@Override
	@Transactional
	public void addMerchantV2(MerchantDto merchant, List<MerchantServiceDto> serviceList,
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
				one.setState(merchant.getState());
				one.setCr(merchant.getCr());
				addMerchantService(one);
			}
		}

	}
}
