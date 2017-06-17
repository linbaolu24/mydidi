package cn.com.didi.order.trade.domain;

import java.util.List;

import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;

/**
 * @author xlm
 *
 */
public class DrawInfoDto {
	private Long total;
	private Long pending;
	private List<MerchantDayRemainingDto> remainDto;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getPending() {
		return pending;
	}
	public void setPending(Long pending) {
		this.pending = pending;
	}
	public List<MerchantDayRemainingDto> getRemainDto() {
		return remainDto;
	}
	public void setRemainDto(List<MerchantDayRemainingDto> remainDto) {
		this.remainDto = remainDto;
	}
	public DrawInfoDto() {
		this.total = 0L;
		this.pending = 0L;
	}
	
}
