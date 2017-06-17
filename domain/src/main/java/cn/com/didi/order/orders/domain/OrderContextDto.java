package cn.com.didi.order.orders.domain;

import cn.com.didi.domain.domains.IMerchantDto;
import cn.com.didi.domain.domains.IReciverDto;
import cn.com.didi.domain.domains.UseAbleDto;
import cn.com.didi.order.orders.util.OrderMessageOperation;
import cn.com.didi.user.users.domain.VipDto;

/**
 * 状态变更DTO
 * @author xlm
 *
 */
public class OrderContextDto {
	private OrderDto orderDto;
	private IMerchantDto merchantDto;
	private IReciverDto reciverDto;
	private OrderMessageOperation lastSuccess;
	private int flag;
	private UseAbleDto<VipDto> used;
	
	public OrderDto getOrderDto() {
		return orderDto;
	}

	public void setOrderDto(OrderDto orderDto) {
		this.orderDto = orderDto;
	}

	public OrderContextDto() {
		super();
	}

	public OrderContextDto(OrderDto orderDto) {
		super();
		this.orderDto = orderDto;
	}

	public OrderContextDto(OrderDto orderDto, IMerchantDto merchantDto) {
		super();
		this.orderDto = orderDto;
		this.merchantDto = merchantDto;
	}

	public IMerchantDto getMerchantDto() {
		return merchantDto;
	}

	public void setMerchantDto(IMerchantDto merchantDto) {
		this.merchantDto = merchantDto;
	}

	public IReciverDto getReciverDto() {
		return reciverDto;
	}

	public void setReciverDto(IReciverDto reciverDto) {
		this.reciverDto = reciverDto;
	}

	public OrderMessageOperation getLastSuccess() {
		return lastSuccess;
	}

	public void setLastSuccess(OrderMessageOperation lastSuccess) {
		this.lastSuccess = lastSuccess;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public UseAbleDto<VipDto> getUsed() {
		return used;
	}

	public void setUsed(UseAbleDto<VipDto> used) {
		this.used = used;
	}
	
}
