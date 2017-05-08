package cn.com.didi.order.orders.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.didi.domain.util.CodeNameConstatns;
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
	

}
