package cn.com.didi.app.deal.domain;

import cn.com.didi.core.property.ICodeAble;
import cn.com.didi.domain.util.DealEnum;
import cn.com.didi.domain.util.PayAccountEnum;
import cn.com.didi.order.trade.domain.MerchantDayRemainingDto;
import cn.com.didi.user.users.domain.UserLinkIdDto;

public class ReamainingWrapperJAO {
	private MerchantDayRemainingDto reamainingDto;
	private String account;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Long getRemaining() {
		return reamainingDto.getRemaining();
	}
	public void setRemaining(Long remaining) {
		reamainingDto.setRemaining(remaining);
	}
	public String getPat() {
		return reamainingDto.getPat();
	}
	public void setPat(String pat) {
		reamainingDto.setPat(pat);
	}
	public ReamainingWrapperJAO(MerchantDayRemainingDto reamainingDto, UserLinkIdDto account) {
		super();
		this.reamainingDto = reamainingDto;
		PayAccountEnum enums=	ICodeAble.getCode(PayAccountEnum.values(), reamainingDto.getPat());
		this.setAccount(enums.getAccoutName(account));
	}
	
}
