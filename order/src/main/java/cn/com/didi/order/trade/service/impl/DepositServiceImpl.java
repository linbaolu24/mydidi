package cn.com.didi.order.trade.service.impl;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.didi.core.select.IPage;
import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.domain.query.TimeInterval;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.IReciverSearchService;
import cn.com.didi.domain.util.State;
import cn.com.didi.order.trade.dao.mapper.DepositDtoMapper;
import cn.com.didi.order.trade.domain.DepositDto;
import cn.com.didi.order.trade.service.IDepositService;
import cn.com.didi.thirdExt.select.MybatisPaginatorPage;
@Service
public class DepositServiceImpl implements IDepositService{
	@Resource
	protected DepositDtoMapper myDepositMapper;
	@Resource
	protected IReciverSearchService reciverSearchService;
	protected static Date endTime;
	static{
		Calendar cla= Calendar.getInstance();
		cla.set(2030, 1, 1);
		endTime=cla.getTime();
	}

	@Override
	public void refound(Long depositId) {
		DepositDto dto=new DepositDto();
		dto.setDepositId(depositId);
		dto.setState(State.UNVALID.getState());
		myDepositMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public IPage<DepositDto> selectDepositList(TimeInterval interval) {
		PageBounds pageBounds=new PageBounds(interval.getPageIndex(), interval.getPageSize(), true);
		PageList<DepositDto> pageList=(PageList<DepositDto> )myDepositMapper.selectDepositList(interval, pageBounds);
		return new MybatisPaginatorPage<>(pageList);
	}

	@Override
	public boolean existDeposit(Long accountId) {
		return myDepositMapper.selectFirstDepositId(accountId,new Date(),State.VALID.getState())!=null;
	}

	@Override
	public Long countDeposit(Long accountId) {
		return myDepositMapper.countDeposit(accountId, new Date(), State.VALID.getState());
	}

	@Override
	public void addDepositDto(DepositDto dto) {
		if(dto==null){
			return ;
		}
		if(StringUtils.isEmpty(dto.getState())){
			dto.setState(State.VALID.getState());
		}
		if(StringUtils.isEmpty(dto.getBpn())){
			dto.setBpn(reciverSearchService.getPhone(dto.getSai()));
		}
		if(dto.getEndTime()==null){
			dto.setEndTime(getEndTime(dto));
		}
		myDepositMapper.insertSelective(dto);
		
	}
	protected Date getEndTime(DepositDto dto){
		return DateUtil.getDateIntervalYear(dto.getCreateTime(),1);
	}

	@Override
	public void updateTradeId(Long depositId, Long tradeId) {
		DepositDto dto=new DepositDto();
		dto.setDepositId(depositId);
		dto.setDealId(tradeId);
		myDepositMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public void updateTradeState(Long depositId, String tradeState) {
		DepositDto dto=new DepositDto();
		dto.setDepositId(depositId);
		dto.setPayState(tradeState);
		myDepositMapper.updateByPrimaryKeySelective(dto);
	}

	@Override
	public boolean existDeposit(Long accountId, Long depositId) {
		DepositDto dto=myDepositMapper.selectByPrimaryKey(depositId);
		if(dto==null){
			return false;
		}
		if(dto.getSai()==null||!dto.getSai().equals(accountId)){
			return false;
		}
		if(!State.VALID.getState().equals(dto.getState())||!DealEnum.FINISH.getCode().equals(dto.getPayState())){
			return false;
		}
		return true;
	}
	
}
