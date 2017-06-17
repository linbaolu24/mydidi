package cn.com.didi.thirdExt.property;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.didi.domain.util.CodeNameConstatns;
import cn.com.didi.order.trade.domain.DealDrawListDto;
import cn.com.didi.user.system.domain.CodeDictionaryDto;
import cn.com.didi.user.system.service.ICodeDicService;
import cn.com.didi.user.system.service.ICodeTextResolver;

public class StateTextResolverImpl implements ICodeTextResolver{
	private ICodeDicService codeDicService;
	private static final Logger LOGGER=LoggerFactory.getLogger(StateTextResolverImpl.class);

	@Override
	public void resolverDraw(List<DealDrawListDto> drawList) {
		if(CollectionUtils.isEmpty(drawList)){
			return ;
		}
		List<CodeDictionaryDto> lists=codeDicService.selectCodes(CodeNameConstatns.DRAW_STATE_TEXT_CDOE_NAME);
		if(CollectionUtils.isEmpty(lists)){
			LOGGER.debug("未配置{}字典",CodeNameConstatns.DRAW_STATE_TEXT_CDOE_NAME);
			return ;
		}
		drawList.forEach(one->{
			for(CodeDictionaryDto two:lists){
				if(two.getDcode().equals(one.getState())){
					one.setStateText(two.getText());
				}
			}
		});
	}

	public ICodeDicService getCodeDicService() {
		return codeDicService;
	}

	public void setCodeDicService(ICodeDicService codeDicService) {
		this.codeDicService = codeDicService;
	}

}
