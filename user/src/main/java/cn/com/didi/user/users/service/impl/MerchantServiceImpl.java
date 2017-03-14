package cn.com.didi.user.users.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.LatLngUtiil;
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

	//@Override
	@Transactional
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
		UserDto userDto = dto.toUserDto();
		userDto.setPassword(DigestUtils.md5Hex("123456"));
		userService.addUser(userDto);
		dto.setAccountId(userDto.getAccountId());
		merchantMapper.insertSelective(dto);
	}

	public boolean addMerchantArea(MerchantAreaDto dto) {
		if (dto == null || dto.getAccountId() == null || StringUtils.isEmpty(dto.getAreaCode())) {
			return false;
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
		return merchantAreaDtoMapper.selectPoints(leftDown, rightTop, slsId);//查询应该存在问题应该根据商户表中的经纬度查询
	}

	protected MerchantAreaDto build(double lat, double lng) {
		MerchantAreaDto dto = new MerchantAreaDto();
		dto.setLat(new BigDecimal(lat, MathContext.DECIMAL64));
		dto.setLng(new BigDecimal(lng, MathContext.DECIMAL64));
		return dto;
	}

	@Override
	public MerchantDto match(MerchantAreaDto mad,Integer slsId) {
		List<MerchantDto> dto=merchantMapper.selectMatched(mad.getAreaCode(), slsId);
		if(!CollectionUtils.isEmpty(dto)){
			return dto.get(0);
		}
		Point pt=new Point(mad.getLng(), mad.getLat());
		List<MerchantAreaDto> areaDto=select(pt,5,slsId);
		if(CollectionUtils.isEmpty(dto)){
			return null;
		}
		double min=Double.MAX_VALUE;
		double temp;
		MerchantAreaDto minDto=null;
		for(MerchantAreaDto one:areaDto){

			temp=LatLngUtiil.getDistance(one.getLng().doubleValue(), one.getLat().doubleValue(), mad.getLng().doubleValue(), mad.getLat().doubleValue());
			if(temp<min){
				minDto=one;
				min=temp;
			}
		}
		return selectMerchant(minDto.getAccountId());
	}
}
