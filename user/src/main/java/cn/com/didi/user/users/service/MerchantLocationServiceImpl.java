package cn.com.didi.user.users.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.Point;
import cn.com.didi.domain.util.ILocationSearchService;
@Service
public class MerchantLocationServiceImpl implements ILocationSearchService{

	@Override
	public List<IReciverDto> list(Point poi, Integer slsId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IReciverDto match(Point poi, Integer slsId) {
		// TODO Auto-generated method stub
		return null;
	}

}
