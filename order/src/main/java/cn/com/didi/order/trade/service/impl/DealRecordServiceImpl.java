package cn.com.didi.order.trade.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.order.trade.dao.mapper.DealRecordDtoMapper;
import cn.com.didi.order.trade.domain.DealRecordDto;
import cn.com.didi.order.trade.service.IDealRecordService;
@Service
public class DealRecordServiceImpl implements IDealRecordService {
	@Resource
	protected DealRecordDtoMapper myDealRecordDtoMapper;

	@Override
	public void addRecord(DealRecordDto dto) {
		if (dto.getCreateTime() == null) {
			dto.setCreateTime(new Date());
		}
		myDealRecordDtoMapper.insert(dto);

	}

}
