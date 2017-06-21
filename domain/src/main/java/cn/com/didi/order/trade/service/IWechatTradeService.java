package cn.com.didi.order.trade.service;

import cn.com.didi.core.property.IResult;
import cn.com.didi.domain.domains.WechatPayCustomerReturnVo;
import cn.com.didi.domain.domains.WechatPayNotifyReturnVO;
import cn.com.didi.domain.domains.wechat.WechatPayContext;
import cn.com.didi.order.trade.domain.DealDto;

public interface IWechatTradeService {
	/**
	 * @param orderId
	 * @param bId
	 * @return
	 */
	IResult<WechatPayContext> createOdrerRequest(Long orderId, Long bId,String desc);
	/**
	 * @param orderId
	 * @param bId
	 * @return
	 */
	IResult<WechatPayContext> createPayRequest(Long accountId,String type,String obj);
	/**
	 * @param map
	 * @return
	 */
	IResult<WechatPayNotifyReturnVO> asynnotify(String notifyStr);
	/**
	 * @param type
	 * @param notifyStr
	 * @return
	 */
	public IResult<WechatPayNotifyReturnVO> asynnotify(String type, String notifyStr ) ;
	/**
	 * 发送转账到支付宝
	 * @param dto
	 * @return
	 */
	public IResult<WechatPayCustomerReturnVo> sendTransForm(DealDto dto);
}
