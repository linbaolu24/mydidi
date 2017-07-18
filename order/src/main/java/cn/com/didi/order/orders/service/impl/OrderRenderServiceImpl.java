package cn.com.didi.order.orders.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.core.utils.DateUtil;
import cn.com.didi.domain.util.CodeNameConstatns;
import cn.com.didi.domain.util.OrderState;
import cn.com.didi.domain.util.SpecialTypeEnum;
import cn.com.didi.order.orders.domain.OrderDto;
import cn.com.didi.order.orders.domain.OrderListBaseDto;
import cn.com.didi.order.orders.service.IOrderRenderService;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.service.ICodeDicService;

@Service
public class OrderRenderServiceImpl implements IOrderRenderService{
	
	@Resource
	ICodeDicService codeDicService;
	@Override
	public String renderStateText(OrderDto order) {
		CodeDictionaryDto dto=null;
		if(SpecialTypeEnum.MRMF.codeEqual(order.getSpecialType())){
			dto=codeDicService.selectCode(CodeNameConstatns.ORDER_SPECIAL_MRMF_STATE_TEXT_CODE_NAME, order.getState());
		}else{
			dto=codeDicService.selectCode(CodeNameConstatns.ORDER_SPECIAL_NORMAL_STATE_TEXT_CODE_NAME, order.getState());
		}
		if(dto==null){
			return null;
		}
		return dto.getText();
	}

	@Override
	public String renderStateText(OrderListBaseDto listBase) {
		CodeDictionaryDto dto=null;
		if(SpecialTypeEnum.MRMF.codeEqual(listBase.getSpecialType())){
			dto=codeDicService.selectCode(CodeNameConstatns.ORDER_SPECIAL_MRMF_STATE_TEXT_CODE_NAME, listBase.getState());
		}else{
			dto=codeDicService.selectCode(CodeNameConstatns.ORDER_SPECIAL_NORMAL_STATE_TEXT_CODE_NAME, listBase.getState());
		}
		if(dto==null){
			return null;
		}
		return dto.getText();
	}

	@Override
	public Integer judgeCommunionFlag(OrderListBaseDto listBase) {

		if (OrderState.ORDER_STATE_CANNEL.codeEqual(listBase.getState())) {
			listBase.setCommunionTip("订单已取消，如有需要，请联系客服0577-64600006");
			return 0;
		}
		if (listBase.getOrt() == null) {
			return 0;
		}
		Date sst = listBase.getSst();
		if (sst != null && !DateUtil.lessInterval(sst, new Date(), 7)) {
			listBase.setCommunionTip("订单已关闭，如有需要，请联系客服0577-64600006");
			return 0;
		}
		return 1;
	}
	

}
